package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Proposal;
import com.cg.ima.exception.InvalidProposalException;

public interface IProposalService {

	/**
	 * @author Aastha
	 * @param prop
	 * @return Proposal
	 */
	Proposal addProposal(Proposal prop);

	/**
	 * @author Aastha
	 * @param prop
	 * @return Proposal
	 * @throws InvalidProposalException
	 */
	Proposal editProposal(Proposal prop) throws InvalidProposalException;

	/**
	 * @author Aastha
	 * @param propId
	 * @return Proposal
	 * @throws InvalidProposalException
	 */
	Proposal getProposal(int propId) throws InvalidProposalException;

	/**
	 * @author Aastha
	 * @param propId
	 * @return Proposal
	 * @throws InvalidProposalException
	 */
	Proposal removeProposal(int propId) throws InvalidProposalException;

	/**
	 * @author Aastha
	 * @param prop
	 * @return List<Proposal>
	 */
	List<Proposal> getAllProposals();
}
