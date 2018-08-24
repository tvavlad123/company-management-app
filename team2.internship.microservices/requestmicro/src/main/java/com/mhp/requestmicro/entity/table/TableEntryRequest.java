package com.mhp.requestmicro.entity.table;

import java.io.Serializable;

public class TableEntryRequest implements Serializable{

    private static final long serialVersionUID = -2276444776740734929L;

    private long id;
    private int month;
    private int year;

    private TableEntryRequest(){}

    public TableEntryRequest(long id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TableEntryRequest{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntryRequest that = (TableEntryRequest) o;

        if (id != that.id) return false;
        if (month != that.month) return false;
        return year == that.year;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }
}
