package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Offer;

import com.cg.ima.exception.InvalidOfferException;

public interface IOfferService {
	/**
	 * @author Himanshu
	 * @param offer
	 * @return Offer
	 */
	Offer addOffer(Offer offer);

	/**
	 * @author Himanshu
	 * @param offer
	 * @return Offer
	 */

	Offer editOffer(Offer offer);

	/**
	 * @author Himanshu
	 * @param offerId
	 * @return Offer
	 * @throws InvalidOfferException
	 */

	Offer getOffer(int offerId) throws InvalidOfferException;

	/**
	 * @author Himanshu
	 * @param offerId
	 * @return Offer
	 * @throws InvalidOfferException
	 */

	Offer removeOffer(int offerId) throws InvalidOfferException;

	/**
	 * @author Himanshu
	 * @return List
	 */
	List<Offer> getAllOffers();

	/**
	 * @author Himanshu
	 * @return List
	 */

	List<Offer> getAllOffers(String category, String type);
}
