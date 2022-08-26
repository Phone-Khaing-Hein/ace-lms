package com.ai.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ai.lms.entity.Batch;
import com.ai.lms.entity.Course;
import com.ai.lms.entity.User;
import com.ai.lms.service.BatchService;
import com.ai.lms.service.CourseService;
import com.ai.lms.service.UserService;

@Controller
@RequestMapping("admin/batch")
public class BatchController {
	
	@Autowired
	private BatchService service;
	
	@Autowired
	private CourseService cService;
	
	@Autowired
	private UserService sService;
    
	@GetMapping("create")
	public String create() {
		return "batchcreate";
	}
	
	@PostMapping("create")
	public String doCreate(@Validated @ModelAttribute Batch batch, BindingResult bs, @RequestParam int course) {
		if(bs.hasErrors()) {
			return "batchcreate";
		}
		service.save(batch);
		return "redirect:/admin/batch/list";
	}
	
	@GetMapping("list")
	public String list(ModelMap m) {
		m.put("batches", service.findAll());
		return "batchview";
	}
	
	@GetMapping("detail")
	public String detail(@RequestParam int batchId,ModelMap m) {
		m.put("students", sService.findByBatchIdStudent(batchId));
		m.put("teachers", sService.findByBatchIdTeacher(batchId));
		m.put("allTeachers", sService.findAllTeacher());
		return "batchdetail";
	}
	
	@ModelAttribute("batch")
	Batch batch() {
		return new Batch();
	}

	@ModelAttribute("batches")
	List<Batch> batches() {
		return service.findAll();
	}
	
	@ModelAttribute("courses")
    List<Course> courses() {
    	return cService.findAll();
    }
	
	@ModelAttribute("student")
	User student() {
		return new User();
	}
}

