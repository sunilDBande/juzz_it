package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;

import com.juzzIt.EducationProject.Entity.Admin;
import com.juzzIt.EducationProject.Models.Responce;

public interface AdminDaoInterface {
	public Admin createAdminAcount( Admin admin);
	public Responce deleteAdminAccount(String adminId);
	public Responce updateAdminAccount(String adminId,HashMap<String, Object> adminData);
	public Admin getAdminByAdminId(String AdminId);
	public boolean iSexists(String email);
	public Admin getAdminByEmail(String email);
	
}
