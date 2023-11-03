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

import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseClassLinksServiceServicesInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCoursePlacementsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseRecordedVideoSubjectServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseStudentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseSubjectServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.PlacementImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedSubjectVideoServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.SubBatchServiceInterface;
import com.juzzIt.EducationProject.Services.BatchCoursePlacementsServiceImplementation;

@RestController
@RequestMapping("/bch")
@CrossOrigin(origins = "*")
public class BatchController {
	
	@Autowired
	private BatchServiceInterface batchServiceInterface;
	
	@Autowired
	private SubBatchServiceInterface subBatchServiceInterface;
	
	private BatchCourseServiceInterface batchCourseServiceInterface;
	
	@Autowired
	private PlacementImageServiceInterface placementImageServiceInterface;

    @Autowired
    public BatchController(BatchCourseServiceInterface batchCourseServiceInterface) {
        this.batchCourseServiceInterface = batchCourseServiceInterface;
    }
	@Autowired
	private BatchCourseSubjectServiceInterface batchCourseSubjectServiceInterface;

	@Autowired
	private BatchCourseStudentServiceInterface batchCourseStudentServiceInterface;
	
	
	@Autowired
	private BatchCoursePlacementsServiceInterface batchCoursePlacementsServiceInterface;
	
	@Autowired
	private BatchCourseClassLinksServiceServicesInterface batchCourseClassLinksServiceServicesInterface;
	
	@Autowired
	private BatchCourseRecordedVideoSubjectServiceInterface batchCourseRecordedVideoSubjectServiceInterface;
	
	@Autowired
	private RecordedSubjectVideoServiceInterface recordedSubjectVideoServiceInterface;
	
	
	
	@PostMapping("/batchs")
	public Responce addBatch(@RequestBody HashMap<String, Object> batch) throws Exception {
		return batchServiceInterface.addBatch(batch);
	}
	
	
	@DeleteMapping("/batchs/{batch_Id}")
	public Responce deleteBatch(@PathVariable("batch_Id") String batchId) throws Exception {
		return batchServiceInterface.deleteBatch(batchId);
	}
	
	@GetMapping("/batchs")
	public List<Map<String , Object>> getAllBatch() throws Exception {
		return batchServiceInterface.getAllBatch();
	}
		
	
	
	/// Sub Batch class....
	
	@PostMapping("/batchs/{batchId}/subBatchs")
	public Responce  addSubBatch(@PathVariable("batchId")String batchId,@RequestBody HashMap<String,Object> subBatch) throws Exception {
		
		return subBatchServiceInterface.addSubBatch(batchId, subBatch);
	}
	
	@DeleteMapping("/subBatchs/{subBatchId}")
	public Responce deleteSubBatch(@PathVariable("subBatchId") String subBatchId) throws Exception {
	
		return subBatchServiceInterface.deleteSubBatch(subBatchId);
	}
	
	@GetMapping("/batchs/{batchId}/subBatchs")
	public List<Map<String , Object>>  getAllSubBatch(@PathVariable String batchId) throws Exception{
		return subBatchServiceInterface.getAllSubBatch(batchId);
	}
	
	
	
	//// for batch course class *****
	
	@PostMapping("/subBatchs/{subBatchId}/batchCourses/{courseTypeId}")
	public Responce addBatchCourse(@PathVariable("subBatchId") String subBatchId,@PathVariable("courseTypeId") String courseTypeId ,@RequestBody HashMap<String ,Object> batchCourse) throws Exception {
		
		return batchCourseServiceInterface.addBatchCourse(subBatchId, courseTypeId, batchCourse);
	}
	
	@DeleteMapping("/batchCourses/{batchCourseId}")
	public Responce deleteBatchCourse( @PathVariable("batchCourseId") String batchCourseId) throws Exception {
		
		return batchCourseServiceInterface.deleteBatchCourse(batchCourseId);
	}
	
	
	@GetMapping("/subBatch/{subBatchId}/batchCourses")
	public List<Map<String , Object>>  getAllBatchCourses(@PathVariable("subBatchId") String subBatchId) throws Exception{
		return batchCourseServiceInterface.getAllBatchCourses(subBatchId);
	}
	
