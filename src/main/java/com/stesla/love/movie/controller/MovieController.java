package com.stesla.love.movie.controller;

import com.alibaba.fastjson2.JSONObject;
import com.stesla.love.movie.domain.Movie;
import com.stesla.love.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stesla.love.movie.service.MovieService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping("/add")
    public Map<String,Object> addMovie(@RequestBody Map<String,Object> map) {
        // 获取map中所有的电影信息
        String Json = null;
        try {
            String title = (String) map.get("title");
            Integer directorId =(Integer) map.get("directorId");
            Integer genreId = (Integer) map.get("genreId");
            String releaseDate = (String) map.get("releaseDate");
            Integer duration = (Integer) map.get("duration");
            String synopsis = (String) map.get("synopsis");
            Movie movie = new Movie(title, directorId, genreId, releaseDate, duration, synopsis);
            Json = movie.toString();
            movieService.addMovie(movie);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            String jsonString = JSONObject.toJSONString(error);
            return ResultUtil.error(500, jsonString).getMap();
        }
        return ResultUtil.success(Json).getMap();
    }

    @PostMapping("/delete/{movieId}")
    public Map<String, Object> deleteMovie(@PathVariable("movieId") String movieId) {
        try {
            movieService.deleteMovie(Integer.parseInt(movieId));
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            String jsonString = JSONObject.toJSONString(error);
            return ResultUtil.error(500, jsonString).getMap();
        }
        return ResultUtil.success("删除成功").getMap();
    }

    @GetMapping("/info/{movieId}")
    public Map<String, Object> getMovie(@PathVariable("movieId") String movieId) {
        Movie movie = null;
        try {
            movie = movieService.getMovie(Integer.parseInt(movieId));
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            String jsonString = JSONObject.toJSONString(error);
            return ResultUtil.error(500, jsonString).getMap();
        }
        return ResultUtil.success(movie).getMap();
    }

    @PostMapping("/info")
    public Map<String, Object> getMovies(@RequestBody Map<String, Object> map) {
        int limit = (int) map.get("pageSize");
        int offset = (int) map.get("pageNum");
        return ResultUtil.success(movieService.getMovies(limit,offset*limit-offset)).getMap();
    }

    @PostMapping("/info/type")
    public Map<String, Object> getMovieByType(@RequestBody Map<String, Object> map) {
        int limit = (int) map.get("pageSize");
        int offset = (int) map.get("pageNum");
        int genreID = (int) map.get("genreID");

        return ResultUtil.success(movieService.getMovieByType(genreID,limit,offset*limit-offset)).getMap();
    }

    @GetMapping("/count")
    public Map<String, Object> getMovieCount() {
        return ResultUtil.success(movieService.getMovieCount()).getMap();
    }

    @GetMapping("/count/{genreID}")
    public Map<String, Object> getMovieCountByType(@PathVariable("genreID") String genreID) {
        return ResultUtil.success(movieService.getMovieCountByType(Integer.parseInt(genreID))).getMap();
    }

}
