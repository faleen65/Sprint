package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.Offer;
import com.cg.ima.exception.InvalidOfferException;
import com.cg.ima.repository.IOfferRepository;

@Service
@Transactional

public class IOfferServiceImpl implements IOfferService {
	
	private Logger logger = LoggerFactory.getLogger(IOfferServiceImpl.class);
	@Autowired
	private IOfferRepository edao;

	/**
	 * This function is for adding new offers.
	 */
	@Override
	public Offer addOffer(Offer offer) {
		logger.info("********Adding Offer" +offer);
		Offer off = edao.save(offer);
		logger.info("********Added Offer" +off);
		return off;
	}

	/**
	 * This function is for editing offers.
	 */
	@Override
	public Offer editOffer(Offer offer) {
		logger.info("********Editing Offer" +offer);
		Optional<Offer> opt = edao.findById(offer.getOfferId());
		if (!opt.isPresent()) {
			throw new InvalidOfferException("Offer not found for id = " + offer.getOfferId());

		}
		Offer off = opt.get();
		off.setIsAvailable(offer.getIsAvailable());
		logger.info("********Edited Offer" +off);
		return off;
	}

	/**
	 * This function is for get offer based on offerid.
	 */
	@Override
	public Offer getOffer(int offerId) throws InvalidOfferException {
		logger.info("********Finding Offer by id" +offerId);
		Optional<Offer> opt = edao.findById(offerId);
		if (!opt.isPresent()) {
			throw new InvalidOfferException("Offer not found for id = " + offerId);

		}
		Offer offer = opt.get();
		return offer;

	}

	/**
	 * This function is for removing offers.
	 */
	@Override
	public Offer removeOffer(int offerId) {
		logger.info("********Deleting Offer by id" +offerId);
		Offer offer = getOffer(offerId);
		edao.deleteById(offerId);
		logger.info("********Deleted Offer" +offer);
		return offer;
	}

	/**
	 * This function is for getting all offers.
	 */
	@Override
	public List<Offer> getAllOffers() {
		logger.info("********Finding all Offer");
		List<Offer> offerList = edao.findAll();
		return offerList;

	}

	/**
	 * This function is for getting offers based on category and type.
	 */
	@Override
	public List<Offer> getAllOffers(String category, String type) {
		logger.info("********Finding Offer by category:" +category+" and type: "+type);
		List<Offer> offerList = edao.findBy(category, type);
		if (offerList.isEmpty()) {
			throw new InvalidOfferException("No offer found for category = " + category + " and type = " + type);
		}
		return offerList;
	}

}