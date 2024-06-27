package com.stesla.love.movie.domain;

import com.alibaba.fastjson2.JSONObject;

public class Director {
    private int directorID;
    private String directorName;
    private String birthDate;
    private String nationality;

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    @Override
    public String toString() { return JSONObject.toJSONString(this);}
}
