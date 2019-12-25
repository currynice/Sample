package geektime.im.lecture.dao;

import geektime.im.lecture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // .... WHERE email= ?
    User findByEmailEquals(String email);

    //... WHERE uid <>= ?
    List<User> findUsersByUidIsNot(long uid);

}
