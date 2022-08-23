package com.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="User")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "ID")
	private Long userDatabaseID;

	
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	
	
	@Column(name = "PASSWORD_HASH", nullable = false)
	private String passwordHash;


	public Long getUserDatabaseID() {
		return userDatabaseID;
	}


	public void setUserDatabaseID(Long userDatabaseID) {
		this.userDatabaseID = userDatabaseID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPasswordHash() {
		return passwordHash;
	}


	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
