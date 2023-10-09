package com.juzzIt.EducationProject.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Entity.Subject;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.SubjectRepository;
import com.juzzIt.EducationProject.ServiceInterface.SubjectServiceInterface;

@Service
public class SubjectServiceImplementation implements SubjectServiceInterface{

	@Autowired
	SubjectRepository subjectRepo;
	
	@Override
	public Responce addSubject(Subject subject) throws Exception {
	   
				Responce responce=new Responce();
				try {
				if(subject.getSubjectName()!=null) {
				
				boolean isExists=subjectRepo.existsBySubjectName(subject.getSubjectName());
				if(isExists) {
					responce.setStatus(false);
				    responce.setMassege("subject with this name is alrady exists");	
				}
				else {
					subject.setSubjectName(subject.getSubjectName().toUpperCase());
					subjectRepo.save(subject);
					responce.setStatus(true);
		            responce.setMassege("subject  added successfully");
				}
				
				}else {
					responce.setMassege("subject name is requied");
					responce.setStatus(false);
				}
				}catch (Exception e) {
					throw new Exception("Getting problem while adding Subject");
				}
		return responce;
	}

	@Override
	public Responce deleteSubject(String subjectId) throws Exception {
				Responce responce=new Responce();
				try {
				Optional<Subject> subject=subjectRepo.findById(subjectId); 
				if(subject.isPresent()) {
					subjectRepo.delete(subject.get());
					responce.setStatus(true);
		         responce.setMassege("subject deleted successfully");	
				}else {
					responce.setStatus(false);
					responce.setMassege("subject with the given id not found");
				}
				}catch (Exception e) {
					throw new Exception("Getting problem while deleting Subject");
				}
				return responce;
	}

	@Override
	public List<Subject> getSubjects() {
		    return	subjectRepo.findAll();
	}

}
