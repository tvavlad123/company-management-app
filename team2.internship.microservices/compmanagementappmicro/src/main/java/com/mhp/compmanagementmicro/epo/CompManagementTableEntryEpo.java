package com.mhp.compmanagementmicro.epo;

import com.mhp.requestmicro.epo.table.DayEpo;

import java.io.Serializable;
import java.util.List;

public class CompManagementTableEntryEpo implements Serializable {

    private static final long serialVersionUID = 6643579166128817971L;

    private long userId;
    private List<DayEpo> requestsImplementation;

    private CompManagementTableEntryEpo(){}

    public CompManagementTableEntryEpo(long userId, List<DayEpo> requestsImplementation) {
        this.userId = userId;
        this.requestsImplementation = requestsImplementation;
    }

    public long getUserId() {
        return userId;
    }

    public List<DayEpo> getRequestsImplementation() {
        return requestsImplementation;
    }

    @Override
    public String toString() {
        return "CompManagementTableEntryEpo{" +
                "userId=" + userId +
                ", requestsImplementation=" + requestsImplementation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompManagementTableEntryEpo that = (CompManagementTableEntryEpo) o;

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