	@PutMapping("/batchCourses/{batchCourseId}")
	public Responce updateBatchCourseDetaile(@PathVariable("batchCourseId") String batchCourseId ,@RequestBody HashMap<String, Object> batchCourseData  ) throws Exception {
			
		return batchCourseServiceInterface.updateBatchCourseDetaile(batchCourseId, batchCourseData);
	}
	
	
	
	
	////for batch course student class ****NO Unique ID
	
	@PostMapping("/batchCourses/{batchCourseId}/student/{studentId}/{requestId}")
	public Responce addStudentToBatch(@PathVariable("batchCourseId") String batchCourseId,@PathVariable("studentId") String studentId,@PathVariable("requestId") String requestId,@RequestBody HashMap<String , Object> details) throws Exception {
		return batchCourseStudentServiceInterface.addStudentToBatch(batchCourseId, studentId, requestId, details);
	}
	
	@GetMapping("/batchCourses/{batchCourseId}/students")
	public List<Map<String, Object>>  getAllstudentsByBatchCourse(@PathVariable("batchCourseId") String batchCourseId){
		return batchCourseStudentServiceInterface.getAllstudentsByBatchCourse(batchCourseId);
	
	
	}
	
	@DeleteMapping("/batchCourseStudent/{batchCourseStudentId}")
	public Responce deleteBatchCourseStudentById(@PathVariable("batchCourseStudentId") String batchCourseStudentId) throws Exception {
		return batchCourseStudentServiceInterface.deleteBatchCourseStudentById(batchCourseStudentId);
	}
	
	@PutMapping("/batchCourseStudent/{batchCourseStudentId}")
	public Responce updateBatchCourseStudentDetailes(@PathVariable("batchCourseStudentId") String batchCourseStudentId,@RequestBody HashMap<String, Object> batchCourseStudentDetails) throws Exception {
		return batchCourseStudentServiceInterface.updateBatchCourseStudentDetailes(batchCourseStudentId, batchCourseStudentDetails);
	}
	
	
 @GetMapping("/student/{studentId}/batchCourse/Details")
	public List<HashMap<String, Object>>   getAllStudentEnrollBatchsByStudentId(@PathVariable("studentId") String studentId){
	return batchCourseStudentServiceInterface.getAllStudentEnrollBatchsByStudentId(studentId);
	}
	
	
	
	////public batch course subject ****
	
	@PostMapping("/batchCourses/{batchCourseId}/subjects/{teacherId}")
	public Responce addSubjectToBatch(@PathVariable("batchCourseId") String batchCourseId,@PathVariable("teacherId") String teacherId,@RequestBody HashMap<String, Object> subjctDetails) throws Exception {
		return batchCourseSubjectServiceInterface.addSubjectToBatch(batchCourseId, teacherId, subjctDetails);
	}
	
	@DeleteMapping("/batchCourseSubject/{batchCourseSubjectId}")
	public Responce deleteBatchCourseSubject(@PathVariable("batchCourseSubjectId") String batchCourseSubjectId) throws Exception {
		
		return batchCourseSubjectServiceInterface.deleteBatchCourseSubject(batchCourseSubjectId);
	}
	
	@GetMapping("/batchCourses/{batchCourseId}/subjects")
	public List<Map<String , Object>> getAllSubjectByBatchCourseId(@PathVariable("batchCourseId") String batchCourseId) throws Exception{
		return batchCourseSubjectServiceInterface.getAllSubjectByBatchCourseId(batchCourseId);
	}
	
	
	
