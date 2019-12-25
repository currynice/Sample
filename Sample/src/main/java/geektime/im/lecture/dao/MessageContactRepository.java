package geektime.im.lecture.dao;

import geektime.im.lecture.entity.ContactMultiKeys;
import geektime.im.lecture.entity.MessageContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageContactRepository extends JpaRepository<MessageContact, ContactMultiKeys> {

    //where owner_uid = ? order by mid desc
    //指定人员的联系人列表
    List<MessageContact> findMessageContactsByOwnerUidOrderByMidDesc(Long ownerUid);
}
