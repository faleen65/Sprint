package com.cg.ima.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.Proposal;
import com.cg.ima.exception.InvalidProposalException;
import com.cg.ima.service.IProposalService;

@RestController
@RequestMapping("/proposals")
@Validated
public class IProposalController {

	@Autowired
	private IProposalService pService;

	@RequestMapping("/hello")
	public String greet() {
		return "Hello from proposal controller";
	}

	@PostMapping("/add")
	public ResponseEntity<Proposal> addProposal(@RequestBody Proposal prop) {
		Proposal addedProp = pService.addProposal(prop);
		if (prop == null) {
			return new ResponseEntity("Sorry! Could not Add Proposal ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proposal>(addedProp, HttpStatus.OK);
	}

	@PutMapping("/edit/{propid}")
	public ResponseEntity<Proposal> editProposal(@RequestBody Proposal prop) throws InvalidProposalException {
		Proposal updatedProp = pService.editProposal(prop);
		if (updatedProp == null) {
			return new ResponseEntity("Sorry! Could not Update Proposal ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proposal>(updatedProp, HttpStatus.OK);
	}

	@GetMapping("/find/{propid}")
	public ResponseEntity<Proposal> findProposal(@PathVariable("propid") Integer propId)
			throws InvalidProposalException {
		Proposal prop = pService.getProposal(propId);
		if (prop == null) {
			return new ResponseEntity("Sorry! Proposal Not Found for " + propId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proposal>(prop, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{propid}")
	public ResponseEntity<Proposal> deleteProposal(@PathVariable("propid") Integer propId)
			throws InvalidProposalException {
		Proposal deletedProp = pService.removeProposal(propId);
		if (deletedProp == null) {
			return new ResponseEntity("Sorry! Could not Delete Proposal ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proposal>(deletedProp, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Proposal>> getAllProposals() {
		List<Proposal> proposals = pService.getAllProposals();
		if (proposals.isEmpty()) {
			return new ResponseEntity("Sorry! No proposals available", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Proposal>>(proposals, HttpStatus.OK);
	}

}
