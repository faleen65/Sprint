package com.cg.ima.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.User;
import com.cg.ima.service.IUserService;

@RestController
@RequestMapping("/user")
@Validated
public class IUserController {
	private Logger logger = LoggerFactory.getLogger(IUserController.class);

	@Autowired
	private IUserService userService;

	@PostMapping("/login")
	public User createSession(@RequestBody @Valid User user, @Param("note") @Valid String note,
			HttpServletRequest request) {
		User loginUser = userService.login(user);
		if (loginUser == null) {
			logger.debug("Sorry! userId or password is incorrect");
		}
		List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
		if (notes == null) {
			notes = new ArrayList<>();
		}
		notes.add(note);
		System.out.println("notes= " + notes);
		request.getSession().setAttribute("NOTES_SESSION", notes);
		return loginUser;
	}

	@PostMapping("/invalidate/session")
	public String destroySession(HttpServletRequest request) {
		User logoutUser = userService.logout();
		if (logoutUser == null) {
			return "Not Loggedin";
		}
		logger.debug("Invalidating session");
		request.getSession().invalidate();
		return "Session invalidated";
	}

	@PostMapping("/userAdd")
	public User addsUser(@RequestBody @Valid User user) {
		User addedUser = userService.addUser(user);
		if (addedUser == null) {
			logger.debug("User cannot be added");
		}
		return addedUser;
	}

	@PutMapping("/userUpdate")
	public User updateUser(@RequestBody @Valid User user) {
		User updatedUser = userService.editUser(user);
		if (updatedUser == null) {
			logger.debug("User cannot be updated");
		}
		return updatedUser;
	}

	@DeleteMapping("/userDelete/{userId}")
	public User deleteUser(@PathVariable("userId") @Valid String userId) {
		User deletedUser = null;
		deletedUser = userService.removeUser(userId);
		if (deletedUser == null) {
			logger.debug("User cannot be deleted");
		}
		return deletedUser;
	}

	@GetMapping("/getsessions")
	public List<String> getSessions(HttpServletRequest request) {
		List<String> sessions = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
		System.out.println("Session ID: " + request.getSession().getId());
		System.out.println("getting sessions: " + sessions);
		return sessions;
	}

}
