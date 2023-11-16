package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BlogsDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BlogsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class BlogsDao  implements BlogsDaoInterface{
	
	
	@Autowired
	private BlogsRepository blogsRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Blogs addNewBloge(Blogs blogs) {
		// TODO Auto-generated method stub
		return blogsRepository.save(blogs);
	}

	@Override
	public Blogs getBlogsById(String blogId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Blogs> createQuery = criteriaBuilder.createQuery(Blogs.class);
		
		Root<Blogs> root = createQuery.from(Blogs.class);
		Predicate predicate = criteriaBuilder.equal(root.get("blogId"), blogId);
		
		createQuery.select(root).where(predicate);
		List<Blogs> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteBlogeById(String blogId) {
		 Blogs blogs = getBlogsById( blogId);
		 
		 Responce responce=new Responce();
		 if(blogs==null) {
			 responce.setMassege("blog with the given id not found");
			 responce.setStatus(false);
			 return responce;
		 }
		 
		 
		 blogsRepository.delete(blogs);
		 responce.setMassege("blog deleted successfully");
		 responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateBlogs(String blogId, HashMap<String, Object> blogData) {
 Blogs blogs = getBlogsById( blogId);
		 
		 Responce responce=new Responce();
		 if(blogs==null) {
			 responce.setMassege("blog with the given id not found");
			 responce.setStatus(false);
			 return responce;
		 }
				 
		 if(blogData.get("active_Status")!=null) {
			 
			 System.out.println(blogData);
			 if(blogs.getActiveStatus().equalsIgnoreCase("D")) {
				 blogs.setActiveStatus("A");
			 }else
			 {
				 blogs.setActiveStatus("D");
			 }
		 }
		 
		 
              if(blogData.get("")!=null) {
			 
		      }
		 
              if(blogData.get("")!=null) {
	 
              }
 
 
 Blogs updatedBlogs = blogsRepository.save(blogs);
 
		 
		 if(updatedBlogs==null) {
			 responce.setMassege("failed to update the data");
			 responce.setStatus(false);
			 return responce;
		 }
		 
		 responce.setMassege("successfully updated the data");
		 responce.setStatus(true);
		 
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllBloges() {
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Blogs> createQuery = criteriaBuilder.createQuery(Blogs.class);
		Root<Blogs> root = createQuery.from(Blogs.class);
		createQuery.select(root);
		List<Blogs> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			map.put("BLOG_ID", result.getBlogId());
			map.put("BLOG_HEADING", result.getBlogHeading());
			map.put("BLOG_INTRUDUCTION", result.getBlogIntruduction());
			map.put("BLOG_ORDER", result.getBlogOrder());
			map.put("BLOG_DESC", result.getBlogDesc());
			map.put("ACTIVE_STATUS", result.getActiveStatus());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
	

}
