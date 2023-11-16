package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BlogsDetailsDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BlogsDetailsRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class BlogsDetailsDao  implements BlogsDetailsDaoInterface{

	@Autowired
	private BlogsDetailsRepositary blogsDetailsRepositary;
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public BlogsDetails addBlogDetails(BlogsDetails blogsDetails) {
		return blogsDetailsRepositary.save(blogsDetails);
	}

	@Override
	public BlogsDetails getBlogsDetailsById(String blogsDetailsId) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<BlogsDetails> createQuery = criteriaBuilder.createQuery(BlogsDetails.class);
	Root<BlogsDetails> root = createQuery.from(BlogsDetails.class);
	
	Predicate equal = criteriaBuilder.equal(root.get("blogDetailsId"), blogsDetailsId);
	
	createQuery.select(root).where(equal);
	List<BlogsDetails> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
	
	
	
	
		return resultList.get(0);
	}

	@Override
	public Responce deleteBlogsDetailsById(String blogsDetailsId) {
		BlogsDetails blogsDetails = getBlogsDetailsById(blogsDetailsId);
		Responce responce=new Responce();
		if(blogsDetails==null) {
			responce.setMassege("blog details with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		blogsDetailsRepositary.delete(blogsDetails);
		responce.setMassege("blog detail deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateBlogsDetails(String BlogsDetailsId, HashMap<String, Object> blogsDetailsData) {
		BlogsDetails blogsDetails = getBlogsDetailsById(BlogsDetailsId);
		Responce responce=new Responce();
		if(blogsDetails==null) {
			responce.setMassege("blog details with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(blogsDetailsData.get("active_Status")!=null) {
			
			if(blogsDetails.getActiveStatus().equalsIgnoreCase("D")) {
				blogsDetails.setActiveStatus("A");
			}else {
				blogsDetails.setActiveStatus("D");
			}
			
		}
        if(blogsDetailsData.get("")!=null) {
			
		}
         if(blogsDetailsData.get("")!=null) {
	
           }
		
		BlogsDetails updatedBlogsDetails = blogsDetailsRepositary.save(blogsDetails);
		if(updatedBlogsDetails==null) {
			responce.setMassege("failed to update the details");
			responce.setStatus(true);
		}
		
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getBlogsDetails(Blogs blogs) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BlogsDetails> createQuery = criteriaBuilder.createQuery(BlogsDetails.class);
		Root<BlogsDetails> root = createQuery.from(BlogsDetails.class);
		Predicate equal = criteriaBuilder.equal(root.get("blogs"), blogs);
		createQuery.select(root).where(equal);
		List<BlogsDetails> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			map.put("BLOG_DETAILS_ID", result.getBlogDetailsId());
			map.put("BLOG_DETAILS_HEADING", result.getBlogDetailsHeading());
			map.put("BLOG_DETAILS_DESC", result.getBlogDetaildDesc());
			map.put("ACTIVE_STATUS", result.getActiveStatus());
			map.put("BLOG_DETAILS_ORDER", result.getBlogDetailsOrder());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

}
