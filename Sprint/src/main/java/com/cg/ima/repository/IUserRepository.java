package com.cg.ima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	User findByUserId(String userId);

	void deleteByUserId(String userId);

	@Query("from User where userId=:userid and password=:pswrd")
	User findByUser(@Param("userid") String userId, @Param("pswrd") String password);

}