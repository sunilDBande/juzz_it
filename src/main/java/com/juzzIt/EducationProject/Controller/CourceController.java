package com.juzzIt.EducationProject.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Entity.CourseTypeDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.CourseCategoryServicesInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeBagroundImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeDetailsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeEssentialsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeKeyHighlightsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeObjectiveServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeProjectsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeServicesInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeToolsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeVideoServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.LessonServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.ModuleServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.ToolImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.TopicServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.TopicVideoServiceInterface;

@RestController
@RequestMapping("/cur")
@CrossOrigin(origins = "*")
public class CourceController {
	

	@Autowired
private	CourseServiceInterface courseServiceInterface;
	
	@Autowired
	private CourseTypeServicesInterface courseTypeServicesInterface;
	
	
	@Autowired
	private CourseTypeDetailsServiceInterface courseTypeDetailsServiceInterface;
	
	@Autowired
	private CourseTypeEssentialsServiceInterface courseTypeEssentialsServiceInterface;
	
	@Autowired
	private CourseTypeKeyHighlightsServiceInterface courseTypeKeyHighlightsServiceInterface;
	
	@Autowired
private CourseTypeObjectiveServiceInterface courseTypeObjectiveServiceInterface;
	
	@Autowired
	private CourseTypeProjectsServiceInterface courseTypeProjectsServiceInterface;
	
	@Autowired
	private CourseTypeToolsServiceInterface courseTypeToolsServiceInterface;
	
	
	@Autowired
	private ModuleServiceInterface moduleServiceInterface;
	
	@Autowired
	private LessonServiceInterface lessonServiceInterface;
	
	@Autowired
	private TopicServiceInterface topicServiceInterface;

	
	@Autowired
	private CourseCategoryServicesInterface courseCategoryServicesInterface;
	
	@Autowired
	private CourseImageServiceInterface courseImageServiceInterface;
	
	@Autowired
	private CourseTypeImageServiceInterface courseTypeImageServiceInterface;
	
	@Autowired
	private CourseTypeBagroundImageServiceInterface courseTypeBagroundImageServiceInterface;
	@Autowired
	private CourseTypeVideoServiceInterface courseTypeVideoServiceInterface;
	@Autowired
	private ToolImageServiceInterface toolImageServiceInterface;
	
	@Autowired
	private TopicVideoServiceInterface topicVideoServiceInterface;
	
	//// course categury 
	
	@PostMapping("/categorys")
	public Responce addCategory(@RequestBody HashMap<String, Object> category) throws Exception {
		
		return courseCategoryServicesInterface.addCategory(category);
	}
	
	
	@DeleteMapping("/categorys/{categoryId}")
	public Responce deleteCategory(@PathVariable("categoryId") String categoryId) throws Exception {
		
		return courseCategoryServicesInterface.deleteCategory(categoryId);
	}
	
	
	@GetMapping("/categorys")
	public List<Map<String , Object>>  getAllCategory() throws Exception{
		return courseCategoryServicesInterface.getAllCategory();
	}
	
		
	///// for course class
	
	@PostMapping("/courses")
	public Responce addCourse(@RequestBody   HashMap<String,Object> course) throws Exception {
		return courseServiceInterface.addCourse(course);
	}

	@PutMapping("/courses/{courseId}")
	public Responce updateCourse( @PathVariable("courseId")String courseId,@RequestBody HashMap<String,Object>course) throws Exception {
		return courseServiceInterface.updateCourse(courseId, course);
	}
	
	@GetMapping("/courses")
	public List<HashMap<String, Object>> getAllCourse() throws Exception{
		return courseServiceInterface.getAllCourse();
	}
	
	
	@GetMapping("/courses/deleted")
	public List<HashMap<String, Object>> getAllTempDeletedCourse() throws Exception{
		return courseServiceInterface.getAllTempDeletedCourse();
	}
	
	
	@DeleteMapping("/courses/{courseId}")
	public Responce deleteCourse(@PathVariable("courseId") String courseId) {
		return courseServiceInterface.deleteCourse(courseId);
	}
	
