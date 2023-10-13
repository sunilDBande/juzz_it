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
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseStudentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseSubjectServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BatchServiceInterface;
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
