package com.clone.insta.controller;

import com.clone.insta.model.Follow;
import com.clone.insta.model.User;
import com.clone.insta.repository.FollowRepository;
import com.clone.insta.repository.UserRepository;
import com.clone.insta.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/follow/follower/{id}")
    public String followFollower(@PathVariable int id, @AuthenticationPrincipal MyUserDetail userDetail, Model model){
        // follower list
        List<Follow> followers = followRepository.findByToUserId(id);

        // follow list
        List<Follow> principalFollows = followRepository.findByFromUserId(userDetail.getUser().getId());

        for(Follow f1 : followers){
            for(Follow f2 : principalFollows){
                if(f1.getFromUser().getId() == f2.getToUser().getId())
                    f1.setFollowState(true); // 맞팔 설정
            }
        }

        model.addAttribute("followers", followers);
        return "follow/follower";
    }

    @GetMapping("/follow/follow/{id}")
    public String followFollow(@PathVariable int id, @AuthenticationPrincipal MyUserDetail userDetail, Model model){
        // 굳이 비슷하게 메소드를 두개 나눌 필요가 있나? 일단 구현해보고 작동되는 원리를 확인하자. 그리고 고쳐보자.

        // follow list
        List<Follow> follows = followRepository.findByFromUserId(id);

        // follow list
        List<Follow> principalFollows = followRepository.findByFromUserId(userDetail.getUser().getId());

        for(Follow f1 : follows){
            for(Follow f2 : principalFollows){
                if(f1.getFromUser().getId() == f2.getToUser().getId())
                    f1.setFollowState(true);
            }
        }

        model.addAttribute("follows", follows);
        return "follow/follow";
    }
}
