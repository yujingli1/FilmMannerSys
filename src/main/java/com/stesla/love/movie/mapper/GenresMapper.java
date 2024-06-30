package com.stesla.love.movie.mapper;


import com.stesla.love.movie.domain.Genres;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GenresMapper {
    Genres selectGenreByID(@Param("genreID") int genreID);
    List<Genres> getGenres();
    void addGenre(@Param("genre") Genres genre);
    void deleteGenre(@Param("genreID") int genreID);
    void updateGenre(@Param("genre") Genres genre);
}
