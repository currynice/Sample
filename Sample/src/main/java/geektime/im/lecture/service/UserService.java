package geektime.im.lecture.service;

import geektime.im.lecture.entity.User;
import geektime.im.lecture.vo.MessageContactVO;

import java.util.List;

public interface UserService {

    //登录
    User login(String email, String password);

    //获得除exceptUid外的所有用户
    List<User> getAllUsersExcept(long exceptUid);

    //获得除exceptUser外的所有用户
    List<User> getAllUsersExcept(User exceptUser);

    //获得某用户联系人列表信息
    MessageContactVO getContacts(User ownerUser);
}
