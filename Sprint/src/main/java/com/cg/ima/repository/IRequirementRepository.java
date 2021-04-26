package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Requirement;

@Repository
public interface IRequirementRepository extends JpaRepository<Requirement,Integer> {
	@Query(value ="select	 \"REQUIREMENT\".\"IS_FULFILLED\" as \"IS_FULFILLED\",\r\n" + 
			"	 \"REQUIREMENT\".\"FULFILLED_ON\" as \"FULFILLED_ON\",\r\n" + 
			"	 \"REQUIREMENT\".\"REQ_ID\" as \"REQ_ID\" \r\n" + 
			" from	 \"RES_TEST\" \"RES_TEST\",\r\n" + 
			"	 \"REQUIREMENT\" \"REQUIREMENT\" \r\n" + 
			" where 	 \"RES_TEST\".\"CATEGORY\" =:cat \r\n" + 
			"   and	 \"RES_TEST\".\"TYPE\" =:type", nativeQuery = true)
	List<Requirement> findBy(@Param("cat") String category, @Param("type") String type);
	
	

}


	