	@GetMapping("/courses/courseTypes")
	public List<HashMap<String, Object>> getAllCoursesAndItsTypes() throws Exception{
		return courseServiceInterface.getAllCoursesAndItsTypes();
	}
	
	
	////  course image
	
	
	@PostMapping("/courses/{courseId}/courseImages")
	public Responce addNewCourseImage(@PathVariable("courseId")String courseId,@RequestBody HashMap<String, Object> courseImageData) {
	return courseImageServiceInterface.addNewCourseImage(courseId, courseImageData);	
	}
	
	
	@DeleteMapping("/courseImages/{courseImageId}")
	public Responce deleteCourseImage(@PathVariable("courseImageId") String courseImageId) {
		return courseImageServiceInterface.deleteCourseImage(courseImageId);
	}
	
	
	@PutMapping("/courseImages/{courseImageId}")
	public Responce updateCourseImage(@PathVariable("courseImageId") String courseImageId,@RequestBody HashMap<String, Object> courseImageData) {
		return courseImageServiceInterface.updateCourseImage(courseImageId, courseImageData);
	}
	
	@GetMapping("/courses/{courseId}/courseImages")
	public List<Map<String , Object>> getCoueseImate(@PathVariable("courseId")String courseId) {
		
		return courseImageServiceInterface.getCoueseImage(courseId);
	}
	
	
	///// for course type classs
	
	
	@PostMapping("/courses/{courseId}/courseTypes")
	public Responce addNewCourseType(@PathVariable("courseId") String courseId,@ RequestBody HashMap<String ,Object>courseTyper) throws Exception {
		
		return courseTypeServicesInterface.addCourseType(courseId,courseTyper);
	}
	
	@GetMapping("/courses/{courseId}/courseTypes")
	public List<HashMap<String, Object>> getAllCourseByCourseId(@PathVariable("courseId") String courseId) throws Exception{
		return courseTypeServicesInterface.getAllCourseByCourseId(courseId);
	}
	
	
	@PutMapping("/courseTypes/{courseTypeId}")
	public Responce updateCourseType(@PathVariable String courseTypeId ,@RequestBody HashMap<String, Object> courseType) throws Exception {
		return courseTypeServicesInterface.updateCourseType(courseTypeId,courseType);
	}
	
	@DeleteMapping("/courseTypes/{courseTypeId}")
	public Responce deleteCourseType(@PathVariable String courseTypeId ) throws Exception {
		return courseTypeServicesInterface.deleteCourseType(courseTypeId);
	}
	
	@GetMapping("/courseType/{courseTypeId}/allDetails")
	public List<HashMap<String , Object>> getAllCourseTypeDetailsById(@PathVariable("courseTypeId") String courseTypeId) throws Exception{
		return courseTypeServicesInterface.getAllCourseTypeDetailsById(courseTypeId);
	}
	
	
	
	////courseType image
	
	
	@PostMapping("/courseTypes/{courseTypeId}/courseTypeImages")
	public Responce addNewCourseTypeImage(@PathVariable("courseTypeId") String courseTypeId,@RequestBody HashMap<String, Object> courseTypeImageData) {
		return courseTypeImageServiceInterface.addNewCourseTypeImage(courseTypeId, courseTypeImageData);
	}
	
	@DeleteMapping("/courseTypeImages/{courseTypeImageId}")
	public Responce deleteCourseTypeImage(@PathVariable("courseTypeImageId") String courseTypeImageId) {
		return courseTypeImageServiceInterface.deleteCourseTypeImage(courseTypeImageId);
	}
	
	@PutMapping("/courseTypeImages/{courseTypeImageId}")
	public Responce updateCourseTypeImage(@PathVariable("courseTypeImageId") String courseTypeImageId,@RequestBody HashMap<String, Object> courseTypeImageData) {
		return courseTypeImageServiceInterface.updateCourseTypeImage(courseTypeImageId, courseTypeImageData);
	}
	
	
	@GetMapping("/courseTypes/{courseTypeId}/courseTypeImages")
	public List<Map<String, Object>> getCourseTypeImage(@PathVariable("courseTypeId")String courseTypeId ){
		return courseTypeImageServiceInterface.getCourseTypeImage(courseTypeId);
	}
	
	/// courseType baground  Image
	
	@PostMapping("/courseTypes/{courseTypeId}/courseTypeBagroundImages")
	public Responce addNewCourseTypeBagroundImage(@PathVariable("courseTypeId") String courseTypeId,@RequestBody HashMap<String, Object> courseTypeBagroundImageData) {
		return courseTypeBagroundImageServiceInterface.addNewCourseTypeBagroundImage(courseTypeId, courseTypeBagroundImageData);
	}
	
