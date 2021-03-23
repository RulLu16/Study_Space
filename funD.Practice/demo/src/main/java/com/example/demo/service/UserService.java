package com.example.demo.service;

import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    public List<User> getAllUserList(){
        return userRepository.findAll();
    }

    public User getUser(String userId, String password){

        return userRepository.findByUserInfo(userId, password);
    }

    public User addUser(String userId, String password, String name){
        User user = new User();

        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);

        return userRepository.save(user);
    }

    public int updateUser(Long id, String userId, String password, String name){

        return userRepository.updateByUserInfo(id, userId, password, name);
    }

    public void deleteUser(String id){
        userRepository.deleteById(Long.parseLong(id));
    }

    public List<Tag> getTag(Long id){
        Optional<User> user = userRepository.findById(id);
        return tagRepository.findByUser(user.get());
    }

    public List<Tag> addTag(Long id, String tags){
        List<Tag> resultTag = new ArrayList<>();
        String[] taglist = tags.split(", ");
        Optional<User> user = userRepository.findById(id);

        for(String tag_cont : taglist){
            Tag tag = new Tag();
            tag.setTag_contents(tag_cont);
            tag.setUser(user.get());

            resultTag.add(tagRepository.save(tag));
        }

        return resultTag;
    }
}
