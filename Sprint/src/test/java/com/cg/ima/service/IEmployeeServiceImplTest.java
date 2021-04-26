package com.cg.ima.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidEmployeeException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(IEmployeeServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IEmployeeServiceImplTest {

	@Autowired
	private IEmployeeService eService;

	@Autowired
	private EntityManager em;

	@Test
	void testAddEmployee() {
		User user = new User("Anmol", "Anmol2099");
		Employee emp = new Employee("Anmol", "HR", "Bangalore", user);
		em.persist(emp);
		Employee emp1 = eService.addEmployee(emp);
		Assertions.assertEquals(emp1.getEmpName(), emp.getEmpName());
	}

	@Test
	void testEditEmployee() {
		User user = new User("Anmol", "Anmol2099");

		Employee emp1 = new Employee("Anmol", "Manager", "Bangalore", user);
		em.persist(emp1);
		Employee emp = new Employee("Anmol", "HR", "Bangalore", user);
		emp.setEmpId(emp1.getEmpId());
		Employee empEdit = eService.editEmployee(emp);
		System.out.println(empEdit);
		Assertions.assertEquals(empEdit.getDeptName(), "HR");
	}

	@Test
	void testGetEmployee() throws InvalidEmployeeException {
		User user = new User("Anmol", "Anmol2099");
		Employee emp = new Employee("Anmol", "HR", "Bangalore", user);
		em.persist(emp);
		Integer id = emp.getEmpId();
		Employee empFound = eService.getEmployee(id);
		Assertions.assertEquals(empFound.getEmpId(), emp.getEmpId());
	}

	@Test
	void testUpdateIsAvailable() {
		
		User user = new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad", user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();
		prop.add(p);

		Offer of = new Offer(true, LocalDate.now(), prop);
		em.persist(of);
		Offer of1=new Offer(true, LocalDate.now(), prop);
		of1.setOfferId(of.getOfferId());
		Offer updateOffer=eService.updateIsAvailable(of1);
		Assertions.assertEquals(updateOffer.getIsAvailable(), true);
	}

	@Test
	void testUpdateIsFulfilled() {

		User user = new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad", user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();
		prop.add(p);
		Requirement req = new Requirement(false, LocalDate.now(), prop);
		em.persist(req);
		Requirement req1=new Requirement(true, LocalDate.now(), prop);
		req1.setReqId(req.getReqId());
		Requirement updateReq=eService.updateIsFulfilled(req1);
		Assertions.assertEquals(updateReq.getisFulfilled(), true);
	}

	@Test
	void testUpdateIsAccepted() {
		User user = new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad", user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		em.persist(p);
		Proposal prop=new Proposal("pg", 2000, LocalDate.now(), true, null, res);
		prop.setPropId(p.getPropId());
		Proposal updatedProp=eService.updateIsAccepted(prop);
		Assertions.assertEquals(updatedProp.getIsAccepted(), true);
	}

	@Test
	void testGetAllOffers() throws InvalidEmployeeException {
		User user = new User("Anmol", "Anmol2099");
		Employee emp = new Employee("Anmol", "HR", "Bangalore", user);
		em.persist(emp);
		List<Offer> list = eService.getAllOffers(emp.getEmpId());
		Assertions.assertEquals(list.size(), 0);
	}

	@Test
	void testGetAllRequirements() throws InvalidEmployeeException {
		User user = new User("Anmol", "Anmol2099");
		Employee emp = new Employee("Anmol", "HR", "Bangalore", user);
		em.persist(emp);
		List<Requirement> list = eService.getAllRequirements(emp.getEmpId());
		Assertions.assertEquals(list.size(), 0);
	}

}
