package geektime.im.lecture.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
/**
 * 联系人信息
 */
@Getter
@AllArgsConstructor
public class ContactInfo {
    private Long otherUid;
    private String otherName;
    private String otherAvatar;
    private Long mid;
    private Integer type;
    private String content;
    private Long convUnread;
    private Date createTime;

}
