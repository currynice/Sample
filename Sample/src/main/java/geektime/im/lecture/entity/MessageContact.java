package geektime.im.lecture.entity;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

/**
 * 联系人表
 */
@Entity
@Table(name = "IM_MSG_CONTACT")
@IdClass(ContactMultiKeys.class)
@Getter
@Setter
@ToString
public class MessageContact {
    @Id
    private Long ownerUid;
    @Id
    private Long otherUid;
    private Long mid;
    private Integer type;

    private Date createTime;


}
