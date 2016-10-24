package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nasruddin on 16/10/16.
 */
@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public List<MovieDTO> findAll() {

        return movieRepository.findAll().stream()
                .map(entity -> new MovieDTO(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieDTO create(MovieDTO movie) {

        Movie aMovie = new Movie();
        aMovie.setName(movie.getName());
        Movie savedMovie = movieRepository.saveAndFlush(aMovie);
        return new MovieDTO(savedMovie.getId(), savedMovie.getName());
    }

    @Transactional
    public MovieDTO update(Long id, MovieDTO movieDTO) {

        Movie movie = findOneSafe(id);
        movie.setName(movieDTO.getName());
        return new MovieDTO(movie.getId(), movie.getName());
    }

    @Transactional
    public void delete(Long id) {

        Movie movie = findOneSafe(id);
        movieRepository.delete(movie);
        
    }

    private Movie findOneSafe(Long id) {

        Movie movie = movieRepository.findOne(id);

        if (movie == null) {
            throw new MovieNotFoundException(id);
        } else {
            return movie;
        }
    }
}
