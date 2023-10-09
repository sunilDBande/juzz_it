package com.juzzIt.EducationProject.Models;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.Entity.Entities;


@Component
public class UniqueIdGenerations implements Serializable{

	@Autowired
	private EntityDao entityDao;
	
	@Autowired
	private Responce reponse;
	
	public HashMap<String, Object> uniqueIdGeneration(HashMap<String, Object> data){
		
		List<Entities> returnCourseName = entityDao.returnCourseName(data);
		String subName = null;
		long count = 0;
		if(!returnCourseName.isEmpty()) {
			System.out.println("566555555555555555555555555555555555555555555555555");
			Entities entities = returnCourseName.get(0);
			 subName = entities.getEntity_SubName();
			 count = entities.getEntity_Count();
			 System.out.println("count--> "+count);
			 count++;
			 System.out.println("count--> "+count);
			 data.put("count", count);
			 System.out.println("data--> "+data);
			 System.out.println("data.gr--> "+data.get("count"));
		}

		return data;
	}
}
