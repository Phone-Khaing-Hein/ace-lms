package com.ai.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ai.lms.entity.Batch;
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
    public String teacherCreate() {
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
    
    @GetMapping("student/create")
    public String studentCreate() {
    	return "teachercreate";
    }
    
    @PostMapping("student/create")
    public String studentCreate(@Validated @ModelAttribute("student") User student, BindingResult bs, @RequestParam int batchId, ModelMap m) {
    	if(bs.hasErrors()) {
    		m.put("students", service.findByBatchIdStudent(batchId));
    		return "batchdetail";
    	}
        System.out.println(batchId);
        // else if(service.stuIdExists(student.getLoginId())){
        //     return "redirect:/admin/batch/detail?batchId=%d".formatted(batchId);
        // }
        System.out.println(student.getLoginId());
    	student.setBatch(new Batch());
    	student.getBatch().setId(batchId);
    	service.createStudent(student);
    	return "redirect:/admin/batch/detail?batchId=%d".formatted(batchId);
    }
    
    @GetMapping("/student/delete/{userId}")
    public String deleteStudent(@PathVariable("userId")String studentId){
        User user = service.findByStudentId(studentId);
        int batchId = user.getBatch().getId();
        user.setStatus(1);
        service.saveUser(user);
        System.out.println(user.getStatus());
        // if(service.removeStudent(user.getLoginId())){
        //     System.out.println("Deleted");
        // }
        return "redirect:/admin/batch/detail?batchId=%d".formatted(batchId);
    }

    @ModelAttribute("teacher")
    User teacher() {
    	return new User();
    }
    
}
