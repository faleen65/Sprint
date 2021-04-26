package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidRequirementException;
import com.cg.ima.repository.IRequirementRepository;

@Service
@Transactional
public class IRequirementServiceImpl implements IRequirementService {
	
	private Logger logger = LoggerFactory.getLogger(IRequirementServiceImpl.class);

	@Autowired
	private IRequirementRepository rDao;

	/**
	 * This function is for adding new requirements.
	 */
	@Override
	public Requirement addRequirement(Requirement requirement) {
		logger.info("********Adding requirement: "+requirement);
		Requirement req = rDao.save(requirement);
		logger.info("********Added requirement: "+requirement);
		return req;
	}

	/**
	 * This function is for editing requirements.
	 */
	@Override
	public Requirement editRequirement(Requirement requirement) {
		logger.info("********Editing requirement: "+requirement);
		Optional<Requirement> opt = rDao.findById(requirement.getReqId());
		if (!opt.isPresent()) {
			throw new InvalidRequirementException("Requirement not found for id= " + requirement.getReqId());
		}
		Requirement req = opt.get();
		req.setisFulfilled(requirement.getisFulfilled());
		logger.info("********Edited requirement: "+req);
		return req;
	}

	/**
	 * This function is for get requirement based on requirementid.
	 */
	@Override
	public Requirement getRequirement(int requirementId) throws InvalidRequirementException {
		logger.info("********Finding requirement by id: "+requirementId);
		Optional<Requirement> opt = rDao.findById(requirementId);
		if (!opt.isPresent()) {
			throw new InvalidRequirementException("Requirement not found for id =" + requirementId);
		}
		Requirement requirement = opt.get();
		return requirement;

	}

	/**
	 * This function is for removing requirements.
	 */
	@Override
	public Requirement removeRequirement(int requirementId) {
		logger.info("********Deleting requirement by id: "+requirementId);
		Requirement requirement = getRequirement(requirementId);
		rDao.deleteById(requirementId);
		logger.info("********Deleted requirement: "+requirement);
		return requirement;
	}

	/**
	 * This function is for getting all requirements.
	 */
	@Override
	public List<Requirement> getAllRequirements() {
		logger.info("********Finding all requirement ");
		List<Requirement> requirementList = rDao.findAll();
		return requirementList;

	}

	/**
	 * This function is for getting requirements based on category and type.
	 */
	@Override
	public List<Requirement> getAllRequirements(String category, String type) {
		logger.info("********Finding requirement by category: "+category+" and type: "+type);
		List<Requirement> requirementList = rDao.findBy(category, type);
		if (requirementList.isEmpty()) {
			throw new InvalidRequirementException(
					"No requirement found for category =" + category + "and type = " + type);
		}
		return requirementList;
	}

}