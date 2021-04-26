package com.cg.ima.service;

import java.time.LocalDate;
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
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidProposalException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(IProposalServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IProposalServiceImplTest {

	@Autowired
	private IProposalService eService;
	
	@Autowired
	private EntityManager em;
	
	@Test
	void testAddProposal() {
		User user = new User("ABNM","ANMASF20349");
		Employee emp = new Employee("ABC","HR","Bangalore",user);
		Resource res = new Resource("anm ", "skdhb", "Service", null, "kjdv", 3400, emp);
		Proposal pro = new Proposal("Abc",25000,null,false,null,res);
		em.persist(pro);
		Proposal addPro = eService.addProposal(pro);
		Assertions.assertEquals(addPro.getPropId(), pro.getPropId());
	}

	@Test
	void testEditProposal() throws InvalidProposalException {
		User user = new User("ABNM","ANMASF20349");
		Employee emp = new Employee("ABC","HR","Bangalore",user);
		Resource res = new Resource("anm ", "skdhb", "Service", null, "kjdv", 3400, emp);
		Proposal pro = new Proposal("Abc",35000,null,false,null,res);
		em.persist(pro);
		Proposal pro1 = new Proposal("Abc",25000,null,false,null,res);
		pro1.setPropId(pro.getPropId());
		Proposal editPro = eService.editProposal(pro);
		Assertions.assertEquals(editPro.getAmount(), 35000);
	}

	@Test
	void testGetProposal() throws InvalidProposalException {
		User user = new User("ABNM","ANMASF20349");
		Employee emp = new Employee("ABC","HR","Bangalore",user);
		Resource res = new Resource("anm ", "skdhb", "Service", null, "kjdv", 3400, emp);
		Proposal pro = new Proposal("Abc",25000,null,false,null,res);
		em.persist(pro);
		Integer id = pro.getPropId();
		Proposal proFound = eService.getProposal(id);
		Assertions.assertEquals(proFound.getPropId(), pro.getPropId());
	}

	@Test
	void testRemoveProposal() throws InvalidProposalException {
		User user = new User("ABNM","ANMASF20349");
		Employee emp = new Employee("ABC","HR","Bangalore",user);
		Resource res = new Resource("anm ", "skdhb", "Service", null, "kjdv", 3400, emp);
		Proposal pro1 = new Proposal("Abc",25000,null,false,null,res);
		em.persist(pro1);
		Integer id = pro1.getPropId();	
		Proposal prodel = eService.removeProposal(id);
		Assertions.assertEquals(prodel.getPropId(),pro1.getPropId());
	}

	@Test
	void testGetAllProposals() {
		User user = new User("ABNM","ANMASF20349");
		Employee emp = new Employee("ABC","HR","Bangalore",user);
		Resource res = new Resource("anm ", "skdhb", "Service", LocalDate.now(), "kjdv", 3400, emp);
		Proposal pro1 = new Proposal("Abc",25000,LocalDate.now(),false,null,res);
		em.persist(pro1);
		List<Proposal> List = eService.getAllProposals();
		Assertions.assertEquals(List.size(),1);
	}

}
