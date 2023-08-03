package com.resendegabriel.serieslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return seriesRepository.findAll().stream().map(serie -> new SeriesDTO(serie)).toList();
	}

	@Transactional(readOnly = true)
	public SeriesDTO findById(Long id) {
		return new SeriesDTO(seriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	@Transactional
	public SeriesDTO insert(Series serie) {
		return new SeriesDTO(seriesRepository.save(serie));
	}

	@Transactional
	public SeriesDTO update(Long id, Series serie) {
		try {
			if (seriesRepository.existsById(id)) {
				Series entity = seriesRepository.getReferenceById(id);
				updateData(entity, serie);
				return new SeriesDTO(seriesRepository.save(entity));
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

	private void updateData(Series entity, Series serie) {
		entity.setId(serie.getId());
		entity.setTitle(serie.getTitle());
		entity.setPlatform(serie.getPlatform());
		entity.setScore(serie.getScore());
		entity.setYear(serie.getYear());
	}
}
