package com.resendegabriel.serieslist.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.resendegabriel.serieslist.dto.SeriesDTO;
import com.resendegabriel.serieslist.entities.Series;
import com.resendegabriel.serieslist.services.SeriesService;

@RestController
@RequestMapping(value = "/series")
public class SeriesController {

	@Autowired
	private SeriesService seriesService;

	@GetMapping
	public List<SeriesDTO> findAll() {
		return seriesService.findAll();
	}

	@GetMapping(value = "/{id}")
	public SeriesDTO findById(@PathVariable Long id) {
		return seriesService.findById(id);
	}

	@PostMapping
	public ResponseEntity<SeriesDTO> insert(@RequestBody Series obj) {
		obj = seriesService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new SeriesDTO(obj));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SeriesDTO> update(@PathVariable Long id, @RequestBody SeriesDTO obj){
		obj = seriesService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
