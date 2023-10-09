package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.Entity.Entities;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.EntitiesRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class EntityDaoImpl implements EntityDao {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EntitiesRepository entitiesRepository;

	@Autowired
	private Responce reponse;

	@SuppressWarnings("null")
	@Override
	public List<Entities> returnCourseName(HashMap<String, Object> data) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entities> createQuery = criteriaBuilder.createQuery(Entities.class);
		Root<Entities> root = createQuery.from(Entities.class);

		Predicate conditionForCourName = criteriaBuilder.equal(root.get("entity_Name"), data.get("Entity_Name"));
		createQuery.select(root).where(conditionForCourName);
		List<Entities> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Entities> resultList1 = null;
		System.out.println("1--> " + resultList);
		System.out.println("**111");
		if (resultList.isEmpty()) {
			System.out.println("**222");
			Entities entities = new Entities();
			entities.setEntity_Count(0);
			entities.setEntity_Name((String) data.get("Entity_Name"));
			entities.setEntity_SubName((String) data.get("Entity_SubName"));
			entitiesRepository.save(entities);
			CriteriaBuilder criteriaBuilder1 = entityManager.getCriteriaBuilder();
			CriteriaQuery<Entities> createQuery1 = criteriaBuilder1.createQuery(Entities.class);
			Root<Entities> root1 = createQuery1.from(Entities.class);
			Predicate conditionForCourName1 = criteriaBuilder1.equal(root1.get("entity_Name"),
					entities.getEntity_Name());
			createQuery1.select(root1).where(conditionForCourName1);
			resultList1 = entityManager.createQuery(createQuery1).getResultList();
			System.out.println("2--> " + resultList1);
		}

		return resultList.size()!=0 ? resultList : resultList1;
	}

	@Override
	public void updateCountForCourseTable(HashMap<String, Object> data) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entities> createQuery = criteriaBuilder.createQuery(Entities.class);
		Root<Entities> root = createQuery.from(Entities.class);

		Predicate conditionForCourName = criteriaBuilder.equal(root.get("entity_Name"), data.get("Entity_Name"));
		Predicate condition = criteriaBuilder.and(conditionForCourName);
		createQuery.select(root).where(condition);
		List<Entities> resultList = entityManager.createQuery(createQuery).getResultList();
		if (!resultList.isEmpty()) {
			Entities entities = resultList.get(0);
			if (entities == null) {
				reponse.setStatus(false);
				reponse.setMassege("Id should not be empty...");
			} else {
				entities.setEntity_Count((long) data.get("count"));
				entitiesRepository.save(entities);
			}
		} else {
			reponse.setStatus(false);
			reponse.setMassege("Id should not be empty...");
		}
	}

}
/*
 * must needed queries drop table course; insert into entities
 * values(1,0,"Course", "COUR"); delete from entities where
 * entity_name="Course"; select * from entities; select * from course;
 * 
 */
