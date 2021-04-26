package com.cg.ima.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidEmployeeException;
import com.cg.ima.service.IEmployeeService;

@RestController
@RequestMapping("/emp")
@Validated
public class IEmployeeController {

	@Autowired
	private IEmployeeService eService;

	@RequestMapping("/greet")
	public String greet() {
		return "Hello from greet()";
	}

	@PostMapping("/addEmp")
	public Employee addEmployee(@RequestBody @Valid Employee emp) {
		Employee addEmp = eService.addEmployee(emp);
		return addEmp;
	}

	@PostMapping("/editEmp/{empId}")
	public Employee editEmployee(@PathVariable("empId") Integer empId, @RequestBody @Valid String string)
			throws InvalidEmployeeException {
		Employee emp = getEmployee(empId);
		emp.setDeptName(string);
		return eService.editEmployee(emp);
	}

	@GetMapping("/by/id/{empId}")
	public Employee getEmployee(@PathVariable("empId") Integer empId) throws InvalidEmployeeException {
		Employee emp = eService.getEmployee(empId);
		return emp;
	}

	@PutMapping("/offeravail")
	public Offer updateIsAvailable(@RequestBody @Valid Offer offer) {
		Offer offer2 = eService.updateIsAvailable(offer);
		return offer2;
	}

	@PutMapping("/reqfulfill")
	public Requirement updateIsFulfilled(@RequestBody @Valid Requirement req) {
		Requirement req2 = eService.updateIsFulfilled(req);
		return req2;
	}

	@PutMapping("/proaccept")
	public Proposal updateIsAccepted(@RequestBody @Valid Proposal pro) {
		Proposal pro2 = eService.updateIsAccepted(pro);
		return pro2;
	}

	@GetMapping("/Offers/id/{empId}")
	public List<Offer> getAllOffers(@PathVariable("empId") Integer empId) throws InvalidEmployeeException {
		List<Offer> offList = eService.getAllOffers(empId);
		return offList;
	}

	@GetMapping("/Requirement/id/{empId}")
	public List<Requirement> getAllRequirements(@PathVariable("empId") Integer empId) throws InvalidEmployeeException {
		List<Requirement> reqList = eService.getAllRequirements(empId);
		return reqList;
	}

}