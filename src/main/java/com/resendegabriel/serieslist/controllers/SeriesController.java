package com.resendegabriel.serieslist.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.resendegabriel.serieslist.dto.SeriesDTO;
import com.resendegabriel.serieslist.services.SeriesService;

@RestController
@RequestMapping(value = "/series")
public class SeriesController {

	@Autowired
	private SeriesService seriesService;

	@GetMapping
	public ResponseEntity<List<SeriesDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(seriesService.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SeriesDTO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(seriesService.findById(id));
	}

	@PostMapping
	public ResponseEntity<SeriesDTO> insert(@RequestBody SeriesDTO serie) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(serie.getId()).toUri();
		return ResponseEntity.created(uri).body(seriesService.insert(serie));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SeriesDTO> update(@PathVariable Long id, @RequestBody SeriesDTO serie) {
		return ResponseEntity.ok().body(seriesService.update(id, serie));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		seriesService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Serie deleted succesfully.");
	}
}
