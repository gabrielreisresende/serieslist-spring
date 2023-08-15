package com.resendegabriel.serieslist.services;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resendegabriel.serieslist.controllers.SeriesController;
import com.resendegabriel.serieslist.dto.SeriesDTO;
import com.resendegabriel.serieslist.entities.Series;
import com.resendegabriel.serieslist.repositories.SeriesRepository;
import com.resendegabriel.serieslist.services.exceptions.DatabaseException;
import com.resendegabriel.serieslist.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SeriesService {

	@Autowired
	private SeriesRepository seriesRepository;

	@Transactional(readOnly = true)
	public List<SeriesDTO> findAll() {
		List<SeriesDTO> seriesDTOs = seriesRepository.findAll().stream().map(serie -> new SeriesDTO(serie)).toList();
		seriesDTOs.stream().forEach(s -> s.add(linkTo(methodOn(SeriesController.class).findById(s.getId())).withSelfRel()));
		return seriesDTOs;
	}

	@Transactional(readOnly = true)
	public SeriesDTO findById(Long id) {
		SeriesDTO dto = new SeriesDTO(seriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
		dto.add(linkTo(methodOn(SeriesController.class).findById(id)).withSelfRel());
		return dto;
	}

	@Transactional
	public SeriesDTO insert(SeriesDTO serie) {
		Series entity = new Series();
		updateData(entity, serie);
		SeriesDTO dto = new SeriesDTO(seriesRepository.save(entity));
		dto.add(linkTo(methodOn(SeriesController.class).findById(dto.getId())).withSelfRel());
		return dto;
	}

	@Transactional
	public SeriesDTO update(Long id, SeriesDTO serie) {
		try {
			if (seriesRepository.existsById(id)) {
				Series entity = seriesRepository.getReferenceById(id);
				updateData(entity, serie);
				SeriesDTO dto = new SeriesDTO(seriesRepository.save(entity));
				dto.add(linkTo(methodOn(SeriesController.class).findById(id)).withSelfRel());
				return dto;
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (EntityNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			if (seriesRepository.existsById(id)) {
				seriesRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private void updateData(Series entity, SeriesDTO serie) {
		entity.setTitle(serie.getTitle());
		entity.setPlatform(serie.getPlatform());
		entity.setScore(serie.getScore());
		entity.setYear(serie.getYear());
	}
}
