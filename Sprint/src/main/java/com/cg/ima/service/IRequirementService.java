package com.cg.ima.service;

import java.util.List;
import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidRequirementException;

public interface IRequirementService {
	/**
	 * @author Faleen
	 * @param requirement
	 * @return Requirement
	 */

	Requirement addRequirement(Requirement requirement);

	/**
	 * @author Faleen
	 * @param requirement
	 * @return Requirement
	 */

	Requirement editRequirement(Requirement requirement);

	/**
	 * @author Faleen
	 * @param requirement
	 * @return Requirement *@throws InvalidRequirementException
	 */

	Requirement getRequirement(int requirementId) throws InvalidRequirementException;

	/**
	 * @author Faleen
	 * @param requirement
	 * @return Requirement *@throws InvalidRequirementException
	 */

	Requirement removeRequirement(int requirementId) throws InvalidRequirementException;

	/**
	 * author Faleen
	 * 
	 * @return List
	 */
	List<Requirement> getAllRequirements();

	/**
	 * author Faleen
	 * 
	 * @return List
	 */

	List<Requirement> getAllRequirements(String category, String type);
}