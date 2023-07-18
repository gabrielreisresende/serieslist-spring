package com.resendegabriel.serieslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resendegabriel.serieslist.dto.SeriesDTO;
import com.resendegabriel.serieslist.services.SeriesService;

@RestController
@RequestMapping(value = "/series")
public class SeriesController {

	@Autowired
	private SeriesService seriesService;
	
	@GetMapping
	public List<SeriesDTO> findAll(){
		return seriesService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public SeriesDTO findById(@PathVariable Long id){
		return seriesService.findById(id);
	}
}
