package com.niv.security.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_TABLE")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	public Integer id;
	
	@Column(name = "USER_NAME")
	public String name;
	
	@Column(name = "USER_USERNAME")
	public String username;
	
	@Column(name = "USER_PASSWORD")
	public String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "ROLES_TABLE",
			joinColumns = @JoinColumn(name = "USER_ID")
			)
	@Column(name = "role")
	private Set<String> roles; 
}
