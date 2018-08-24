package com.mhp.usermicro.epo;

import java.io.Serializable;

public class TeamEpo implements Serializable {

    private static final long serialVersionUID = -4447963950088297062L;

    private long              id;
    private String            name;
    private DepartmentEpo     department;

    private TeamEpo(){}

    public TeamEpo(long id, String name, DepartmentEpo department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DepartmentEpo getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "TeamEpo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEpo teamEpo = (TeamEpo) o;

        if (id != teamEpo.id) return false;
        if (name != null ? !name.equals(teamEpo.name) : teamEpo.name != null) return false;
        return department != null ? department.equals(teamEpo.department) : teamEpo.department == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }
}
