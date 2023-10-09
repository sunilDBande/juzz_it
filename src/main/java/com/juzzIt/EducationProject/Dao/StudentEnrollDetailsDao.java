package com.juzzIt.EducationProject.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.StudentEnrollDetailsDaoInterface;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Repositary.StudentEnrollDetailsRepository;
@Component
public class StudentEnrollDetailsDao  implements StudentEnrollDetailsDaoInterface{

	@Autowired
	private StudentEnrollDetailsRepository studentEnrollDetailsRepo;
	
	
	@Override
	public StudentEnrollDetails addStudentDetailes(StudentEnrollDetails studentEnrollDetails) {
		// TODO Auto-generated method stub
		return studentEnrollDetailsRepo.save(studentEnrollDetails);
	}

	@Override
	public boolean deletedEnrollDetails(StudentEnrollDetails studentEnrollDetails) {
		
		try {
		studentEnrollDetailsRepo.delete(studentEnrollDetails);
		return true;
		}catch (Exception e) {
			
		}
		return false;
	
	}

}
