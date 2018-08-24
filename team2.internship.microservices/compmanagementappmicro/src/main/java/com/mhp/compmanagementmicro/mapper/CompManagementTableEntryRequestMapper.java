package com.mhp.compmanagementmicro.mapper;

import com.mhp.compmanagementmicro.epo.CompManagementTableEntryRequestEpo;
import com.mhp.requestmicro.entity.table.TableEntryRequest;
import org.springframework.stereotype.Service;

@Service
public class CompManagementTableEntryRequestMapper extends GenericMapper<TableEntryRequest,CompManagementTableEntryRequestEpo> {
    @Override
    public TableEntryRequest toInternal(CompManagementTableEntryRequestEpo epo) {
        return new TableEntryRequest(epo.getId(),epo.getMonth(),epo.getYear());
    }

    @Override
    public CompManagementTableEntryRequestEpo toExternal(TableEntryRequest model) {
        return new CompManagementTableEntryRequestEpo(model.getId(),model.getMonth(),model.getYear());
    }
}
