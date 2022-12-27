package com.application.repository;


import com.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.QueryHint;


@Repository
public interface UserRepository   extends JpaRepository<User, Long> {
    @Query(value = "select * from users where email =?1", nativeQuery = true)
    User getUserByEmail(String email);



}
