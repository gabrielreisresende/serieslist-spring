package com.resendegabriel.serieslist.dto;

import org.springframework.beans.BeanUtils;

import com.resendegabriel.serieslist.entities.Series;

import lombok.Data;

@Data
public class SeriesDTO {

	private String title;
	private String year;
	private String platform;
	private Double score;
	
	public SeriesDTO() {
	}
	
	public SeriesDTO(Series entity) {
		BeanUtils.copyProperties(entity, this, "id");
	}
}
