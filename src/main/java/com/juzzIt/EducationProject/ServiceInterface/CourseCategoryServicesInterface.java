package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseCategoryServicesInterface {

	public Responce addCategory( HashMap<String, Object> category) throws Exception;
	public Responce deleteCategory(String categoryId) throws Exception;
	public List<Map<String , Object>>  getAllCategory() throws Exception;
}
