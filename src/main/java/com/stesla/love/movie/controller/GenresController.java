package com.stesla.love.movie.controller;


import com.stesla.love.movie.domain.Genres;
import com.stesla.love.movie.service.GenresService;
import com.stesla.love.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/genres")
public class GenresController {
    @Autowired
    private GenresService genresService;
    @PostMapping("/add")
    public Map<String,Object> addGenre(@RequestBody Map<String,Object> map) {
        String name = (String) map.get("genreName");
        Genres genre = new Genres(name);
        genresService.addGenre(genre);
        return ResultUtil.success("添加成功").getMap();
    }

    @PostMapping("/delete/{genreId}")
    public Map<String, Object> deleteGenre(@PathVariable String genreId) {
        genresService.deleteGenre(Integer.parseInt(genreId));
        return ResultUtil.success("删除成功").getMap();
    }


    @PostMapping("/update")
    public Map<String, Object> updateGenre(@RequestBody Map<String,Object> map) {
        String name = (String) map.get("genreName");
        int genreId = (int) map.get("genreID");
        Genres genre = new Genres(name,genreId);
        genresService.updateGenre(genre);
        return ResultUtil.success("修改成功").getMap();
    }

    @GetMapping("/info/{genreId}")
    public Map<String, Object> getGenre(@RequestParam("genreId") int genreId) {
        Genres genre = genresService.selectGenreByID(genreId);
        return ResultUtil.success(genre).getMap();
    }

    @GetMapping("/list")
    public Map<String, Object> getGenres() {
        return ResultUtil.success(genresService.getGenres()).getMap();
    }

}
