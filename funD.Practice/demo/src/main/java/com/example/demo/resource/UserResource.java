package com.example.demo.resource;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class UserResource extends EntityModel<User> {

    public static EntityModel<User> selfModel(User user){
        // 셀프 링크 생성
        EntityModel<User> userResource = EntityModel.of(user);
        userResource.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());

        return userResource;
    }
}
