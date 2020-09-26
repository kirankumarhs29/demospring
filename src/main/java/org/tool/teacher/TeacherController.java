package org.tool.teacher;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeacherController {
	
	
	
	@Autowired
	private TeacherRepository tRepo;
	
	@Autowired
	private TeacSubjectRepository sRepo;
	
	@Autowired
	private TeacResponseMessage resp;
	
	
	
	
	
	
	@GetMapping("/register-teacher")
	public String check() {
		
		System.out.println("get req");
		return "Got get!!";
	}
	
	
	
	
	
	
	
	@PostMapping("/register/teacher")
	public TeacResponseMessage registerTeacher(@RequestBody TeacherEntity teacher ) {
		

		teacher.setTeacher_id(Integer.parseInt(java.time.LocalTime.now().toString().replaceAll(":", "").replaceAll("\\.", "")));
		
		if(! tRepo.existsTeacherEntityByEmail(teacher.getEmail())) {
			
			
			List<TeacSubjectEntity> list  =  new ArrayList<TeacSubjectEntity>();
			for (TeacSubjectEntity s : teacher.getSubjectList()) {
				TeacSubjectEntity subject = new TeacSubjectEntity();
				subject.setId( s.getName() + java.time.LocalTime.now().toString().replaceAll(":", "").replaceAll("\\.", "") );
				subject.setName( s.getName() );
				subject.setTeacher(teacher);
				list.add(subject);
			}

			teacher.getSubjectList().clear();
			teacher.setSubjects(list);
			
			teacher.setPassword(RandomStringUtils.random(10, true, true));
			
			MailService2.send(teacher.getEmail(), "Registration Successful ", " Your user_id : " + teacher.getEmail() +  "  password : " + teacher.getPassword());
			
			tRepo.save(teacher);
			
			
			resp.setStatus("success");
			resp.setMessage("Your User ID and Password are sent to your mail. Please use them to login and change password for successful registration.");
			return  resp;
			
		}else {
			
			resp.setStatus("failure");
			resp.setMessage("User already registered. Please register with a different email or click forgot password button to reset your password.");
			return resp;
		}
	
		
		
	}
	
	
	 
	
	
	

}
