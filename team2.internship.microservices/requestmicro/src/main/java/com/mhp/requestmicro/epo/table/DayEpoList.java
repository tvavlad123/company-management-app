package com.mhp.requestmicro.epo.table;

import java.io.Serializable;
import java.util.List;

public class DayEpoList implements Serializable {

    private static final long serialVersionUID = -7104209706691337165L;

    private List<DayEpo> dayEpoList;

    private DayEpoList(){}

    public DayEpoList(List<DayEpo> dayEpoList) {
        this.dayEpoList = dayEpoList;
    }

    public List<DayEpo> getDayEpoList() {
        return dayEpoList;
    }

    @Override
    public String toString() {
        return "DayEpoList{" +
                "dayEpoList=" + dayEpoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayEpoList that = (DayEpoList) o;

        return dayEpoList != null ? dayEpoList.equals(that.dayEpoList) : that.dayEpoList == null;
    }

    @Override
    public int hashCode() {
        return dayEpoList != null ? dayEpoList.hashCode() : 0;
    }
}
