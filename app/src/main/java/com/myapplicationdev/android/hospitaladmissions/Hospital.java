package com.myapplicationdev.android.hospitaladmissions;

import java.io.Serializable;

public class Hospital implements Serializable {

    private String level;
    private String value;
    private String year;

    public Hospital(String level, String value, String year){
        this.level = level;
        this.value = value;
        this.year = year;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "level='" + level + '\'' +
                ", value='" + value + '\'' +
                ", year=" + year +
                '}';
    }
}
