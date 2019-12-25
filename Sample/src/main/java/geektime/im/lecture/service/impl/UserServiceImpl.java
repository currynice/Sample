package geektime.im.lecture.service.impl;

import geektime.im.lecture.dao.MessageContactRepository;
import geektime.im.lecture.dao.MessageContentRepository;
import geektime.im.lecture.dao.UserRepository;
import geektime.im.lecture.entity.MessageContact;
import geektime.im.lecture.entity.MessageContent;
import geektime.im.lecture.entity.User;
import geektime.im.lecture.exceptions.InvalidUserInfoException;
import geektime.im.lecture.exceptions.UserNotExistException;
import geektime.im.lecture.service.UserService;
import geektime.im.lecture.vo.ContactInfo;
import geektime.im.lecture.vo.MessageContactVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {



    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageContactRepository contactRepository;

    @Autowired
    private MessageContentRepository contentRepository;


    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public User login(String email, String password) {
        //根据邮箱账号登录
      User user = userRepository.findByEmailEquals(email);
        if (null == user) {
            log.warn("该用户不存在:" + email);
            throw new UserNotExistException("该用户不存在:" + email);
        } else {
            if (user.getPassword().equals(password)) {
                log.info(user.getUsername() + " logged in!");
                return user;
            } else {
                log.warn(user.getUsername() + " failed to log in!");
                throw new InvalidUserInfoException("invalid user info:" + user.getUsername());
            }
        }
    }

    //获得除exceptUid外的所有用户
    @Override
    public List<User> getAllUsersExcept(long exceptUid) {
        List<User> allUsers = userRepository.findAll();
        Optional<User>  userOptional = userRepository.findById(exceptUid);
         userOptional.ifPresent(user -> allUsers.remove(user));
          return allUsers;
    }

    @Override
    public List<User> getAllUsersExcept(User exceptUser) {
        return userRepository.findUsersByUidIsNot(exceptUser.getUid());
    }



    @Override
    public MessageContactVO getContacts(User ownerUser) {
        List<MessageContact> contacts = contactRepository.findMessageContactsByOwnerUidOrderByMidDesc(ownerUser.getUid());
        if (contacts != null) {
            //未读总数
            long totalUnread = 0;

            Object totalUnreadObj = redisTemplate.opsForValue().get(ownerUser.getUid() + "_T");
            if (null != totalUnreadObj) {
                totalUnread = Long.parseLong((String) totalUnreadObj);
            }

            final MessageContactVO contactVO = new MessageContactVO(ownerUser.getUid(), ownerUser.getUsername(), ownerUser.getAvatar(), totalUnread);
            contacts.stream().forEach(contact -> {
                Long mid = contact.getMid();

                MessageContent contentVO = contentRepository.findById(mid).orElse(null);

                User otherUser = userRepository.findById(contact.getOtherUid()).orElse(null);


                if (null != contentVO) {
                    long convUnread = 0;
                    Object convUnreadObj = redisTemplate.opsForHash().get(ownerUser.getUid() + "_C", otherUser.getUid());
                    if (null != convUnreadObj) {
                        convUnread = Long.parseLong((String) convUnreadObj);
                    }
                    ContactInfo contactInfo = new ContactInfo(otherUser.getUid(), otherUser.getUsername(), otherUser.getAvatar(), mid, contact.getType(), contentVO.getContent(), convUnread, contact.getCreateTime());
                    contactVO.appendContact(contactInfo);
                }
            });
            return contactVO;
        }
        return null;
    }
}
