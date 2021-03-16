package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userId = ?1 and u.password = ?2")
    public User findByUserInfo(String userId, String password);

    @Modifying
    @Transactional
    @Query("update User u set u.userId = ?2, u.password = ?3, u.name = ?4 where u.id = ?1")
    public int updateByUserInfo(Long id, String userId, String password, String name);
}