	@PutMapping("/batchCourseSubject/{batchCourseSubjectId}")
	public Responce updateBatchCourseSubject(@PathVariable("batchCourseSubjectId") String batchCourseSubjectId,@RequestBody HashMap<String, Object> batchCourseSubjectDetails) throws Exception {
		return batchCourseSubjectServiceInterface.updateBatchCourseSubject(batchCourseSubjectId, batchCourseSubjectDetails);
	}
	
	
	
	
	//// batch Course Recorded video subjects
	
	
	
	
	@PostMapping("/batchCourses/{batchCourseId}/recordedVideoSubjects")
	public Responce addNewbatchCourseRecordedVideoSubject(@PathVariable("batchCourseId")String batchCourseId,@RequestBody Map<String, Object> subjectData) {
		return batchCourseRecordedVideoSubjectServiceInterface.addNewbatchCourseRecordedVideoSubject(batchCourseId, subjectData);
	}
	
	@DeleteMapping("/recordedVideoSubjects/{recordedVideoSubjectsId}")
	public Responce deletebatchCourseRecordedVideoSubject(@PathVariable("recordedVideoSubjectsId")String recordedVideoSubjectsId) {
		return batchCourseRecordedVideoSubjectServiceInterface.deletebatchCourseRecordedVideoSubject(recordedVideoSubjectsId);
	}
	
	@PutMapping("/recordedVideoSubjects/{recordedVideoSubjectsId}")
	public Responce updatebatchCourseRecordedVideoSubject(@PathVariable("recordedVideoSubjectsId")String recordedVideoSubjectsId,@RequestBody  Map<String, Object> subjectData) {
		return batchCourseRecordedVideoSubjectServiceInterface.updatebatchCourseRecordedVideoSubject(recordedVideoSubjectsId, subjectData);
	}
	
	@GetMapping("/batchCourses/{batchCourseId}/recordedVideoSubjects")
	public List<Map<String , Object>> getAllbatchCourseRecordedVideoSubjectByBatchCourseId(@PathVariable("batchCourseId")String batchCourseId) {
		return batchCourseRecordedVideoSubjectServiceInterface.getAllbatchCourseRecordedVideoSubjectByBatchCourseId(batchCourseId);
	}
	
	
	
	
	
	
	//// recorded subject videos
	
	@PostMapping("/recordedVideoSubjects/{recordedVideoSubjectsId}/recordedVideos")
	public Responce addNewRecordedSubjectVideo(@PathVariable("recordedVideoSubjectsId") String recordedVideoSubjectsId,@RequestBody HashMap<String, Object> recordedVideoData) {
		return recordedSubjectVideoServiceInterface.addNewRecordedSubjectVideo(recordedVideoSubjectsId, recordedVideoData);
	}
	
	@DeleteMapping("/recordedVideos/{recordedVideoId}")
	public Responce deleteRecordedSubjectVideo(@PathVariable("recordedVideoId") String recordedVideoId) {
		return recordedSubjectVideoServiceInterface.deleteRecordedSubjectVideo(recordedVideoId);
	}
	
	@PutMapping("/recordedVideos/{recordedVideoId}")
	public Responce updateRecodedSubjectVideo(@PathVariable("recordedVideoId")String recordedVideoId,@RequestBody HashMap<String, Object>  RecordedVideoData) {
		return recordedSubjectVideoServiceInterface.updateRecodedSubjectVideo(recordedVideoId, RecordedVideoData);
	}

@GetMapping("/recordedVideoSubjects/{recordedVideoSubjectsId}/recordedVideos")
	public List<Map<String, Object>> getAllRecordedSubjectVideo(@PathVariable("recordedVideoSubjectsId")String recordedVideoSubjectsId){
		return recordedSubjectVideoServiceInterface.getAllRecordedSubjectVideo(recordedVideoSubjectsId);
	}
	
	
	
	
	//batch course Placements class
	
	
	@PostMapping("/batchCourses/{batchCourseId}/Placements")
	public Responce addNewPlacement(@PathVariable("batchCourseId") String batchCourseId, @RequestBody HashMap<String , Object>placementData) throws Exception {
		
		return batchCoursePlacementsServiceInterface.addNewPlacement(batchCourseId, placementData);
	}
	
