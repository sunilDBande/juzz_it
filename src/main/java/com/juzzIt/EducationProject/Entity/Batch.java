package com.juzzIt.EducationProject.Entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Batch{
  @Id
  @Column(name="BATCH_ID")
	private String batchId;
  @Column(name="BATCH_NAME")
	private String batchName;
  @Column(name="TEMP_DELETE")
  private String temp_delete;
  
  @OneToMany(mappedBy = "batch",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
  private List<SubBatch> subBatch;
		
	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Batch(String batchId, String batchName, String temp_delete, List<SubBatch> subBatch) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.temp_delete = temp_delete;
		this.subBatch = subBatch;
	}


	public String getBatchId() {
		return batchId;
	}


	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}


	public String getBatchName() {
		return batchName;
	}


	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}


	public String getTemp_delete() {
		return temp_delete;
	}


	public void setTemp_delete(String temp_delete) {
		this.temp_delete = temp_delete;
	}


	public List<SubBatch> getSubBatch() {
		return subBatch;
	}


	public void setSubBatch(List<SubBatch> subBatch) {
		this.subBatch = subBatch;
	}


	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", temp_delete=" + temp_delete + ", subBatch="
				+ subBatch + "]";
	}
	
	
	
	


}