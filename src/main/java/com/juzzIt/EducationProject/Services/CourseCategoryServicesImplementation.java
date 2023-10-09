package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseCategoryDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseCategory;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseCategoryServicesInterface;

@Service
public class CourseCategoryServicesImplementation implements CourseCategoryServicesInterface{
@Autowired
private CourseCategoryDaoInterface courseCategoryDaoInterface;
	
     @Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;
	
	@Override
	public Responce addCategory(HashMap<String, Object> category) throws Exception {
		
		Responce responce=new Responce();
		try {
		if(category.get("category_Name")==null) {		
			responce.setMassege("category name is needed");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(courseCategoryDaoInterface.existsByCategoryName(category.get("category_Name").toString())) {
			responce.setMassege("category with the name is alredy added");
			responce.setStatus(false);
			return responce;
		}
		
		CourseCategory newCategory=new CourseCategory();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "CourseCategory");
		data.put("Entity_SubName", "COURCATG");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		System.out.println("IMP---> " + uniqueIdGeneration);
		System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newCategory.setCategoryId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newCategory.setCategoryId(subName + "0" + count);
		} else {
			newCategory.setCategoryId(subName + "" + count);
		}
		// *******
		newCategory.setCategoryName(category.get("category_Name").toString());
		
		
		CourseCategory addedCategory = courseCategoryDaoInterface.addCategory(newCategory);
		
		if(addedCategory==null) {
			
			responce.setMassege("failed to add the category");
			responce.setStatus(false);
			return responce;
		} else {
			 entityDao.updateCountForCourseTable(data);		
		}
		
		responce.setMassege("category added successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Category");
		}
		return responce;
		
	}


	@Override
	public Responce deleteCategory(String categoryId) throws Exception {
		// TODO Auto-generated method stub
		return courseCategoryDaoInterface.deleteCategory(categoryId);
	}


	@Override
	public List<Map<String, Object>> getAllCategory() throws Exception {
		// TODO Auto-generated method stub
		return courseCategoryDaoInterface.getCategorys();
	}
	
	
}
