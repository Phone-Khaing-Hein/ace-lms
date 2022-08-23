package com.ai.lms.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ai.lms.entity.Course;
import com.ai.lms.entity.Resource;
import com.ai.lms.service.CourseService;
import com.ai.lms.service.ModuleService;
import com.ai.lms.service.ResourceService;
import com.ai.lms.entity.Module;

@Controller
@RequestMapping("admin/module")
public class ModuleController {
	
	@Autowired
	private CourseService cService;
	
	@Autowired
	private ModuleService service;
	
	@Autowired
	private ResourceService rService;
	
	@GetMapping("create")
    public String getCreate(ModelMap m){
    	m.put("module", new Module());
        return "coursecreate";
    }
	
	@PostMapping("create")
	public String create(@Validated @ModelAttribute Module module, BindingResult bs, @RequestParam List<MultipartFile> resources, RedirectAttributes attr, ModelMap map) throws IllegalStateException, IOException {
		
		if(bs.hasErrors()) {
			return "coursecreate";
		}
		var m = service.findByName(module.getName());
		if(m != null) {
			map.put("merror", "%s module has already existed!");
			return "coursecreate";
		}
		var moduleId = service.save(module);
		for(var file : resources) {
			var prefixName = file.getOriginalFilename().split("\\.")[0];
			var suffixName = file.getOriginalFilename().split("\\.")[1];
			var fileName = prefixName.concat("-").concat(String.valueOf(Math.random() * 100000)).concat(".").concat(suffixName);
			
			uploadFile(file, fileName);
			var resource = new Resource();
			resource.setResource(fileName);
			resource.getModule().setId(moduleId);
			rService.save(resource);
		}
		attr.addFlashAttribute("mmessage", "%s module created successfully!".formatted(module.getName()));
		return "redirect:/admin/course/create";
	}
	
	public void uploadFile(MultipartFile file, String posterName) throws IllegalStateException, IOException {
		file.transferTo(
				new File("D:\\ai-lms-final\\src\\main\\resources\\static\\resource\\%s".formatted(posterName)));
	}

	public boolean deleteFile(String poster) {
		File fileToDelete = new File(
				"D:\\ai-lms-final\\src\\main\\resources\\static\\resource\\%s".formatted(poster));
		return fileToDelete.delete();
	}

	 @ModelAttribute("course")
	 Course course() {
		 return new Course();
	 }
	    
	 @ModelAttribute("courses")
	 List<Course> courses() {
	  	return cService.findAll();
	 }
}
