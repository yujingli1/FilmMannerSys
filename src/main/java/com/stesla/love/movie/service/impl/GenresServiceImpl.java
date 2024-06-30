package com.stesla.love.movie.service.impl;

import com.stesla.love.movie.domain.Genres;
import com.stesla.love.movie.mapper.GenresMapper;
import com.stesla.love.movie.service.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresServiceImpl implements GenresService {

    @Autowired
    private GenresMapper genresMapper;

    @Override
    public void addGenre(Genres genre) {
        genresMapper.addGenre(genre);
    }

    @Override
    public void deleteGenre(int genreID) {
        genresMapper.deleteGenre(genreID);
    }

    @Override
    public void updateGenre(Genres genre) {
        genresMapper.updateGenre(genre);
    }

    @Override
    public Genres selectGenreByID(int genreID) {
        return genresMapper.selectGenreByID(genreID);
    }

    @Override
    public List<Genres> getGenres() {
        return genresMapper.getGenres();
    }
}
