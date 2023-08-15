package com.resendegabriel.serieslist.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resendegabriel.serieslist.entities.Series;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesDTO extends RepresentationModel<SeriesDTO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
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
}
