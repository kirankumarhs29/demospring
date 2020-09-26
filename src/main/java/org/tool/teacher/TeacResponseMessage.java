package org.tool.teacher;

import org.springframework.stereotype.Component;

@Component
public class TeacResponseMessage {
	
	private String Status;
	
	private String message;
	
	
	
	
	public TeacResponseMessage() {
	}



	public TeacResponseMessage(String status, String message) {
		Status = status;
		this.message = message;
	}
	
	

	public String getStatus() {
		return Status;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	
	
	

}
