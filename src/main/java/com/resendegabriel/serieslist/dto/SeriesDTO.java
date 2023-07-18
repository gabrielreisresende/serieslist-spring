package com.resendegabriel.serieslist.dto;

import org.springframework.beans.BeanUtils;

import com.resendegabriel.serieslist.entities.Series;

public class SeriesDTO {

	private Long id;
	private String title;
	private String year;
	private String platform;
	private Double score;

	public SeriesDTO() {
	}

	public SeriesDTO(Series entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
