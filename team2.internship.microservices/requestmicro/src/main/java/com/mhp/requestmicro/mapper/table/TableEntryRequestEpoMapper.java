package com.mhp.requestmicro.mapper.table;

import com.mhp.requestmicro.entity.table.TableEntryRequest;
import com.mhp.requestmicro.epo.table.TableEntryRequestEpo;
import com.mhp.requestmicro.mapper.GenericMapper;
import org.springframework.stereotype.Service;

@Service
public class TableEntryRequestEpoMapper extends GenericMapper<TableEntryRequest,TableEntryRequestEpo>{
    @Override
    public TableEntryRequest toInternal(TableEntryRequestEpo epo) {
        return new TableEntryRequest(epo.getId(),epo.getMonth(),epo.getYear());
    }

    @Override
    public TableEntryRequestEpo toExternal(TableEntryRequest model) {
        return new TableEntryRequestEpo(model.getId(),model.getMonth(),model.getYear());
    }
}
