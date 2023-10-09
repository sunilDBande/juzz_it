package com.juzzIt.EducationProject.ServiceInterface;

import java.util.List;

import com.juzzIt.EducationProject.Entity.Subject;
import com.juzzIt.EducationProject.Models.Responce;

public interface SubjectServiceInterface {

	
	public Responce addSubject(Subject subject) throws Exception;
	public List<Subject> getSubjects();
	public Responce  deleteSubject(String subjectId) throws Exception;
}
