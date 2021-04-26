package com.cg.ima.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author Aditya
 *
 */
@Entity
@Table(name = "usr")
public class User {
	@Id
	@NotNull
	private String userId;
	@NotNull
	@Size(min = 5,max = 20)
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
	}

	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "sUser1 [userId=" + userId + ", password=" + password + "]";
	}

}
