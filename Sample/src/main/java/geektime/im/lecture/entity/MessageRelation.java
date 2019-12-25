package geektime.im.lecture.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 消息关系索引表
 */
@Entity
@Table(name = "IM_MSG_RELATION")
@IdClass(RelationMultiKeys.class)
@Getter
@Setter
public class MessageRelation {

    @Id
    private Long mid;
    @Id
    private Long ownerUid;

    //0:一条sender的发件箱索引   1:一条recipient的收件箱索引
    private Integer type;


    private Long otherUid;
    private Date createTime;


}
