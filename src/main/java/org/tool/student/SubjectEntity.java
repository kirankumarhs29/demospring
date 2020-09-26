package org.tool.student;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students_subjects")
public class SubjectEntity {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private StudentEntity student_id;

	
	
	
	public SubjectEntity() {
	}
	
	
	public SubjectEntity(String name) {
		this.name = name;
	}

	public SubjectEntity(String id, String name, StudentEntity student_id) {
		this.id = id;
		this.name = name;
		this.student_id = student_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentEntity getStudent() {
		return student_id;
	}

	public void setStudent(StudentEntity student_id) {
		this.student_id = student_id;
	}


	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", name=" + name + ", student_id=" + student_id + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
