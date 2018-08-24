package com.mhp.compmanagementmicro.epo.list;

import com.mhp.compmanagementmicro.epo.CompManagementTableEntryEpo;

import java.io.Serializable;
import java.util.List;

public class CompManagementTableEntryEpoList implements Serializable{

    private static final long serialVersionUID = -422281033798939163L;

    private List<CompManagementTableEntryEpo> entryEpoList;

    private CompManagementTableEntryEpoList(){}

    public CompManagementTableEntryEpoList(List<CompManagementTableEntryEpo> entryEpoList) {
        this.entryEpoList = entryEpoList;
    }

    public List<CompManagementTableEntryEpo> getEntryEpoList() {
        return entryEpoList;
    }

    @Override
    public String toString() {
        return "CompManagementTableEntryEpoList{" +
                "entryEpoList=" + entryEpoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompManagementTableEntryEpoList that = (CompManagementTableEntryEpoList) o;

        return entryEpoList != null ? entryEpoList.equals(that.entryEpoList) : that.entryEpoList == null;
    }

    @Override
    public int hashCode() {
        return entryEpoList != null ? entryEpoList.hashCode() : 0;
    }
}
