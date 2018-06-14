package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, unique = true, length=90)
	private String username;
	
	@Column(nullable=false, length=250)
	private char[] password;
	
	@ManyToOne(optional=false)
	private UserRole role;
	
	@OneToMany(mappedBy="doctor", fetch=FetchType.EAGER, orphanRemoval=true)
	@JsonBackReference
	private Set<Diagnostic> diagnostics;
	
	public User() {}

	public User(Long id, String username, char[] password, UserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Set<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(Set<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + Arrays.toString(password) + ", role="
				+ role + "]";
	}
}
