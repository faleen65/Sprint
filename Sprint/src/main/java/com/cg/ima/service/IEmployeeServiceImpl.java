package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidEmployeeException;
import com.cg.ima.repository.IEmployeeRepository;

@Service
@Transactional
public class IEmployeeServiceImpl implements IEmployeeService {

	private Logger logger = LoggerFactory.getLogger(IEmployeeServiceImpl.class);

	@Autowired
	private IEmployeeRepository eDao;

	/**
	 * This function is for adding new employees.
	 */

	@Override
	public Employee addEmployee(Employee emp) {
		logger.info("********Adding Employee" +emp);
		Employee emp1 = eDao.save(emp);
		logger.info("********Added Employee" +emp1);

		return emp1;
	}

	/**
	 * This function is to edit the employee's department name.
	 */

	@Override
	public Employee editEmployee(Employee emp) {
		logger.info("********Editing Employee" +emp);
		Optional<Employee> opt = eDao.findById(emp.getEmpId());
		Employee emp1 = opt.get();
		emp1.setDeptName(emp.getDeptName());
		logger.info("********Edited Employee" +emp1);

		return emp1;
	}

	/**
	 * This function is to get the details of an employee by giving the id of the
	 * employee.
	 */

	@Override
	public Employee getEmployee(int empId) throws InvalidEmployeeException {
		logger.info("******** Finding by id: " + empId);
		Optional<Employee> opt = eDao.findById(empId);
		if (!opt.isPresent()) {
			throw new InvalidEmployeeException("Employee not found for id: " + empId);
		}
		Employee emp = opt.get();
		return emp;
	}

	/**
	 * This function is to get the value to check whether the offer is available or
	 * not.
	 */

	@Override
	public Offer updateIsAvailable(Offer offer) {
		logger.info("********Updating Offer" +offer);
		Offer of1=eDao.getOfferById(offer.getOfferId());
		of1.setIsAvailable(offer.getIsAvailable());
		logger.info("********Updated Offer" +of1);
		return of1;
	}

	/**
	 * This function is to get the value to check whether the requirement is
	 * fulfilled or not.
	 */

	@Override
	public Requirement updateIsFulfilled(Requirement req) {
		logger.info("********Updating Requirement" +req);
		Requirement req1 = eDao.getRequirementById(req.getReqId());
		req1.setisFulfilled(req.getisFulfilled());
		logger.info("********Updated Requirement" +req1);
		return req1;
	}

	/**
	 * This function is to get the value to check whether the proposal is accepted
	 * or not.
	 */

	@Override
	public Proposal updateIsAccepted(Proposal prop) {
		logger.info("********Updating Proposal" +prop);
		Proposal pro = eDao.getProposalById(prop.getPropId());
		pro.setIsAccepted(prop.getIsAccepted());
		logger.info("********Updated Requirement" +pro);
		return pro;
	}

	/**
	 * This function is to get the list of all offers.
	 */

	@Override
	public List<Offer> getAllOffers(int empId) throws InvalidEmployeeException {
		logger.info("******** Finding by id: " + empId);
		Optional<Employee> opt = eDao.findById(empId);
		if (!opt.isPresent()) {
			throw new InvalidEmployeeException("Employee not found for id: " + empId);
		}
		List<Offer> list = eDao.fetchAllOffers(empId);
		return list;
	}

	/**
	 * This function is to get the list of all requirements.
	 */

	@Override
	public List<Requirement> getAllRequirements(int empId) throws InvalidEmployeeException {
		logger.info("******** Finding by id: " + empId);
		Optional<Employee> opt = eDao.findById(empId);
		if (!opt.isPresent()) {
			throw new InvalidEmployeeException("Employee not found for id: " + empId);
		}
		List<Requirement> list = eDao.fetchAllRequirements(empId);
		return list;
	}

}