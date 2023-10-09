package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="SUBBATCH")
public class SubBatch implements Serializable{

	@Id
	@Column(name="SUBBATCH_ID")
	private   String subBatchId;
	@Column(name="SUBBATCH_NAME")
	private String subBatchName;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_ID")
    private Batch batch;
	
	@OneToMany(mappedBy = "subBatch",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<BatchCourse>  batchCourse;

	public SubBatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSubBatchId() {
		return subBatchId;
	}

	public void setSubBatchId(String subBatchId) {
		this.subBatchId = subBatchId;
	}

	public String getSubBatchName() {
		return subBatchName;
	}

	public void setSubBatchName(String subBatchName) {
		this.subBatchName = subBatchName;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public List<BatchCourse> getBatchCourse() {
		return batchCourse;
	}

	public void setBatchCourse(List<BatchCourse> batchCourse) {
		this.batchCourse = batchCourse;
	}

	public SubBatch(String subBatchId, String subBatchName, Batch batch, List<BatchCourse> batchCourse) {
		super();
		this.subBatchId = subBatchId;
		this.subBatchName = subBatchName;
		this.batch = batch;
		this.batchCourse = batchCourse;
	}

	@Override
	public String toString() {
		return "SubBatch [subBatchId=" + subBatchId + ", subBatchName=" + subBatchName + ", batch=" + batch
				+ ", batchCourse=" + batchCourse + "]";
	}
}
