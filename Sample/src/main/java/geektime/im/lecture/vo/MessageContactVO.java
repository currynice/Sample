package geektime.im.lecture.vo;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 某用户联系人列表信息
 */
@Getter
public class MessageContactVO {
    private Long ownerUid;
    private String ownerAvatar;
    private String ownerName;
    private Long totalUnread;
    private List<ContactInfo> contactInfoList;

    public MessageContactVO(Long ownerUid, String ownerName, String ownerAvatar, Long totalUnread) {
        this.ownerUid = ownerUid;
        this.ownerAvatar = ownerAvatar;
        this.ownerName = ownerName;
        this.totalUnread = totalUnread;
    }


    //只能通过该方法设置 联系人信息
    public void setContactInfoList(List<ContactInfo> contactInfoList) {
        this.contactInfoList = contactInfoList;
    }


    //添加联系人
    public void appendContact(ContactInfo contactInfo) {
       if (contactInfoList.isEmpty()){
           contactInfoList = new ArrayList<>();
       }
        contactInfoList.add(contactInfo);
    }

}
