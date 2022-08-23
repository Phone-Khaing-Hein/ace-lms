package com.ai.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.lms.entity.User;
import com.ai.lms.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserService service;

    @GetMapping("home")
    public String home(){
        return "ADB01";
    }
    
    @GetMapping("teacher/create")
    public String studentCreate() {
    	return "teachercreate";
    }
    
    @PostMapping("teacher/create")
    public String teacherCreate(@Validated @ModelAttribute("teacher") User teacher, BindingResult bs) {
    	if(bs.hasErrors()) {
    		return "teachercreate";
    	}
    	service.createTeacher(teacher);
    	return "redirect:/admin/teacher/create";
    }
    
    @ModelAttribute("teacher")
    User teacher() {
    	return new User();
    }
    
}
