package com.cg.ima.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.Resource;
import com.cg.ima.exception.InvalidEmployeeException;
import com.cg.ima.service.IResourceService;

@RestController
@RequestMapping("/resource")
@Validated
public class IResourceController {

	@Autowired
	private IResourceService resourceService;

	@GetMapping("/by/empid/{empid}")
	public List<Resource> findByEmpId(@PathVariable("empid") @Valid int empId) throws InvalidEmployeeException {
		List<Resource> list = resourceService.getAllResources(empId);
		return list;
	}

	@GetMapping("/by/category/type/{category}/{type}")
	public List<Resource> findAll(@PathVariable("category") @Valid String category,
			@PathVariable("type") @Valid String type) {
		List<Resource> list = resourceService.getAllResources(category, type);
		return list;
	}

}
