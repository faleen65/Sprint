package com.cg.ima.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidEmployeeException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(IResourceServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IResourceServiceImplTest {

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private EntityManager em;

	@Test
	public void testgetAllResources() {
		List<Resource> res1 = new ArrayList<Resource>();
		User user1 = new User("adityagoyal123", "Addy1999934");
		Employee emp1 = new Employee("Aditya", "HR", "Panchkula", user1);
		Resource e = new Resource("A", "B", "help", LocalDate.now(), "buy", 10000, emp1);
		res1.add(e);
		em.persist(e);
		List<Resource> get = resourceService.getAllResources("help", "buy");
		e.equals(get);
//		Assertions.assertEquals(e, get);
	}

	@Test
	public void testgetAllResourcesByEmpId() throws InvalidEmployeeException {
		List<Resource> res1 = new ArrayList<Resource>();
		User user1 = new User("adityagoyal456", "Addy1999@");
		Employee emp1 = new Employee("Aditya", "HR", "Panchkula", user1);
		Resource e = new Resource("A", "B", "help", LocalDate.now(), "buy", 10000, emp1);
		res1.add(e);
		em.persist(e);
		List<Resource> get = resourceService.getAllResources(emp1.getEmpId());
		e.equals(get);
//		Assertions.assertEquals(e, get);
	}

}
