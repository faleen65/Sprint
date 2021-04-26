package com.cg.ima.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.User;
import com.cg.ima.repository.IUserRepository;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {
	private Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);

	static String userid = "";

	@Autowired
	private IUserRepository userDao;

	/**
	 * This function is used to login and create a session
	 */
	@Override
	public User login(User user) {
		User user1 = userDao.findByUser(user.getUserId(), user.getPassword());
		userid = user1.getUserId();
		if (user1 != null)
			logger.info("********"+user.getUserId() + "loggedin");
		return user1;
	}

	/**
	 * This function is used to logout and invalidate a session
	 */
	@Override
	public User logout() {
		if (userid != null)
			logger.info("********"+userid + "loggedout");
		User user2 = userDao.findByUserId(userid);
		userid = "";
		return user2;
	}

	/**
	 * This function is used to add a User
	 */
	@Override
	public User addUser(User user) {
		logger.info("********Adding user" +user);
		User user1 = userDao.save(user);
		logger.info("********User added" + user1);
		return user1;
	}

	/**
	 * This function is used to update a present user
	 */
	@Override
	public User editUser(User user) {
		logger.info("********Updating User" +user);
		User user1 = userDao.findByUserId(user.getUserId());
		user1.setPassword(user.getPassword());
		logger.info("********User updated" + user1);
		return user1;
	}

	/**
	 * This function is used to delete a user
	 */
	@Override
	public User removeUser(String userId) {
		logger.info("********Deleting User of id" +userId);
		User user1 = userDao.findByUserId(userId);
		userDao.deleteByUserId(userId);
		logger.info("********User deleted" + user1);
		return user1;

	}

}
