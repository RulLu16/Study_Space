package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
}
