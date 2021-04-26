package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidEmployeeException;

public interface IEmployeeService {
	/**
	 * @author Anmol
	 * @param emp
	 * @return Employee
	 */
	Employee addEmployee(Employee emp);

	/**
	 * @author Anmol
	 * @param emp
	 * @return Employee
	 */
	Employee editEmployee(Employee emp);

	/**
	 * @author Anmol
	 * @param empId
	 * @return Employee
	 */
	Employee getEmployee(int empId) throws InvalidEmployeeException;

	/**
	 * @author Anmol
	 * @param offer
	 * @return Offer
	 */
	Offer updateIsAvailable(Offer offer);

	/**
	 * @author Anmol
	 * @param req
	 * @return Requirement
	 */
	Requirement updateIsFulfilled(Requirement req);

	/**
	 * @author Anmol
	 * @param prop
	 * @return Proposal
	 */
	Proposal updateIsAccepted(Proposal prop);

	/**
	 * @author Anmol
	 * @param empId
	 * @return List<Offer>
	 * @throws InvalidEmployeeException
	 */
	List<Offer> getAllOffers(int empId) throws InvalidEmployeeException;

	/**
	 * @author Anmol
	 * @param empId
	 * @return List<Requirement>
	 * @throws InvalidEmployeeException
	 */
	List<Requirement> getAllRequirements(int empId) throws InvalidEmployeeException;
}
