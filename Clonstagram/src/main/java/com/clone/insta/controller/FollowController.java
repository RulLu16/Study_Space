package com.clone.insta.controller;

import com.clone.insta.model.Follow;
import com.clone.insta.model.User;
import com.clone.insta.repository.FollowRepository;
import com.clone.insta.repository.UserRepository;
import com.clone.insta.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/follow/{id}")
    public @ResponseBody
    String follow(@AuthenticationPrincipal MyUserDetail userDetail, @PathVariable int id){
        User fromUser = userDetail.getUser();
        Optional<User> Ouser = userRepository.findById(id);
        User toUser = Ouser.get();

        Follow follow = new Follow();
        follow.setFromUser(fromUser);
        follow.setToUser(toUser);

        followRepository.save(follow);

        return "ok";
    }

    @DeleteMapping("/follow/{id}")
    public @ResponseBody String unFollow(@AuthenticationPrincipal MyUserDetail userDetail, @PathVariable int id) {
        User fromUser = userDetail.getUser();
        Optional<User> oToUser = userRepository.findById(id);
        User toUser = oToUser.get();

        followRepository.deleteByFromUserIdAndToUserId(fromUser.getId(), toUser.getId());

        List<Follow> follows = followRepository.findAll();
        return "ok";
    }
}
