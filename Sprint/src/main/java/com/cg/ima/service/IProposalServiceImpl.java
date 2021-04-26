package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.ima.entity.Proposal;
import com.cg.ima.exception.InvalidProposalException;
import com.cg.ima.repository.IProposalRepository;

@Service
@Transactional
public class IProposalServiceImpl implements IProposalService {
	
	private Logger logger = LoggerFactory.getLogger(IProposalServiceImpl.class);
	
	@Autowired
	private IProposalRepository pRepo;

	/**
	 * This function is for adding proposals.
	 */
	@Override
	public Proposal addProposal(Proposal proposal) {
		logger.info("********Adding proposal: "+proposal);
		Proposal prop = pRepo.save(proposal);
		logger.info("********Added proposal: "+prop);
		return prop;
	}

	/**
	 * This function is for editing proposals.
	 */
	@Override
	public Proposal editProposal(Proposal proposal) throws InvalidProposalException {
		logger.info("********Editing proposal: "+proposal);
		int propId = proposal.getPropId();
		Optional<Proposal> opt = pRepo.findById(propId);
		if (!opt.isPresent()) {
			throw new InvalidProposalException("Proposal not found for id: " + propId);
		}
		Proposal prop = opt.get();
		prop.setAmount(prop.getAmount());
		logger.info("********Edited proposal: "+prop);
		return prop;
	}

	/**
	 * This function is for getting proposals based on proposalid.
	 */
	@Override
	public Proposal getProposal(int propId) throws InvalidProposalException {
		logger.info("********Finding proposal by id: "+propId);
		Optional<Proposal> opt = pRepo.findById(propId);
		if (!opt.isPresent()) {
			throw new InvalidProposalException("Proposal not found for id: " + propId);
		}
		Proposal prop = opt.get();
		return prop;
	}

	/**
	 * This function is for removing proposals.
	 */
	@Override
	public Proposal removeProposal(int propId) throws InvalidProposalException {
		logger.info("********Deleting proposal by id: "+propId);
		Optional<Proposal> opt = pRepo.findById(propId);
		if (!opt.isPresent()) {
			throw new InvalidProposalException("Proposal not found for id: " + propId);
		}
		Proposal prop = opt.get();
		pRepo.delete(prop);
		logger.info("********Deleted proposal: "+prop);
		return prop;
	}

	/**
	 * This function is for getting all proposals.
	 */
	@Override
	public List<Proposal> getAllProposals() {
		logger.info("********Finding all proposal: ");
		List<Proposal> list = pRepo.findAll();
		return list;
	}

}