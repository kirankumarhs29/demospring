package org.tool.student;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, String> {
	
	
	

}
