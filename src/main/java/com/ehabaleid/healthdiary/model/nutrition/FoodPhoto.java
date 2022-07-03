package com.ehabaleid.healthdiary.model.nutrition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodPhoto {
	private String thumbnail;
	private String highResolutionImage;

	@JsonProperty("thumb")
	public String getThumbnail() {
		return thumbnail;
	}

	@JsonProperty("thumb")
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@JsonProperty("highres")
	public String getHighResolutionImage() {
		return highResolutionImage;
	}

	@JsonProperty("highres")
	public void setHighResolutionImage(String highResolutionImage) {
		this.highResolutionImage = highResolutionImage;
	}
}