	@DeleteMapping("/courseTypeBagroundImages/{courseTypeBagroundImageId}")
	public Responce deleteCourseTypeBagroundImage(@PathVariable("courseTypeBagroundImageId") String courseTypeBagroundImage) {
		return courseTypeBagroundImageServiceInterface.deleteCourseTypeBagroundImage(courseTypeBagroundImage);
	}
	@PutMapping("/courseTypeBagroundImages/{courseTypeBagroundImageId}")
	public Responce updateCourseTypeBagroundImage(@PathVariable("courseTypeBagroundImageId") String courseTypeBagroundImageId,@RequestBody HashMap<String, Object> courseTypeBagroundImageData) {
		return courseTypeBagroundImageServiceInterface.updateCourseTypeBagroundImage(courseTypeBagroundImageId, courseTypeBagroundImageData);
	}
	@GetMapping("/courseTypes/{courseTypeId}/courseTypeBagroundImages")
	public List<Map<String, Object>> getCourseTypeBagroundImage(@PathVariable("courseTypeId")String courseTypeId ){
		return courseTypeBagroundImageServiceInterface.getCourseTypeBagroundImage(courseTypeId);
	}
	
	/// course Type video
	
	@PostMapping("/courseTypes/{courseTypeId}/courseTypeVideos")
	public Responce addNewCourseTypeVideo(@PathVariable("courseTypeId") String courseTypeId,@RequestBody HashMap<String, Object> courseTypeVideoData) {
		return courseTypeVideoServiceInterface.addNewCourseTypeVideo(courseTypeId, courseTypeVideoData);
	}
	
	@DeleteMapping("/courseTypeVideos/{courseTypeVideoId}")
	public Responce deleteCourseTypeVideo(@PathVariable("courseTypeVideoId") String courseTypeVideoId) {
		return courseTypeVideoServiceInterface.deleteCourseTypeVideo(courseTypeVideoId);
	}
	@PutMapping("/courseTypeVideos/{courseTypeVideoId}")
	public Responce updateCourseTypeVideo(@PathVariable("courseTypeVideoId") String courseTypeVideoId,@RequestBody HashMap<String, Object> courseTypeVideoData) {
		return courseTypeVideoServiceInterface.updateCourseTypeVideo(courseTypeVideoId, courseTypeVideoData);
	}
	@GetMapping("/courseTypes/{courseTypeId}/courseTypeVideos")
	public List<Map<String, Object>> getCourseTypeVideo(@PathVariable("courseTypeId")String courseTypeId ){
		return courseTypeVideoServiceInterface.getCourseTypeVideo(courseTypeId);
	}
	
	
	
	///   for  course details  class
	
	@PostMapping("/courseTypes/{courseTypeId}/courseDetails")
	public Responce addCourseTypeDetails(@PathVariable("courseTypeId")	 String courseTypeId,@RequestBody HashMap<String , Object> courseTypeDetails) throws Exception {
		return courseTypeDetailsServiceInterface.addCourseTypeDetails(courseTypeId, courseTypeDetails);
	}
	
	
	@GetMapping("/courseTypes/{courseTypeId}/courseDetails")
	public List<HashMap<String, Object>> getCourseTypeDetails(@PathVariable("courseTypeId")	 String courseTypeId) throws Exception{
		return courseTypeDetailsServiceInterface.getCourseTypeDetails(courseTypeId);
	}
	
	@PutMapping("/courseTypes/{detailId}/courseDetails")
	public Responce updateCourseDetails(@PathVariable("detailId")	 String courseDetailsId ,@RequestBody HashMap<String ,Object> coursetypeDetails) throws Exception {
		return courseTypeDetailsServiceInterface.updateCourseDetails(courseDetailsId, coursetypeDetails);
	}
	@DeleteMapping("/courseTypes/{courseTypeId}/courseDetails")
	public Responce deleteDetails(@PathVariable("courseDetailsId")	 String courseDetailsId) {
	return null; 		
	}
	
	
	/////  for course type essential class ******
	
	@PostMapping("/courseTypes/{courseTypeId}/essentials")
	public Responce addEssential(@PathVariable("courseTypeId")String courseTypeId,@RequestBody HashMap<String , Object> essential) throws Exception {
		return courseTypeEssentialsServiceInterface.addEssential(courseTypeId, essential);
	}
	
	@DeleteMapping("/essestials/{essentialId}")
	public Responce deleteEssential(@PathVariable("essentialId") String essentialId) {
		return courseTypeEssentialsServiceInterface.deleteEssential(essentialId);
	}
	
	@GetMapping("/courseTypes/{courseTypeId}/essentials")
	public List<Map<String , Object>> getAllEssentials(@PathVariable("courseTypeId")String courseTypeId) throws Exception{
		
		return courseTypeEssentialsServiceInterface.getAllEssentials(courseTypeId);
	}
	
	

	//// for key highlight class
	