	@GetMapping("/batchCourses/{batchCourseId}/Placements")
	public List<Map<String , Object>> getAllPlacementByBatchCourseId(@PathVariable("batchCourseId") String batchCourseId) throws Exception{
		return batchCoursePlacementsServiceInterface.getAllPlacementByBatchCourseId(batchCourseId);
	}
	
	
	@PutMapping("/placements/{placementId}")
	public  Responce updateBatchCoursePlacement(@PathVariable("placementId") String placementId,@RequestBody HashMap<String, Object> placementData) throws Exception {
		return batchCoursePlacementsServiceInterface.updateBatchCoursePlacement(placementId, placementData);
	}
	
	
	@DeleteMapping("/placements/{placementId}")
	public  Responce deleteBatchCoursePlacement(@PathVariable("placementId") String placementId) throws Exception {
		return batchCoursePlacementsServiceInterface.deleteBatchCoursePlacement(placementId);
	}
	
	
	
	//// batch Course PLacement image
	
	
	@PostMapping("/placements/{placementId}/placementImages")
	public Responce addNewPlacementImage(@PathVariable("placementId")String placementId,@RequestBody HashMap<String, Object> placementImgaeData) {
		return placementImageServiceInterface.addNewPlacementImage(placementId, placementImgaeData);	
		}
		
		@DeleteMapping("/placementImages/{placementImageId}")
		public Responce deletePlacementImage(@PathVariable("placementImageId") String placementimageId) {
			return placementImageServiceInterface.deletePlacementImage(placementimageId);
		}
		
		@PutMapping("/placementImages/{placementImageId}")
		public Responce updatePlacementImage(@PathVariable("placementImageId") String placementImageId,@RequestBody HashMap<String, Object> placementImageData) {
			return placementImageServiceInterface.updatePlacementImage(placementImageId, placementImageData);
		}
		
		@GetMapping("/placements/{placementId}/placementImages")
		public List<Map<String , Object>> getPlacementImate(@PathVariable("placementId")String placementId) {
			
			return placementImageServiceInterface.getPlacementImate(placementId);
		}
	
	
	
	////BATCH COURSE CLASS LINKS
	
	@PostMapping("/batchCourses/{batchCourseId}/classLinks/{batchCourseSubjectId}")
	public Responce addnewClassLink(@PathVariable("batchCourseId") String batchCourseId,@PathVariable("batchCourseSubjectId") String batchCourseSubjectId,@RequestBody HashMap<String, Object> classLinkData) throws Exception {	
		return batchCourseClassLinksServiceServicesInterface.addnewClassLink(batchCourseId, batchCourseSubjectId, classLinkData);
	}
	@GetMapping("/batchCourses/{batchCourseId}/classLinks")
	public List<Map<String, Object>> getAllClassLinksByBatchCourseId(@PathVariable("batchCourseId")String batchCourseId) throws Exception{
		
		return batchCourseClassLinksServiceServicesInterface.getAllClassLinksByBatchCourseId(batchCourseId);
	}
	
	@GetMapping("/batchCourses/{batchCourseId}/classLinks/{batchCourseSubjectId}")
	public List<Map<String , Object>> getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(@PathVariable("batchCourseId") String batchCourseId,@PathVariable("batchCourseSubjectId") String batchCourseSubjectId) throws Exception{
		
		return batchCourseClassLinksServiceServicesInterface.getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(batchCourseId, batchCourseSubjectId);
	}
 
	
	@DeleteMapping("/classLinks/{classId}")
	 public Responce deleteClassLink(@PathVariable("classId") String classId) throws Exception {
		 
		return batchCourseClassLinksServiceServicesInterface.deleteClassLink(classId);
	 }

}
