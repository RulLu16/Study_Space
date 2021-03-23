package com.example.demo.controller;

import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;
import com.example.demo.vo.Result;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity getUserListPage(){
        /*Result result = new Result();

        result.setData(userService.getAllUserList());*/
        CollectionModel<User> userResource = CollectionModel.of(userService.getAllUserList());
        /*List<User> userList = userService.getAllUserList();
        for(User user : userList) {
            EntityModel<User> resource = UserResource.selfModel(user);
            userResource.add(linkTo(UserController.class).withRel("users"));
        }*/
        userResource.add(linkTo(UserController.class).withRel("users"));

        return ResponseEntity.ok(userResource);
        //return result;
    }

    @PostMapping("/user/details")
    @ResponseBody
    public ResponseEntity getUserPage(@RequestBody Map<String, String> params){
        /*Result result = new Result();

        result.setData(userService.getUser(userId, password));
*/
        String userId = params.get("userId");
        String password = params.get("password");

        User user = userService.getUser(userId, password);
        EntityModel<User> userResource = UserResource.selfModel(user);

        return ResponseEntity.ok(userResource);
        //return result;
    }

    @PostMapping("/user/add")
    @ResponseBody
    public ResponseEntity addUserPage(@RequestBody Map<String, String> params){
        /*Result result = new Result();
        result.setData(userService.addUser(userId, password, name));
        */
        String userId = params.get("userId");
        String password = params.get("password");
        String name = params.get("name");

        // 자기 자신 유저 링크 추가
        User user = userService.addUser(userId, password, name);
        URI newUri = linkTo(UserController.class).slash(user.getId()).toUri();

        // 전체 유저 링크 추가
        EntityModel<User> userResource = UserResource.selfModel(user);
        userResource.add(linkTo(UserController.class).withRel("query-users"));

        //return result;
        return ResponseEntity.created(newUri).body(userResource);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public Result updateUserPage(@RequestBody Map<String, String> params){
        Result result = new Result();

        Long id = Long.parseLong(params.get("id"));
        String userId = params.get("userId");
        String password = params.get("password");
        String name = params.get("name");

        result.setData(userService.updateUser(id, userId, password, name));

        return result;
    }

    @PostMapping("/user/delete")
    @ResponseBody
    public ResponseEntity deleteUserPage(@RequestBody Map<String, String> params){
        //Result result = new Result();
        String id = params.get("id");

        userService.deleteUser(id);
        //result.setData("success");

        return ResponseEntity.ok().build();
        //return result;
    }

    @GetMapping("/user/tag")
    @ResponseBody
    public List<Tag> getUserTag(@RequestBody Map<String, String> params){
        String id = params.get("id");

        return userService.getTag(Long.parseLong(id));
    }

    @PostMapping("/user/tag")
    @ResponseBody
    public List<Tag> addUserTag(@RequestBody Map<String, String> params){
        String id = params.get("id");
        String tags = params.get("tags");

        return userService.addTag(Long.parseLong(id), tags);
    }
}
