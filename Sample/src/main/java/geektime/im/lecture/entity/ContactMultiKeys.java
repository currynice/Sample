package geektime.im.lecture.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.io.Serializable;

/**
 * Contact联系人表联合主键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMultiKeys implements Serializable {

    protected Long ownerUid;
    protected Long otherUid;



}