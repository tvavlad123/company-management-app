package com.mhp.usermicro.mapper;

import com.mhp.usermicro.entity.Position;
import com.mhp.usermicro.epo.PositionEpo;

import org.springframework.stereotype.Service;

@Service
public class PositionEpoMapper extends GenericMapper<Position,PositionEpo> {
    @Override
    public Position toInternal(PositionEpo epo) {
        return new Position(epo.getId(),epo.getName());
    }

    @Override
    public PositionEpo toExternal(Position model) {
        return new PositionEpo(model.getId(),model.getName());
    }
}
