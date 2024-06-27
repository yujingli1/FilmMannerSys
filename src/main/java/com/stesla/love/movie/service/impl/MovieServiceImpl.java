package com.stesla.love.movie.service.impl;

import com.stesla.love.movie.domain.Movie;
import com.stesla.love.movie.mapper.MovieMapper;
import com.stesla.love.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public void addMovie(Movie movie) {
        movieMapper.addMovie(movie);
    }

    @Override
    public void deleteMovie(int movieId) {
        movieMapper.deleteMovie(movieId);
    }

    @Override
    public void updateMovie() {

    }

    @Override
    public Movie getMovie(int movieId) {
        return movieMapper.getMovieByID(movieId);
    }

    @Override
    public List<Movie> getMovies(int limit, int offset) {
        return movieMapper.getMovies(limit, offset);
    }

    @Override
    public List<Movie> getMovieByType(int genreID, int limit, int offset) {
        return movieMapper.getMovieByType(genreID,limit, offset);
    }

    @Override
    public int getMovieCount() {
        return movieMapper.getCount();
    }

    @Override
    public int getMovieCountByType(int genreID) {
        return movieMapper.getCountByType(genreID);
    }
}
