package com.clone.insta.controller;

import com.clone.insta.model.User;
import com.clone.insta.repository.FollowRepository;
import com.clone.insta.repository.UserRepository;
import com.clone.insta.service.MyUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller // ㅇㄴ.. controller 추가 안해놓고 왜 getmapping 못하나 찾고있었다..
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @GetMapping("/auth/login")
    public String authLogin(){
        return "auth/login";
    }

    @GetMapping("/auth/join")
    public String authJoin(){
        return "auth/join";
    }

    @PostMapping("/auth/joinProc")
    // join.jsp에서 post로 로그인 정보를 보내주면,
    public String authJoinProc(User user){
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);

        log.info("raw password: " + rawPassword);
        log.info("enc password: " + encPassword);

        userRepository.save(user);

        // 로그인 화면으로 전환
        return "redirect:/auth/login";
    }

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, @AuthenticationPrincipal MyUserDetail userDetail, Model model){
        // id를 통해 유저 검색

        Optional<User> oToUser = userRepository.findById(id);
        User user = oToUser.get();
        model.addAttribute("user", user);

        // follow count
        int followCount = followRepository.countByFromUserId(user.getId());
        model.addAttribute("followCount", followCount);

        // follower count
        int followerCount = followRepository.countByToUserId(user.getId());
        model.addAttribute("followerCount", followerCount);

        // follow check, 1 = folow, 0 = unfollow
        User principal = userDetail.getUser();
        int followCheck = followRepository.countByFromUserIdAndToUserId(principal.getId(), id);

        log.info("followCheck: " + followCheck);
        model.addAttribute("followCheck", followCheck);

        return "user/profile";
    }

    @GetMapping("/user/edit/{id}")
    public String userEdit(@PathVariable int id){

        return "user/profile_edit";
    }
}
