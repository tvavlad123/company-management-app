package com.mhp.requestmicro.service;

import com.mhp.requestmicro.entity.table.Day;
import com.mhp.requestmicro.entity.table.DayType;
import com.mhp.requestmicro.entity.table.TableEntry;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface ITableService{
    List<Day> initWorkingDays(Month month,Year year);
    List<Day>requestRepresentation(Date startDate,Date endDate,List<Day> workingDays,DayType type);
    List<Day>requestsRepresentation(HomeOfficeRequestEpoList homeOfficeRequestEpoList,
                                   VacationRequestEpoList vacationRequestEpoList,List<Day> workingDays);
    TableEntry createTableEntryForUser(long userId,Month month,Year year);
    List<TableEntry> createTableEntriesForTeam(List<Long> userIds,Month month,Year year);

}
