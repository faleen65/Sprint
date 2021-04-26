package com.cg.ima.service;

import com.cg.ima.entity.User;

public interface IUserService {
	/**
	 * @author Aditya
	 * @param user
	 * @return User
	 */
	User login(User user);

	/**
	 * @author Aditya
	 * @return User
	 */
	User logout();

	/**
	 * @author Aditya
	 * @param user
	 * @return User
	 */
	User addUser(User user);

	/**
	 * @author Aditya
	 * @param user
	 * @return User
	 */
	User editUser(User user);

	/**
	 * @author Aditya
	 * @param userId
	 * @return User
	 */
	User removeUser(String userId);

}
