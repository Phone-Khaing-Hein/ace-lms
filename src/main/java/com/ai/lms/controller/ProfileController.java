package com.ai.lms.controller;


import com.ai.lms.entity.User;
import com.ai.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/change-password")
    public String profile(){
        return "PRF01";
    }

    @PostMapping("/change-password")
    public String changeProfile(@RequestParam("oldpassword") String oldPassword, @RequestParam("newpassword") String newPassword, Principal principal, ModelMap model){


        User user = userRepository.findByLoginId(principal.getName());

        if (bCryptPasswordEncoder.matches(oldPassword,user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
        }

        return "redirect:/change-password";
    }
}
