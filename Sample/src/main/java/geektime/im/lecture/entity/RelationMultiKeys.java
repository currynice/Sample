package geektime.im.lecture.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;


/**
 * 关系索引表的联合主键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationMultiKeys implements Serializable {

    protected Long mid;
    protected Long ownerUid;


}