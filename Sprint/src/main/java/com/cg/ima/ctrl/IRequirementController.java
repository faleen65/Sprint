package com.cg.ima.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidRequirementException;
import com.cg.ima.service.IRequirementService;

@RestController
@RequestMapping("/requirement")
public class IRequirementController {

	@Autowired
	private IRequirementService rService;

	@RequestMapping("/hello")
	public String greet() {
		return "Hello from student controller";
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public ResponseEntity<Requirement> addRequirement(@RequestBody Requirement req) {
		System.out.println("req data = " + req);
		Requirement requirement = rService.addRequirement(req);
		System.out.println("req data = " + requirement);

		return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Requirement> removeRequirement(@PathVariable("id") Integer id) {
			Requirement requirement = rService.removeRequirement(id);
			return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Requirement>> getAllRequirement() {
		List<Requirement> requirementList = rService.getAllRequirements();
		return new ResponseEntity<List<Requirement>>(requirementList, HttpStatus.OK);
	}

	@GetMapping("/getby/{cat}/{type}")
	public ResponseEntity<List<Requirement>> getAllRequirements(@PathVariable("cat") String category,
			@PathVariable("type") String type) {
		List<Requirement> requirementList = rService.getAllRequirements(category, type);
		return new ResponseEntity<List<Requirement>>(requirementList, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Requirement> getRequirement(@PathVariable("id") int reqId)
			throws InvalidRequirementException {
		Requirement requirement = rService.getRequirement(reqId);
		return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Requirement> editRequirement(@PathVariable("id") int id, @RequestBody boolean bool)
			throws InvalidRequirementException {
		Requirement requirement = rService.getRequirement(id);
		requirement.setisFulfilled(bool);
		Requirement req = rService.editRequirement(requirement);
		return new ResponseEntity<Requirement>(req, HttpStatus.OK);
	}
}
