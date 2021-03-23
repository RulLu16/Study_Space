package com.example.demo.repository;

import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public List<Tag> findByUser(User user);
}
