package com.ai.lms.service;

import com.ai.lms.entity.Batch;
import com.ai.lms.entity.User;
import com.ai.lms.entity.User.Role;
import com.ai.lms.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("MyUserDetailsServiceImpl")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Resource
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    @Transactional
    @EventListener(classes = ContextRefreshedEvent.class)
    public void initializeUser() {
        if(userRepo.count() == 0) {
            var user = new User();
            user.setLoginId("admin");
            user.setName("Admin User");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(User.Role.Admin);
            userRepo.save(user);

            var teacher = new User();
            teacher.setLoginId("TCH001");
            teacher.setName("May Zwei");
            teacher.setEmail("teacher@gmail.com");
            teacher.setPassword(passwordEncoder.encode("teacher"));
            teacher.setRole(User.Role.Teacher);
            userRepo.save(teacher);

            var student = new User();
            student.setLoginId("STU001");
            student.setName("Swam Thu Marn");
            student.setEmail("swamthumarn@gmail.com");
            student.setPassword(passwordEncoder.encode("student"));
            student.setRole(User.Role.Student);
            userRepo.save(student);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByLoginId(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
          return   org.springframework.security.core.userdetails.User
                    .withUsername(user.getLoginId())
                    .password(user.getPassword())
                    .authorities(user.getRole().name())
            .build();
        }
    }

	public void studentCreate(User user, int batchId) {
		user.setRole(Role.Student);
		user.setPassword(passwordEncoder.encode(user.getEmail()));
		user.setBatch(new Batch());
		user.getBatch().setId(batchId);
		userRepo.save(user);
	}
	
	public List<User> findByBatchIdStudent(int batchId) {
		return userRepo.findByBatchId(batchId).stream().filter(a -> a.getRole().equals(Role.Student)).collect(Collectors.toList());
	}

    public List<User> findStudentByBatchIdAndStatus(int batchId, int status){
        return userRepo.findByBatchIdAndStatus(batchId, status).stream().filter(a -> a.getRole().equals(Role.Student)).collect(Collectors.toList());
    }
	
	public List<User> findByBatchIdTeacher(int batchId) {
		return userRepo.findByBatchId(batchId).stream().filter(a -> a.getRole().equals(Role.Teacher)).collect(Collectors.toList());
	}

	public List<User> findAllTeacher() {
		return userRepo.findAll().stream().filter(a -> a.getRole().equals(Role.Teacher)).collect(Collectors.toList());
	}

	public void createTeacher(User teacher) {
		teacher.setRole(Role.Teacher);
		teacher.setPassword(passwordEncoder.encode(teacher.getEmail()));
		userRepo.save(teacher);
	}
	
	public void createStudent(User student) {
		student.setRole(Role.Student);
		student.setPassword(passwordEncoder.encode(student.getEmail()));
		userRepo.save(student);
	}

    public User findByStudentId(String stuId){
        return userRepo.findByLoginId(stuId);
    }

    public boolean stuIdExists(String id){
        return userRepo.existsById(id);
    }

}
