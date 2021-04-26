package com.cg.ima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidRequirementException.class)
	public String handlesInvalidRequirement(InvalidRequirementException e) {
		return e.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidEmployeeException.class)
	public String handlesInvalidEmployee(InvalidEmployeeException e) {
		return e.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidOfferException.class)
	public String handlesInvalidOffer(InvalidOfferException e) {
		return e.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidProposalException.class)
	public String handlesInvalidProposal(InvalidProposalException e) {
		return e.getMessage();
	}

}
