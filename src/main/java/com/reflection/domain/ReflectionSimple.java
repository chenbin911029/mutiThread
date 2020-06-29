package com.reflection.domain;

import java.util.Date;

public class ReflectionSimple {
    private Date day;

    private String feeling;

    public ReflectionSimple() {}

    public ReflectionSimple(Date day,String feeling) {
        this.day = day;
        this.feeling = feeling;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public Date getDate() {
        return this.day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    private String getDayFeeling() {
        return this.day + " " + this.feeling;
    }
}
