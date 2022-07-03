package com.ehabaleid.healthdiary.model.nutrition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrient {
	private int nutrientId;
	private float nutrientValue;

	@JsonProperty("attr_id")
	public int getNutrientId() {
		return nutrientId;
	}

	@JsonProperty("attr_id")
	public void setNutrientId(int nutrientId) {
		this.nutrientId = nutrientId;
	}

	@JsonProperty("value")
	public float getNutrientValue() {
		return nutrientValue;
	}

	@JsonProperty("value")
	public void setNutrientValue(float nutrientValue) {
		this.nutrientValue = nutrientValue;
	}
}
