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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.Offer;
import com.cg.ima.exception.InvalidOfferException;
import com.cg.ima.service.IOfferService;

@RestController
@RequestMapping("/offer")
@Validated
public class IOfferController {
	@Autowired
	private IOfferService oServive;

	@RequestMapping("/hello")
	public String greet() {
		return "Hello from student controller";
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public ResponseEntity<Offer> addOffer(@RequestBody Offer off) {
		System.out.println("req data = " + off);
		Offer offer = oServive.addOffer(off);
		System.out.println("req data = " + offer);

		return new ResponseEntity<>(offer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Offer> removeOffer(@PathVariable("id") Integer id) {
		Offer off = oServive.removeOffer(id);
		return new ResponseEntity<>(off, HttpStatus.OK);

	}

	@GetMapping("/getall")
	public ResponseEntity<List<Offer>> getAllOffer() {
		List<Offer> offerList = oServive.getAllOffers();
		return new ResponseEntity<>(offerList, HttpStatus.OK);
	}

	@GetMapping("/getby/{cat}/{type}")
	public ResponseEntity<List<Offer>> getAllOffers(@PathVariable("cat") String category,
			@PathVariable("type") String type) {
		List<Offer> offerList = oServive.getAllOffers(category, type);
		return new ResponseEntity<>(offerList, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Offer> getOffer(@PathVariable("id") int offerId) throws InvalidOfferException {
		Offer offer = oServive.getOffer(offerId);
		return new ResponseEntity<>(offer, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Offer> editOffer(@PathVariable("id") int id, @RequestBody boolean bool)
			throws InvalidOfferException {
		Offer offer = oServive.getOffer(id);
		offer.setIsAvailable(bool);
		Offer off = oServive.editOffer(offer);
		return new ResponseEntity<>(off, HttpStatus.OK);

	}
}
