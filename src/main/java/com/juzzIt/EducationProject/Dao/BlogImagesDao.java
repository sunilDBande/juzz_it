package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BlogImagesDaoInterface;
import com.juzzIt.EducationProject.Entity.BlogImages;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BlogImagesRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class BlogImagesDao implements BlogImagesDaoInterface {

	@Autowired
	private BlogImagesRepository blogImagesRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public BlogImages addBlogImages(BlogImages blogImages) {
		return blogImagesRepository.save(blogImages);
	}

	@Override
	public BlogImages getBlogImagesById(String imageId) {
	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BlogImages> createQuery = criteriaBuilder.createQuery(BlogImages.class);
		Root<BlogImages> root = createQuery.from(BlogImages.class);
		Predicate predicate = criteriaBuilder.equal(root.get("imageId"), imageId);
		createQuery.select(root).where(predicate);
		
		List<BlogImages> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		if(resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}

	@Override
	public Responce deleteBlogImages(String imageId) {
		BlogImages blogImages = getBlogImagesById(imageId);
		Responce responce=new Responce();
		
		if(blogImages==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		blogImagesRepository.delete(blogImages);
		
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce updateBlogImages(String imageId, HashMap<String, Object> imageData) {
		BlogImages blogImages = getBlogImagesById(imageId);
		Responce responce=new Responce();
		
		if(blogImages==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(imageData.get("active_Status")!=null) {
			if(blogImages.getActiveStatus().equalsIgnoreCase("D")) {
				blogImages.setActiveStatus("A");
			}else {
				blogImages.setActiveStatus("D");
			}
		}
		
		
		BlogImages updateBlogImages = blogImagesRepository.save(blogImages);
		
		if(updateBlogImages==null) {
			responce.setMassege("failed to update the data");
			responce.setStatus(false);
			return responce;
		}
		
		
		responce.setMassege("data updated successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllBlogImages( Blogs blogs) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BlogImages> createQuery = criteriaBuilder.createQuery(BlogImages.class);
		Root<BlogImages> root = createQuery.from(BlogImages.class);
		Predicate predicate = criteriaBuilder.equal(root.get("blogs"), blogs);
		createQuery.select(root).where(predicate);
		
		List<BlogImages> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			
			map.put("image_Id", result.getImageId());
			map.put("image_URL", result.getImageURL());
			map.put("active_Status", result.getActiveStatus());
			
			return map;
		}).collect(Collectors.toList());

		
		return collect;
	}

}
