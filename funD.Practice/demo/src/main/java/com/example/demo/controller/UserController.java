package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;
import com.example.demo.vo.Result;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public Result getUserListPage(){
        Result result = new Result();

        result.setData(userService.getAllUserList());

        return result;
    }

    @PostMapping("/user/details")
    @ResponseBody
    public Result getUserPage(@RequestBody Map<String, String> params){
        Result result = new Result();
        String userId = params.get("userId");
        String password = params.get("password");

        result.setData(userService.getUser(userId, password));

        return result;
    }

    @PostMapping("/user/add")
    @ResponseBody
    public Result addUserPage(@RequestBody Map<String, String> params){
        Result result = new Result();

        String userId = params.get("userId");
        String password = params.get("password");
        String name = params.get("name");

        result.setData(userService.addUser(userId, password, name));

        return result;
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
    public Result deleteUserPage(@RequestBody Map<String, String> params){
        Result result = new Result();
        String id = params.get("id");

        userService.deleteUser(id);
        result.setData("success");

        return result;
    }
}
