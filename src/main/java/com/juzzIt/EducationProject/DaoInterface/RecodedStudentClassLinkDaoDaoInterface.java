package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecodedStudentClassLinks;
import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecodedStudentClassLinkDaoDaoInterface {

	
public RecodedStudentClassLinks addRecodedStudentClassLinks(RecodedStudentClassLinks recodedStudentClassLinks);
public RecodedStudentClassLinks getRecodedStudentClassLinksById(String classLinkId);
public Responce deleteClassLink(String classLinkId);
public List<Map<String, Object>> getAllRecodedClassLinks(RecordedStudent recordedStudent);


}
