package com.juzzIt.EducationProject.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BlogsCommentDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsComment;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BlogsCommentRepository;
import com.juzzIt.EducationProject.Repositary.BlogsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class BlogsCommentDao implements BlogsCommentDaoInterface {
	
	@Autowired
	private BlogsCommentRepository blogsCommentRepository;
	
	@Autowired
	private EntityManager entityManager;
	@Override
	public BlogsComment addNewComment(BlogsComment blogsComment) {
		// TODO Auto-generated method stub
		return blogsCommentRepository.save(blogsComment);
	}

	@Override
	public BlogsComment getBlogsCommentById(String blogsCommentId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BlogsComment> createQuery = criteriaBuilder.createQuery(BlogsComment.class);
		Root<BlogsComment> root = createQuery.from(BlogsComment.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("blogCommentId"), blogsCommentId);
		
		createQuery.select(root).where(predicate);
		List<BlogsComment> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		
		
		return resultList.get(0);
	}

	@Override
	public Responce deleteBlogsComment(String blogsCommentId) {
		BlogsComment blogsComment = getBlogsCommentById( blogsCommentId) ;
		Responce responce=new Responce();
		if(blogsComment==null) {
			responce.setMassege("comment with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		blogsCommentRepository.delete(blogsComment);
		responce.setMassege("comment deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateBlogsComment(String blogsCommentId) {
		BlogsComment blogsComment = getBlogsCommentById( blogsCommentId) ;
		Responce responce=new Responce();
		if(blogsComment==null) {
			responce.setMassege("comment with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		BlogsComment blogsComment2 = blogsCommentRepository.save(blogsComment);
		if(blogsComment2==null) {
			responce.setMassege("failed to update the data");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("detatils updated successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getBlogsCommentByBlog(Blogs blogs) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BlogsComment> createQuery = criteriaBuilder.createQuery(BlogsComment.class);
		Root<BlogsComment> root = createQuery.from(BlogsComment.class);
		Predicate predicate = criteriaBuilder.equal(root.get("blogs"), blogs);
		createQuery.select(root).where(predicate);
		List<BlogsComment> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String  ,Object>();	
			
			map.put("blog_comment_id", result.getBlogCommentId());
			map.put("blog_Comment", result.getComment());
			map.put("user_Email", result.getUserEmail());
			map.put("user_Name", result.getUserName());
			map.put("student_Data", getStudentData(result.getBlogCommentId()));
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
	
	public HashMap<String, Object> getStudentData(String blogCommentId){
		
		
String []data= {"STUDENT_ID","STUDENT_NAME","STUDENT_EMAIL"};
		
		
		String query= " select student.student_id,student.student_name ,student.student_email   "
				+ " from student , blogs_comment "
				+ " where "
				+ "  blogs_comment.blog_comment_id='"+blogCommentId+"'" 
				+ "  and  blogs_comment.student_id = student.student_id ";
		
		
		Query createNativeQuery = entityManager.createNativeQuery(query);
		List<Object[]> resultList = createNativeQuery.getResultList();
		
List<HashMap<String, Object>> finalOutput = new ArrayList<HashMap<String,Object>>();
		
		
		for(Object res[]:resultList) {
			  LinkedHashMap<String, Object> lh = new LinkedHashMap<String, Object>();
			for(int i=0; i<=res.length-1; i++) {
				if (res[i] == null || res[i].toString().trim().isEmpty()) {
					lh.put(data[i], "");
				} else {
					lh.put(data[i], res[i].toString());
				}
			}
			finalOutput.add(lh);
		}
		System.out.println("finalOutput--> "+finalOutput);
		if(finalOutput.isEmpty()) {
			return null;
		}
		
		return finalOutput.get(0);
	}

}
