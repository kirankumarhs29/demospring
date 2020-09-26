package org.tool.student;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
	
	
	
	@Autowired
	private StudentRepository tRepo;
	
	@Autowired
	private SubjectRepository sRepo;
	
	@Autowired
	private ResponseMessage resp;
	
	
	
	
	
	
	@GetMapping("/register-student")
	public String check() {
		
		System.out.println("get req");
		return "Got get!!";
	}
	
	
	
	
	
	
	
	@PostMapping("/register/student")
	public ResponseMessage registerStudent(@RequestBody StudentEntity student ) {
		

		
		
		if(! tRepo.existsStudentEntityByEmail(student.getEmail())) {
			
			
			List<SubjectEntity> list  =  new ArrayList<SubjectEntity>();
			for (SubjectEntity s : student.getSubjectList()) {
				SubjectEntity subject = new SubjectEntity();
				subject.setId( s.getName() + java.time.LocalTime.now().toString().replaceAll(":", "").replaceAll("\\.", "") );
				subject.setName( s.getName() );
				subject.setStudent(student);
				list.add(subject);
			}

			student.getSubjectList().clear();
			student.setSubjects(list);
			
			student.setPassword(RandomStringUtils.random(10, true, true));
			
			MailService.send(student.getEmail(), "Registration Successful ", " Your user_id : " + student.getEmail() +  "  password : " + student.getPassword());
			
			tRepo.save(student);
			
			
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
