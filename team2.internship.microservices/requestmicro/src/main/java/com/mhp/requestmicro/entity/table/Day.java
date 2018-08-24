package com.mhp.requestmicro.entity.table;

import java.io.Serializable;

public class Day implements Serializable{

    private static final long serialVersionUID = -3808704464918593032L;

    private int dayInMonth;
    private int month;
    private int year;
    private DayType type;

    private Day(){}

    public Day(int month, int year, DayType type) {
        this.month = month;
        this.year = year;
        this.type = type;
    }

    public Day(int dayInMonth, int month, int year, DayType type) {
        this.dayInMonth = dayInMonth;
        this.month = month;
        this.year = year;
        this.type = type;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public void setDayInMonth(int dayInMonth) {
        this.dayInMonth = dayInMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DayType getType() {
        return type;
    }

    public void setType(DayType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayInMonth=" + dayInMonth +
                ", month=" + month +
                ", year=" + year +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (dayInMonth != day.dayInMonth) return false;
        if (month != day.month) return false;
        if (year != day.year) return false;
        return type == day.type;
    }

    @Override
    public int hashCode() {
        int result = dayInMonth;
        result = 31 * result + month;
        result = 31 * result + year;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
