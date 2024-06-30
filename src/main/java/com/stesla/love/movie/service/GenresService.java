package com.stesla.love.movie.service;

import com.stesla.love.movie.domain.Genres;

import java.util.List;

public interface GenresService {
    // 调用Mapper层增删改查
    void addGenre(Genres genre);
    void deleteGenre(int genreID);
    void updateGenre(Genres genre);
    Genres selectGenreByID(int genreID);
    List<Genres> getGenres();
}
