package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Entities implements Serializable{

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    @org.hibernate.annotations.GenericGenerator(
        name = "increment",
        strategy = "increment",
        parameters = @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    )
	@Column(name = "Entity_Id")
	private long entity_Id;
	
	@Column(name = "Entity_Name")
	private String entity_Name;
	
	@Column(name = "Entity_SubName")
	private String entity_SubName;
	
	@Column(name = "Entity_Count")
	private long entity_Count;

	public Entities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entities(long entity_Id, String entity_Name, String entity_SubName, long entity_Count) {
		super();
		this.entity_Id = entity_Id;
		this.entity_Name = entity_Name;
		this.entity_SubName = entity_SubName;
		this.entity_Count = entity_Count;
	}

	public long getEntity_Id() {
		return entity_Id;
	}

	public void setEntity_Id(long entity_Id) {
		this.entity_Id = entity_Id;
	}

	public String getEntity_Name() {
		return entity_Name;
	}

	public void setEntity_Name(String entity_Name) {
		this.entity_Name = entity_Name;
	}

	public String getEntity_SubName() {
		return entity_SubName;
	}

	public void setEntity_SubName(String entity_SubName) {
		this.entity_SubName = entity_SubName;
	}

	

	public long getEntity_Count() {
		return entity_Count;
	}

	public void setEntity_Count(long entity_Count) {
		this.entity_Count = entity_Count;
	}

	@Override
	public String toString() {
		return "Entities [entity_Id=" + entity_Id + ", entity_Name=" + entity_Name + ", entity_SubName="
				+ entity_SubName + ", entity_Count=" + entity_Count + "]";
	}

	
	
}
