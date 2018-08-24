package com.mhp.usermicro.mapper;

import com.mhp.usermicro.entity.Team;
import com.mhp.usermicro.epo.TeamEpo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamEpoMapper extends GenericMapper<Team,TeamEpo> {

    @Autowired
    private DepartmentEpoMapper dem;

    @Override
    public Team toInternal(TeamEpo epo) {
        return new Team(epo.getId(),epo.getName(),
                dem.toInternal(epo.getDepartment()));
    }

    @Override
    public TeamEpo toExternal(Team model) {
        return new TeamEpo(model.getId(),model.getName(),
                dem.toExternal(model.getDepartment()));
    }
}
