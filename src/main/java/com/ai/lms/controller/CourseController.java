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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ai.lms.entity.Course;
import com.ai.lms.service.CourseService;
import com.ai.lms.service.ModuleService;
import com.ai.lms.service.ResourceService;
import com.ai.lms.entity.Module;

@Controller
@RequestMapping("admin/course")
public class CourseController {

    @Autowired
    private CourseService service;
    
    @Autowired
    private ModuleService mService;
    
    @Autowired
    private ResourceService rService;
    
    @GetMapping("create")
    public String getCreate(){
        return "coursecreate";
    }

    @PostMapping("create")
    public String postCreate(@Validated @ModelAttribute Course course, BindingResult bs, RedirectAttributes attr, ModelMap m){
        if(bs.hasErrors()){
            return "coursecreate";
        }
        var c = service.findByName(course.getName());
        if(c != null) {
        	m.put("error", "%s course has already existed!".formatted(course.getName()));
        	return "coursecreate";
        }
        service.save(course);
        attr.addFlashAttribute("cmessage", "%s course created successfully!".formatted(course.getName()));
        return "redirect:/admin/course/create";
    }
    
    @GetMapping("list")
    public String list(ModelMap m) {
    	return "courselist";
    }
    
    @GetMapping("detail")
    public String detail(ModelMap m, @RequestParam int courseId) {
    	var modules = mService.findByCourseId(courseId);
    	for(var module : modules) {
    		var count = rService.findResourceCount(module.getId());
    		module.setResourceCount(count);
    	}
    	m.put("modules", modules);
    	m.put("courseName", service.findById(courseId).getName());
    	return "admin-coursemodules";
    }
    
    @ModelAttribute("course")
    Course course() {
    	return new Course();
    }
    
    @ModelAttribute("courses")
    List<Course> courses() {
    	return service.findAll();
    }
    
    @ModelAttribute("module")
    Module module() {
    	return new Module();
    }
}

