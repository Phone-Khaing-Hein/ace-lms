package com.ai.lms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sf.jasperreports.engine.JRException;

import com.ai.lms.entity.User;
import com.ai.lms.service.UserService;
import com.ai.lms.service.ReportService;

@SpringBootApplication
@RestController
@RequestMapping
public class AiFinalLmsApplication {


    @Autowired
	private UserService userService;
	@Autowired
	private ReportService service;

	@GetMapping("/report/{format}")
	public  void generateReport(@PathVariable String format,HttpServletResponse response) throws JRException, IOException{
            service.exportReport(format,response);
	}

	@GetMapping("/getStudentListByBatchId")
	public List<User> getStudentData(){
		return userService.findByBatchIdStudent(1);
	}

    public static void main(String[] args) {
        SpringApplication.run(AiFinalLmsApplication.class, args);
    }

}
