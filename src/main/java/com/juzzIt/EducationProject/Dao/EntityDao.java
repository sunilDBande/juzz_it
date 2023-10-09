package com.juzzIt.EducationProject.Dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.juzzIt.EducationProject.Entity.Entities;



public interface EntityDao{

	public List<Entities> returnCourseName(HashMap<String, Object> data);
	
	public void updateCountForCourseTable(HashMap<String, Object> data);
}