	@PostMapping("/courseTypes/{courseTypeId}/keyHighlights")
	public Responce addKeyHighlight(@PathVariable("courseTypeId")String courseTypeId,@RequestBody HashMap<String , Object> KeyHighlight) throws Exception {
		return courseTypeKeyHighlightsServiceInterface.addKeyHighlight(courseTypeId, KeyHighlight);
	}
	
	@DeleteMapping("/keyHighlights/{keyHighlightId}")
	public Responce deleteKeyHighlight(@PathVariable("keyHighlightId") String keyHighlightId) throws Exception {
		return courseTypeKeyHighlightsServiceInterface.deleteKeyHighlight(keyHighlightId);
	}
	
	@GetMapping("/courseTypes/{courseTypeId}/keyHighlights")
	public List<Map<String , Object>> getAllKeyHighlight(@PathVariable("courseTypeId")String courseTypeId) throws Exception{
		return courseTypeKeyHighlightsServiceInterface.getAllKeyHighlight(courseTypeId);
	}
	
	
	////for Objective class
	

	@PostMapping("/courseTypes/{courseTypeId}/objectives")
	public Responce addObjective(@PathVariable("courseTypeId")String courseTypeId,@RequestBody HashMap<String , Object> KeyHighlight) throws Exception {
		return courseTypeObjectiveServiceInterface.addObjective(courseTypeId, KeyHighlight);
	}
	
	@DeleteMapping("/objectives/{objectiveId}")
	public Responce deleteObjective(@PathVariable("objectiveId") String objectiveId) throws Exception {
		return courseTypeObjectiveServiceInterface.deleteObjective(objectiveId);
	}
	
	@GetMapping("/courseTypes/{courseTypeId}/objectives")
	public List<Map<String , Object>> getAllObjective(@PathVariable("courseTypeId")String courseTypeId) throws Exception{
		
		return courseTypeObjectiveServiceInterface.getAllObjective(courseTypeId);
	}
	
	
	
	///for  	Projects
	
	
//above done
	@PostMapping("/courseTypes/{courseTypeId}/projects")
	public Responce addProjects(@PathVariable("courseTypeId")String courseTypeId,@RequestBody HashMap<String , Object> project) throws Exception {
		return courseTypeProjectsServiceInterface.addProjects(courseTypeId, project);
	}
	
	@DeleteMapping("/projects/{projectsId}")
	public Responce deleteProjects(@PathVariable("projectsId") String projectsId) throws Exception {
		return courseTypeProjectsServiceInterface.deleteProjects(projectsId);
	}
	
	@GetMapping("/courseTypes/{courseTypeId}/projects")
	public List<Map<String , Object>> getAllProjects(@PathVariable("courseTypeId")String courseTypeId) throws Exception{
		
		return courseTypeProjectsServiceInterface.getAllProjects(courseTypeId);
	}
	
	
	////for Tools class
	
	                 
	
//all done

	@PostMapping("/courseTypes/{courseTypeId}/tools")
	public Responce addTools(@PathVariable("courseTypeId")String courseTypeId,@RequestBody HashMap<String , Object> tool) throws Exception {
		return courseTypeToolsServiceInterface.addTools(courseTypeId, tool);
	}
	
	@DeleteMapping("/tools/{toolId}")
	public Responce deleteTools(@PathVariable("toolId") String toolId) throws Exception {
		return courseTypeToolsServiceInterface.deleteTools(toolId);
	}
	
	@GetMapping("/courseTypes/{courseTypeId}/tools")
	public List<Map<String , Object>> getAllTools(@PathVariable("courseTypeId")String courseTypeId) throws Exception{
		
		return courseTypeToolsServiceInterface.getAllTools(courseTypeId);
	}
	
	
	
	//// tool image
	
	@PostMapping("/tools/{toolId}/toolImages")
	public Responce addNewToolImage(@PathVariable("toolId") String toolId,@RequestBody HashMap<String, Object> toolImageData) {
		return toolImageServiceInterface.addNewToolImage(toolId, toolImageData);
	}
	
	@DeleteMapping("/toolImages/{toolImageId}")
	public Responce deleteToolImage(@PathVariable("toolImageId") String toolImageId) {
		return toolImageServiceInterface.deleteToolImage(toolImageId);
	}
	
	@PutMapping("/toolImages/{toolImageId}")
	public Responce updateToolImage(@PathVariable("toolImageId") String toolImageId,@RequestBody HashMap<String, Object> toolImageData) {
		return toolImageServiceInterface.updateToolImage(toolImageId, toolImageData);
	}
	@GetMapping("/tools/{toolId}/toolImages")
	public List<Map<String, Object>> getToolImage(@PathVariable("toolId")String toolId ){
		return toolImageServiceInterface.getToolImage(toolId);
	}
	
	
	
