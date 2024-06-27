package com.stesla.love.movie.mapper;

import com.stesla.love.movie.domain.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieMapper {
    void addMovie(@Param("movie") Movie movie);
    void deleteMovie(@Param("movieID") int movieId);
    void updateMovie(@Param("movie") Movie movie);
    Movie getMovieByID(@Param("movieID") int movieId);
    List<Movie> getMovies(@Param("limit")int limit, @Param("offset")int offset);
    List<Movie> getMovieByType(@Param("genreID")int genreID,@Param("limit")int limit, @Param("offset")int offset);

    int getCount();

    int getCountByType(@Param("genreID")int genreID);
}
