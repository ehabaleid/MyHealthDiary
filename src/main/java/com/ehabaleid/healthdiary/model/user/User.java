package com.ehabaleid.healthdiary.model.user;


import javax.persistence.*;


@Entity
@Table(name = "AppUser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	private boolean active;
	private String role;
	private boolean enabled = false;

	public User(String name, String username, String password, String email, String role, Boolean active) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.active = active;
	}

	public User() {
	}

	public boolean isActive() {
		return active;
	}

	public String getRole() {
		return role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", active=" + active +
				", role='" + role + '\'' +
				", enabled=" + enabled +
				'}';
	}
}
