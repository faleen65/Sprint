package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Resource;
import com.cg.ima.exception.InvalidEmployeeException;

public interface IResourceService {

	/**
	 * @author Aditya
	 * @param category,type
	 * @return List<Resource>
	 */
	List<Resource> getAllResources(String category, String type);

	/**
	 * @author Aditya
	 * @param empId
	 * @return List<Resource>
	 * @throws InvalidEmployeeException
	 */
	List<Resource> getAllResources(int empId) throws InvalidEmployeeException;

}
