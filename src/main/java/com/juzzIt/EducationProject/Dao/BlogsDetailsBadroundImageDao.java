package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BlogsDetailsBadroundImageDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsDetailsBadroundImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BlogsDetailsBadroundImageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class BlogsDetailsBadroundImageDao implements BlogsDetailsBadroundImageDaoInterface {
	
	
	@Autowired
	private BlogsDetailsBadroundImageRepository blogsDetailsBadroundImageRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public BlogsDetailsBadroundImage addBlogsDetailsBadroundImage(BlogsDetailsBadroundImage blogsDetailsBadroundImage) {
		// TODO Auto-generated method stub
		return blogsDetailsBadroundImageRepository.save(blogsDetailsBadroundImage);
	}

	@Override
	public BlogsDetailsBadroundImage getBlogsDetailsBadroundImageById(String imageId) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<BlogsDetailsBadroundImage> createQuery = criteriaBuilder.createQuery(BlogsDetailsBadroundImage.class);
	Root<BlogsDetailsBadroundImage> root = createQuery.from(BlogsDetailsBadroundImage.class);
	Predicate predicate = criteriaBuilder.equal(root.get("imageId"), imageId);
	createQuery.select(root).where(predicate);
	List<BlogsDetailsBadroundImage> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
		return resultList.get(0);
	}

	@Override
	public Responce updateBlogsDetailsBadroundImage(String imageId, HashMap<String, Object> imageData) {
		BlogsDetailsBadroundImage image = getBlogsDetailsBadroundImageById( imageId);
		
		Responce responce=new Responce();
		if(image==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(imageData.get("active_Status")!=null) {
			
             if(image.getActiveStatus().equalsIgnoreCase("D")) {
                	image.setActiveStatus("A");
               }else {
                 	image.setActiveStatus("D");
               }
		}
		
		
		
		BlogsDetailsBadroundImage badroundImage = blogsDetailsBadroundImageRepository.save(image);
		
		if(badroundImage==null) {
			responce.setMassege("failed to update the data");
			responce.setStatus(false);
			return responce;
		}
		
		
		responce.setMassege("image Data updated successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce deleteBlogsDetailsBadroundImage(String imageId) {
BlogsDetailsBadroundImage image = getBlogsDetailsBadroundImageById( imageId);
		
		Responce responce=new Responce();
		if(image==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		blogsDetailsBadroundImageRepository.delete(image);
		
		responce.setMassege("image deleted succesfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getBlogsDetailsBadroundImage(Blogs blogs) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BlogsDetailsBadroundImage> createQuery = criteriaBuilder.createQuery(BlogsDetailsBadroundImage.class);
		Root<BlogsDetailsBadroundImage> root = createQuery.from(BlogsDetailsBadroundImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("blogs"), blogs);
		createQuery.select(root).where(predicate);
		List<BlogsDetailsBadroundImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			map.put("IMAGE_ID", result);
			map.put("IMAGE_URL", result);
			map.put("ACTIVE_STATUS", result);
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}

}
