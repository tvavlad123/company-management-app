package com.mhp.requestmicro.entity.table;

import java.io.Serializable;
import java.util.List;

public class TableEntry implements Serializable {

    private static final long serialVersionUID = -1657071785487043524L;

    private long userId;
    private List<Day> requestsImplementation;

    private TableEntry(){}

    public TableEntry(long userId, List<Day> requestsImplementation) {
        this.userId = userId;
        this.requestsImplementation = requestsImplementation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Day> getRequestsImplementation() {
        return requestsImplementation;
    }

    public void setRequestsImplementation(List<Day> requestsImplementation) {
        this.requestsImplementation = requestsImplementation;
    }

    @Override
    public String toString() {
        return "TableEntry{" +
                "userId=" + userId +
                ", requestsImplementation=" + requestsImplementation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntry that = (TableEntry) o;

        if (userId != that.userId) return false;
        return requestsImplementation != null ? requestsImplementation.equals(that.requestsImplementation) : that.requestsImplementation == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (requestsImplementation != null ? requestsImplementation.hashCode() : 0);
        return result;
    }
}
