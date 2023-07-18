package com.resendegabriel.serieslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resendegabriel.serieslist.dto.SeriesDTO;
import com.resendegabriel.serieslist.entities.Series;
import com.resendegabriel.serieslist.repositories.SeriesRepository;

@Service
public class SeriesService {

	@Autowired
	private SeriesRepository seriesRepository;

	@Transactional(readOnly = true)
	public List<SeriesDTO> findAll() {
		List<Series> list = seriesRepository.findAll();
		return list.stream().map(x -> new SeriesDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public SeriesDTO findById(Long id) {
		Series result = seriesRepository.findById(id).get();
		return new SeriesDTO(result);
	}

	@Transactional
	public Series insert(Series obj) {
		return seriesRepository.save(obj);
	}
	
	@Transactional
    public SeriesDTO update(Long id, SeriesDTO updatedSeriesDTO) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Série não encontrada"));
        ConvertToEntitySeries(series, updatedSeriesDTO);
        seriesRepository.save(series);
        return new SeriesDTO(series);
    }

	private void ConvertToEntitySeries(Series series, SeriesDTO dto) {
		series.setTitle(dto.getTitle());
		series.setYear(dto.getYear());
		series.setPlatform(dto.getPlatform());
		series.setScore(dto.getScore());
	}
}
