package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Resource;
import com.cg.ima.exception.InvalidEmployeeException;
import com.cg.ima.repository.IResourceRepository;

@Service
@Transactional
public class IResourceServiceImpl implements IResourceService {
	private Logger logger = LoggerFactory.getLogger(IResourceServiceImpl.class);

	@Autowired
	private IResourceRepository resourceDao;

	/**
	 * This function is used to get all resources based on category and type
	 */
	@Override
	public List<Resource> getAllResources(String category, String type) {
		logger.info("********Resources fetched by category: " + category + " and type: " + type);
		List<Resource> getAll = resourceDao.findByCategoryAndType(category, type);
		return getAll;
	}

	/**
	 * This function is used to get all resources based on employee id
	 */
	@Override
	public List<Resource> getAllResources(int empId) throws InvalidEmployeeException {
		logger.info("Resources fetched by employee id: " + empId);
		Optional<Employee> opt = resourceDao.findEmpById(empId);
		if (!opt.isPresent()) {
			throw new InvalidEmployeeException("Employee not found for id: " + empId);
		}
		List<Resource> res1 = resourceDao.getByEmpId(empId);
		return res1;
	}

}
