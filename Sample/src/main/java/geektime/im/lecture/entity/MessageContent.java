package geektime.im.lecture.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 消息内容
 */
@Entity
@Table(name = "IM_MSG_CONTENT")
@Getter
@Setter
public class MessageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mid;
    private Long senderId;
    private Long recipientId;
    private String content;
    private Integer msgType;
    private Date createTime;

}
