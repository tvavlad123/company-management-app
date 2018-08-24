package com.mhp.requestmicro.epo.table;

import java.io.Serializable;

public class TableEntryRequestEpo implements Serializable {

    private static final long serialVersionUID = -847511073473978000L;

    private long id;
    private int month;
    private int year;

    private TableEntryRequestEpo(){}

    public TableEntryRequestEpo(long id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "TableEntryRequestEpo{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntryRequestEpo that = (TableEntryRequestEpo) o;

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
