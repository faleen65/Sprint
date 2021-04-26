package com.cg.ima.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ima.entity.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(IUserServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IUserServiceImplTest {

	@Autowired
	private IUserService userService;

	@Autowired
	private EntityManager em;

	@Test
	public void testAddUser() {
		User user1 = new User("adityagoyal844@gmail.com", "Addy123");
		em.persist(user1);
		User userSaved = userService.addUser(user1);
		Assertions.assertEquals(userSaved.getUserId(), user1.getUserId());
	}

	@Test
	public void testEditUser() {
		User user1 = new User("adityagoyal844@gmail.com", "@ddy123");
		em.persist(user1);
		User user2 = new User("adityagoyal844@gmail.com", "@ddy456");
		User userSaved = userService.editUser(user2);
		em.persist(userSaved);
		Assertions.assertEquals(userSaved.getPassword(), "@ddy456");
	}

	@Test
	public void testRemoveUser() {
		User user1 = new User("adityagoyal844@gmail.com", "@ddy123");
		em.persist(user1);
		User userDelete = userService.removeUser("adityagoyal844@gmail.com");
		em.persist(userDelete);
		Assertions.assertEquals(userDelete.getUserId(), "adityagoyal844@gmail.com");
	}

	@Test
	public void testLogin() {
		User user1 = new User("adityagoyal199@gmail.com", "@ddy123");
		em.persist(user1);
		User userLogin = userService.login(user1);
		Assertions.assertEquals(userLogin, user1);
	}

	@Test
	public void testLogout() {
		IUserServiceImpl.userid = "adityagoyal199@gmail.com";
		User user1 = new User("adityagoyal199@gmail.com", "@ddy123");
		em.persist(user1);
		User userLogin = userService.logout();
		Assertions.assertEquals(userLogin.getUserId(), "adityagoyal199@gmail.com");
	}

}
