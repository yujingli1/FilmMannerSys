package com.stesla.love.movie.service;
import com.stesla.love.movie.domain.Movie;

import java.util.List;

public interface MovieService {
    void addMovie(Movie movie);
    void deleteMovie(int movieId);
    void updateMovie();
    Movie getMovie(int movieId);
    List<Movie> getMovies(int limit, int offset);
    List<Movie> getMovieByType(int genreID,int limit, int offset);

    int getMovieCount();
    int getMovieCountByType(int genreID);
}
