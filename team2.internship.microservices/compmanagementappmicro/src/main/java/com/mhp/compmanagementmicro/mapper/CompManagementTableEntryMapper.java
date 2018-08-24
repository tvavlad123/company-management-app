package com.mhp.compmanagementmicro.mapper;

import com.mhp.compmanagementmicro.epo.CompManagementTableEntryEpo;
import com.mhp.requestmicro.epo.table.TableEntryEpo;
import org.springframework.stereotype.Service;

@Service
public class CompManagementTableEntryMapper extends GenericMapper<TableEntryEpo,CompManagementTableEntryEpo> {
    @Override
    public TableEntryEpo toInternal(CompManagementTableEntryEpo epo) {
        return new TableEntryEpo(epo.getUserId(),epo.getRequestsImplementation());
    }

    @Override
    public CompManagementTableEntryEpo toExternal(TableEntryEpo model) {
        return new CompManagementTableEntryEpo(model.getUserId(),model.getRequestsImplementation());
    }
}
