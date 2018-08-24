package com.mhp.requestmicro.epo.table;

import java.io.Serializable;
import java.util.List;

public class TableEntryEpoList implements Serializable {

    private static final long serialVersionUID = 452720702529377606L;

    List<TableEntryEpo> tableEntryEpoList;

    private TableEntryEpoList(){}

    public TableEntryEpoList(List<TableEntryEpo> tableEntryEpoList) {
        this.tableEntryEpoList = tableEntryEpoList;
    }

    public List<TableEntryEpo> getTableEntryEpoList() {
        return tableEntryEpoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntryEpoList that = (TableEntryEpoList) o;

        return tableEntryEpoList != null ? tableEntryEpoList.equals(that.tableEntryEpoList) : that.tableEntryEpoList == null;
    }

    @Override
    public int hashCode() {
        return tableEntryEpoList != null ? tableEntryEpoList.hashCode() : 0;
    }
}
