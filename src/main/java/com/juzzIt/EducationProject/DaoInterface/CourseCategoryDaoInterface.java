package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.CourseCategory;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseCategoryDaoInterface {

	
	
	public CourseCategory addCategory(CourseCategory category);
	
	public Responce  deleteCategory(String categoryId) throws Exception;
	
	public List<Map<String , Object>> getCategorys() throws Exception;
	
	public boolean existsByCategoryName(String cataegoryName);
}
