package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juzzIt.EducationProject.Entity.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, String> {

}