	/// for  Models class
	
	//all done

	@PostMapping("/courseTypes/{courseTypeId}/models")
	public Responce addModels(@PathVariable("courseTypeId")String courseTypeId,@RequestBody HashMap<String , Object> model) throws Exception {
		return moduleServiceInterface.addModels(courseTypeId, model);
	}
	
	@DeleteMapping("/models/{modelId}")
	public Responce deleteModels(@PathVariable("modelId") String modelId) throws Exception {
		return moduleServiceInterface.deleteModels(modelId);
	}
	
	@GetMapping("/courseTypes/{courseTypeId}/models")
	public List<Map<String , Object>> getAllModels(@PathVariable("courseTypeId")String courseTypeId) throws Exception{
		
		return moduleServiceInterface.getAllModels(courseTypeId);
	}
	
	@PutMapping("/module/{module_id}")
	public Responce updeteModule(@PathVariable("module_id") String module_Id,@RequestBody HashMap<String ,Object> module) throws Exception {
		return moduleServiceInterface.updateModule(module_Id, module);
	}
	
	
	
	
	//// for lesson class
	//all done

	@PostMapping("/models/{modelId}/lessons")
	public Responce addLesson(@PathVariable("modelId")String modelId,@RequestBody HashMap<String , Object> lesson) throws Exception {
		return lessonServiceInterface.addLesson(modelId, lesson);
	}
	
	@DeleteMapping("/lessons/{lessonId}")
	public Responce deleteLesson(@PathVariable("lessonId") String lessonId) throws Exception {
		return lessonServiceInterface.deleteLesson(lessonId);
	}
	
	@GetMapping("/models/{modelId}/models")
	public List<Map<String , Object>> getAllLessons(@PathVariable("modelId")String modelId) throws Exception{	
		return lessonServiceInterface.getAllLessons(modelId);
	}
	
	
	@PutMapping("/lesson/{lesson_Id}")
	public Responce updateLesson(@PathVariable("lesson_Id")String lessonId,@RequestBody HashMap<String , Object> lessonData) throws Exception {
		return lessonServiceInterface.updateLessons(lessonId, lessonData);
	}
	
	
	
	//// for topics class
	

	@PostMapping("/lessons/{lessonId}/topics")
	public Responce addTopic(@PathVariable("lessonId")String lessonId,@RequestBody HashMap<String , Object> topic) throws Exception {
		return topicServiceInterface.addTopic(lessonId, topic);
	}
	
	@DeleteMapping("/topics/{topicId}")
	public Responce deleteTopic(@PathVariable("topicId") String topicId) throws Exception {
		return topicServiceInterface.deleteTopic(topicId);
	}
	
	@GetMapping("/lessons/{lessonId}/topics")
	public List<Map<String , Object>> getAllTopics(@PathVariable("lessonId")String lessonId) throws Exception{
		
		return topicServiceInterface.getAllTopics(lessonId);
	}
	
	
	@PutMapping("/topics/{topic_Id}")
	public Responce updateTopic(@PathVariable("topic_Id") String topic_Id,@RequestBody	 HashMap<String, Object> topicData) throws Exception {
		return topicServiceInterface.updeteTopic(topic_Id, topicData);
	}
		
	
	//// topic vedios
	
@PostMapping("/topics/{topicId}/topicVideos")
	public Responce addNewTopicVideo(@PathVariable("topicId") String topicId,@RequestBody HashMap<String, Object> topicVideoData) {
		return topicVideoServiceInterface.addNewTopicVideo(topicId, topicVideoData);
	}
	
	
@DeleteMapping("/topicVideos/{topicVideoId}")
	public Responce deleteTopicVideo(@PathVariable("topicVideoId") String topicVideoId) {
		return topicVideoServiceInterface.deleteTopicVideo(topicVideoId);
	}
	
@PutMapping("/topicVideos/{topicVideoId}")
	public Responce updateTopicVideo(@PathVariable("topicVideoId") String topicVideoId,@RequestBody HashMap<String, Object> topicVideoData) {
		return topicVideoServiceInterface.updateTopicVideo(topicVideoId, topicVideoData);
	}

@GetMapping("/topics/{topicId}/topicVideos")
	public List<Map<String, Object>> getTopicVideo(@PathVariable("topicId")String topicId ){
		return topicVideoServiceInterface.getTopicVideo(topicId);
	}
	
	
}
