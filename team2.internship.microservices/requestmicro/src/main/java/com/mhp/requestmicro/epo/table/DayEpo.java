package com.mhp.requestmicro.epo.table;

import com.mhp.requestmicro.entity.table.DayType;

import java.io.Serializable;

public class DayEpo implements Serializable {

    private static final long serialVersionUID = -828758367308335990L;

    private int dayInMonth;
    private int month;
    private int year;
    private DayType type;

    private DayEpo (){}

    public DayEpo(int month, int year, DayType type) {
        this.month = month;
        this.year = year;
        this.type = type;
    }

    public DayEpo(int dayInMonth, int month, int year, DayType type) {
        this.dayInMonth = dayInMonth;
        this.month = month;
        this.year = year;
        this.type = type;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public DayType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "DayEpo{" +
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

        DayEpo dayEpo = (DayEpo) o;

        if (dayInMonth != dayEpo.dayInMonth) return false;
        if (month != dayEpo.month) return false;
        if (year != dayEpo.year) return false;
        return type == dayEpo.type;
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
