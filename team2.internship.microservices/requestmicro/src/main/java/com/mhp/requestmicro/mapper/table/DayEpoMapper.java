package com.mhp.requestmicro.mapper.table;

import com.mhp.requestmicro.entity.table.Day;
import com.mhp.requestmicro.epo.table.DayEpo;
import com.mhp.requestmicro.mapper.GenericMapper;
import org.springframework.stereotype.Service;

@Service
public class DayEpoMapper extends GenericMapper<Day,DayEpo>{
    @Override
    public Day toInternal(DayEpo epo) {
        return new Day(epo.getDayInMonth(),epo.getMonth(),epo.getYear(),epo.getType());
    }

    @Override
    public DayEpo toExternal(Day model) {
        return new DayEpo(model.getDayInMonth(),model.getMonth(),model.getYear(),model.getType());
    }
}
