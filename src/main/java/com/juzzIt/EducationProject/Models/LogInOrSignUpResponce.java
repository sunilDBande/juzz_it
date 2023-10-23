package com.juzzIt.EducationProject.Models;




public class LogInOrSignUpResponce {
	
	
	private String jwtTokan;
	private String userId;
	private String userType;
	private boolean successStatus;
	private String massage;
	public LogInOrSignUpResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LogInOrSignUpResponce(String jwtTokan, String userId, String userType, boolean successStatus,
			String massage) {
		super();
		this.jwtTokan = jwtTokan;
		this.userId = userId;
		this.userType = userType;
		this.successStatus = successStatus;
		this.massage = massage;
	}
	@Override
	public String toString() {
		return "LogInOrSignUpResponce [jwtTokan=" + jwtTokan + ", userId=" + userId + ", userType=" + userType
				+ ", successStatus=" + successStatus + ", massage=" + massage + "]";
	}
	public String getJwtTokan() {
		return jwtTokan;
	}
	public void setJwtTokan(String jwtTokan) {
		this.jwtTokan = jwtTokan;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isSuccessStatus() {
		return successStatus;
	}
	public void setSuccessStatus(boolean successStatus) {
		this.successStatus = successStatus;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}

}
