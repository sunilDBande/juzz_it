package com.juzzIt.EducationProject.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BlogImagesServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BlogsCommentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BlogsDetailsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BlogsServiceInterface;

@RestController
@RequestMapping("blg")
public class BlogsController {

	
	@Autowired
	private BlogsServiceInterface blogsServiceInterface;
	
	@Autowired
	private BlogsDetailsServiceInterface blogsDetailsServiceInterface;
	
	
	
	@Autowired
	private BlogsCommentServiceInterface blogsCommentServiceInterface;
	
	@Autowired
	private BlogImagesServiceInterface blogImagesServiceInterface;
	
	
	

	
	@PostMapping("/blogs")
	public Responce addNewBlogs(@RequestBody HashMap<String, Object> blogsData) {
		return blogsServiceInterface.addNewBlogs( blogsData);	
	}
	@DeleteMapping("/blogs/{blogId}")
	public Responce deleteBlog(@PathVariable("blogId") String  blogId) {
		return blogsServiceInterface.deleteBlog(blogId);
	}
	@PutMapping("/blogs/{blogId}")
	public Responce updateBlog(@PathVariable("blogId") String blogId,@RequestBody HashMap<String, Object> blogData) {
		return blogsServiceInterface.updateBlog(blogId, blogData);
	}
	
	@GetMapping("/blogs")
	public List<Map<String, Object>> getAllBlogs(){
		return blogsServiceInterface.getAllBlogs();
		
	}
	
	
	@GetMapping("/student/blogs")
	public List<Map<String, Object>> getAllBlogsWithImages(){
		return blogsServiceInterface.getAllBlogsWithImages();
	}
	
	 @GetMapping("/student/blogs/{blogId}/details")
     public List<Map<String, Object>> getAllBlogWithDetails(@PathVariable("blogId")  String blogId){
    	 
    	 return blogsServiceInterface.getAllBlogWithDetails(blogId);
     }
	
	
	/// blog image
	
	
	@PostMapping("/blogs/{blogId}/images")
	public Responce addBlogImage(@PathVariable("blogId") String blogId,@RequestBody HashMap<String, Object> imageData) {
		return blogImagesServiceInterface.addBlogImage(blogId, imageData);
	}
	
	@DeleteMapping("/blogs/images/{imageId}")
	public Responce deleteBlogImage(@PathVariable("imageId") String imageId) {
		return blogImagesServiceInterface.deleteBlogImage(imageId);
	}
	
	@PutMapping("/blogs/images/{imageId}")
	public Responce updateBlogImageData(@PathVariable("imageId") String imageId,@RequestBody HashMap<String, Object> imageData) {
		return blogImagesServiceInterface.updateBlogImageData(imageId, imageData);
	}
	
	@GetMapping("/blogs/{blogId}/images")
	public List<Map<String, Object>> getBlogImages(@PathVariable("blogId") String blogId){
		return blogImagesServiceInterface.getBlogImages(blogId);
	}
	
	
	///
	
	@PostMapping("/blogs/{blogId}/details")
	public Responce addNewBlogDetails(@PathVariable("blogId") String blogId,@RequestBody  HashMap<String, Object> blogDetailsData) {
		return blogsDetailsServiceInterface.addNewBlogDetails(blogId, blogDetailsData);
	}


@DeleteMapping("/blogDetails/{blogDetailsId}")
public Responce deleteBlogDetails(@PathVariable("blogDetailsId") String blogDetailsId) {
    	 return blogsDetailsServiceInterface.deleteBlogDetails(blogDetailsId);
     }
     

@PutMapping("/blogDetails/{blogDetailsId}")
     public Responce updateBlogDetails(@PathVariable("blogDetailsId") String blogDetailsId,@RequestBody HashMap<String, Object> blogDetailsData) {
    	 return blogsDetailsServiceInterface.updateBlogDetails(blogDetailsId, blogDetailsData);
     }
     
     
     @GetMapping("/blogs/{blogId}/details")
     public List<Map<String, Object>> getAllBlogDetails(@PathVariable("blogId")  String blogId){
    	 
    	 return blogsDetailsServiceInterface.getAllBlogDetails(blogId);
     }
    
     
     
     @PostMapping("/student/{studentId}/blogs/{blogId}/comments")
     public Responce addNewComment(@PathVariable("studentId") String studentId,@PathVariable("blogId") String blogId,@RequestBody HashMap<String, Object> blogCommentData ) {
    	 return blogsCommentServiceInterface.addNewComment(studentId, blogId, blogCommentData);
     }
     
     
     @DeleteMapping("/blogs/comments/{blogCommentId}")
     public Responce deleteBlogComment(@PathVariable("blogCommentId") String blogCommentId) {
    	 return blogsCommentServiceInterface.deleteBlogComment(blogCommentId);
     }

     
     @PutMapping("/blogs/comments/{blogCommentId}")
     public Responce updateBlogComment(@PathVariable("blogCommentId") String blogCommnetId,@RequestBody HashMap<String, Object> blogCommentData) {
    	 return blogsCommentServiceInterface.updateBlogComment(blogCommnetId, blogCommentData);
     }	
     
     @GetMapping("/blogs/{blogId}/comments")
     public List<Map<String, Object>> getBlogCommentData(@PathVariable("blogId") String blogId){
    	 return  blogsCommentServiceInterface.getBlogCommentData(blogId);
     }
}

