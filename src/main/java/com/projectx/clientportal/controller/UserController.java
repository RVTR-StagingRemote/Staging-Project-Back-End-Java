package com.projectx.clientportal.controller;

import com.projectx.clientportal.model.JsonResponse;
import com.projectx.clientportal.model.User;
import com.projectx.clientportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userController")
@RequestMapping(value = "api")
@CrossOrigin(value = CrossOriginUtil.CROSS_ORIGIN_VALUE, allowCredentials = "true")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public JsonResponse createUser(@RequestBody User user) {
        JsonResponse jsonResponse;
        User newUser = this.userService.createUser(user);
        if(newUser != null)
            jsonResponse = new JsonResponse(true, "User successfully created", newUser);
        else
            jsonResponse= new JsonResponse(false, "Email entered already exists", null);
        return jsonResponse;
    }

}
