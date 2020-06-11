package com.schoolproject.InvestorPanel.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Users")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;

	@NotNull
	@Column(name = "email_address", unique = true, nullable = false)
	private String emailAddress;

	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<UserRole> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public User(String emailAddress, String password, Set<UserRole> roles) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", emailAddress=" + emailAddress + ", password=" + password + ", roles=" + roles
				+ "]";
	}

}
