package com.juzzIt.EducationProject.Models;

import org.springframework.stereotype.Component;

@Component
public class Responce {
			
			private boolean status;
			private String massege;
			
			
			
			public Responce() {
				super();
				// TODO Auto-generated constructor stub
			}
			
			
			public Responce(boolean status, String massege) {
				super();
				this.status = status;
				this.massege = massege;
			}
			
			
			public boolean isStatus() {
				return status;
			}
			public void setStatus(boolean status) {
				this.status = status;
			}
			public String getMassege() {
				return massege;
			}
			public void setMassege(String massege) {
				this.massege = massege;
			}
			@Override
			public String toString() {
				return "Responce [status=" + status + ", massege=" + massege + "]";
			}

}
