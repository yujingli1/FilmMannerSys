package com.stesla.love.movie.domain;

import com.alibaba.fastjson2.JSONObject;

public class Movie {
    /*
  * CREATE TABLE `movies`  (
  *`MovieID` int NOT NULL AUTO_INCREMENT,
  *`Title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  *`DirectorID` int NULL DEFAULT NULL,
  *`GenreID` int NULL DEFAULT NULL,
  *`ReleaseDate` date NULL DEFAULT NULL,
  *`Duration` int NULL DEFAULT NULL,
  *`Synopsis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  *`ImgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  *`ImgHash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  *PRIMARY KEY (`MovieID`) USING BTREE,
  *INDEX `DirectorID`(`DirectorID` ASC) USING BTREE,
  *INDEX `GenreID`(`GenreID` ASC) USING BTREE,
  *CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`DirectorID`) REFERENCES `directors` (`DirectorID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  *CONSTRAINT `movies_ibfk_2` FOREIGN KEY (`GenreID`) REFERENCES `genres` (`GenreID`) ON DELETE RESTRICT ON UPDATE RESTRICT
  *) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
  *
  *SET FOREIGN_KEY_CHECKS = 1;
* */
    private int movieID;
    private String title;
    private int directorID;
    private int genreID;
    private String releaseDate;
    private int duration;
    private String synopsis;
    private String imgUrl;
    private String imgHash;

    public Movie() {
    }


    public Movie(int movieID, String title, int directorID, int genreID, String releaseDate, int duration, String synopsis, String imgUrl, String imgHash) {
        this.movieID = movieID;
        this.title = title;
        this.directorID = directorID;
        this.genreID = genreID;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.synopsis = synopsis;
        this.imgUrl = imgUrl;
        this.imgHash = imgHash;
    }

    public Movie(int movieId, String title, int directorId, int genreId, String releaseDate, int duration, String synopsis) {
        this.movieID = movieId;
        this.title = title;
        this.directorID = directorId;
        this.genreID = genreId;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.synopsis = synopsis;
    }

    public Movie(String title, int directorId, int genreId, String releaseDate, int duration, String synopsis) {
        this.title = title;
        this.directorID = directorId;
        this.genreID = genreId;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.synopsis = synopsis;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgHash() {
        return imgHash;
    }

    public void setImgHash(String imgHash) {
        this.imgHash = imgHash;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
