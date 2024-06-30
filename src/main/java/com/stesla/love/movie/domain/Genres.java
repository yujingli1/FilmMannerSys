package com.stesla.love.movie.domain;

import com.alibaba.fastjson2.JSONObject;

/*
CREATE TABLE `genres`  (
        `GenreID` int NOT NULL AUTO_INCREMENT,
  `GenreName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
PRIMARY KEY (`GenreID`) USING BTREE
)
*/
public class Genres {
    private int genreID;
    private String genreName;

    public Genres() {
    }
    public Genres(String name, int genreId) {
        this.genreName = name;
        this.genreID = genreId;
    }
    public Genres(String name) {
        this.genreName = name;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() { return JSONObject.toJSONString(this);}
}
