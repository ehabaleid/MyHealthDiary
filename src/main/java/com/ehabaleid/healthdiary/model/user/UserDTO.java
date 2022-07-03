package com.ehabaleid.healthdiary.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

	private User user;
	private float totalCaloriesLogged;

	public UserDTO(User user, float totalCaloriesLogged) {
		this.user = user;
		this.totalCaloriesLogged = totalCaloriesLogged;
	}

	@JsonProperty(index = 1)
	public int getUserId() {
		return user.getId();
	}

	public String getUserEmail() {
		return user.getEmail();
	}

	public float getTotalCaloriesLogged() {
		return totalCaloriesLogged;
	}

	public void setTotalCaloriesLogged(float totalCaloriesLogged) {
		this.totalCaloriesLogged = totalCaloriesLogged;
	}
}
