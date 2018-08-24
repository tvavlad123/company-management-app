package com.mhp.requestmicro.mapper.table;

import com.mhp.requestmicro.entity.table.TableEntry;
import com.mhp.requestmicro.epo.table.TableEntryEpo;
import com.mhp.requestmicro.mapper.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableEntryEpoMapper extends GenericMapper<TableEntry,TableEntryEpo> {

    @Autowired
    private DayEpoMapper dayEpoMapper;

    @Override
    public TableEntry toInternal(TableEntryEpo epo) {
        return new TableEntry(epo.getUserId(),dayEpoMapper.toInternals(epo.getRequestsImplementation()));
    }

    @Override
    public TableEntryEpo toExternal(TableEntry model) {
        return new TableEntryEpo(model.getUserId(),dayEpoMapper.toExternals(model.getRequestsImplementation()));
    }
}
