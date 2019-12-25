package geektime.im.lecture.dao;


import geektime.im.lecture.entity.MessageContact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageContactRepositoryTest {
    @Autowired
    private MessageContactRepository contactRepository;


    @Test
    public void findMessageContactsByOwnerUidOrderByMidDesc() {
        List<MessageContact> messageContactList =  contactRepository.findMessageContactsByOwnerUidOrderByMidDesc(1000L);
        Assert.assertFalse(messageContactList.isEmpty());
        messageContactList.forEach(contact->System.out.println(contact.toString()));
    }
}
