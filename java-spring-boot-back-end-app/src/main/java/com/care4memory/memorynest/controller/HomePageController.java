package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.UserRoleDTO;
import com.care4memory.memorynest.service.UserRoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController is not working with redirect, so using @Controller
@Controller
public class HomePageController {

    @Value("${memory-nest.app.front-end.base_url}")
    private String frontEndBaseUrl;

    private final UserRoleService userRoleService;

    public HomePageController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/home")
    public String home() {
        try {
            UserRoleDTO userRoleDTO = userRoleService.getUserInfo();
            if(userRoleDTO != null) {
                return "redirect:"+this.frontEndBaseUrl+"/home";
            }
        } catch (Exception e) {
            System.out.println("User not found in our database");
        }
        return "redirect:"+this.frontEndBaseUrl+"/not_registered";
    }

}
