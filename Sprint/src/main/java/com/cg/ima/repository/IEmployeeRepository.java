package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidEmployeeException;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {


	@Query(value = "select Offer.offer_Id as offerId, Offer.available_Upto as availableUpto, Offer.is_Available as isAvailable, Proposal.emp_Id as empId from Proposal Proposal, Offer OFFER where OFFER.offer_Id=Proposal.offer_Id and Proposal.emp_Id=:empId", nativeQuery = true)
	List<Offer> fetchAllOffers(@Param("empId") int empId) throws InvalidEmployeeException;

	@Query(value = "select Requirement.req_Id as reqId, Requirement.fulfilled_On as fulfilledOn, Requirement.is_Fulfilled as isFulfilled, Proposal.emp_Id as empId from Proposal Proposal, Requirement Requirement where Requirement.req_Id=Proposal.req_Id and Proposal.emp_Id =:empId", nativeQuery = true)
	List<Requirement> fetchAllRequirements(@Param("empId") int empId) throws InvalidEmployeeException;

	@Query("from Offer where offerId=:offerid")
	Offer getOfferById(@Param("offerid") int offerId);

	@Query("from Requirement where reqId=:reqid")
	Requirement getRequirementById(@Param("reqid") int reqId);

	@Query("from Proposal where propId=:propid")
	Proposal getProposalById(@Param("propid") int propId);
}