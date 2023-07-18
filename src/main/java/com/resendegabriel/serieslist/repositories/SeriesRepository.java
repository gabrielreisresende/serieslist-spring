package com.resendegabriel.serieslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resendegabriel.serieslist.entities.Series;

public interface SeriesRepository extends JpaRepository<Series, Long>{

}
