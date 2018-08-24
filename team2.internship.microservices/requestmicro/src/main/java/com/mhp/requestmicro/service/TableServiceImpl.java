package com.mhp.requestmicro.service;

import com.mhp.requestmicro.entity.table.Day;
import com.mhp.requestmicro.entity.table.DayType;
import com.mhp.requestmicro.entity.table.TableEntry;
import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;
import com.mhp.requestmicro.epo.VacationRequestEpo;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class TableServiceImpl implements ITableService {

    private static final int CALENDAR_SIZE = 25;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TableServiceImpl.class);
    @Autowired
    private IHomeOfficeRequestService homeOfficeRequestService;
    @Autowired
    private IVacationRequestService vacationRequestService;

    public List<TableEntry> createTableEntriesForTeam(List<Long> userIds,Month month,Year year){
        List<TableEntry> tableEntries = new ArrayList<>();
        for (Long l : userIds){
            tableEntries.add(createTableEntryForUser(l,month,year));
        }
        return tableEntries;
    }

    public TableEntry createTableEntryForUser(long userId,Month month,Year year){
        List<Day> workingDays= initWorkingDays(month,year);
        workingDays=requestsRepresentation(homeOfficeRequestService.getAllHomeOfficeRequestsOfSpecificUserMonthYear(userId,month,year),
                vacationRequestService.getAllVacationRequestsOfSpecificUserMonthYear(userId,month,year),workingDays);
        return new TableEntry(userId,workingDays);
    }

    @Override
    public List<Day> requestRepresentation(Date startDate, Date endDate, List<Day> workingDays, DayType type) {
        //using joda-time because date methods are deprecated and hardly to use
        DateTime sd = new DateTime(startDate);
        DateTime ed = new DateTime(endDate);

        for(Day day : workingDays){
            if (day.getType()==DayType.UNINITIALIZED){
                //if the request starts in the anterior month and ends in this month
                if (day.getMonth()>sd.getMonthOfYear()&&
                        day.getMonth()==ed.getMonthOfYear()&&day.getYear()==ed.getYear()&&day.getDayInMonth()<=ed.getDayOfMonth()){
                    day.setType(type);
                }
                //if the request have the start and the end in this month
                if (day.getMonth()==sd.getMonthOfYear()&&day.getYear()==sd.getYear()&&day.getDayInMonth()>=sd.getDayOfMonth()&&
                        day.getMonth()==ed.getMonthOfYear()&&day.getYear()==ed.getYear()&&day.getDayInMonth()<=ed.getDayOfMonth()){
                    day.setType(type);
                }
                //if the requests start in this month and ends in the next month
                if (day.getMonth()==sd.getMonthOfYear()&&day.getYear()==sd.getYear()&&day.getDayInMonth()>=sd.getDayOfMonth()&&
                        day.getMonth()<ed.getMonthOfYear()){
                    day.setType(type);
                }
            }
        }
        return workingDays;
    }

    public List<Day> requestsRepresentation(HomeOfficeRequestEpoList homeOfficeRequestEpoList,
                                           VacationRequestEpoList vacationRequestEpoList, List<Day> workingDays) {

        for (HomeOfficeRequestEpo h : homeOfficeRequestEpoList.getHomereqepolist()){
            if (h.getStatus_id().getName().equals("UNRESOLVED")&&h.getHalf_day().getType().equals("First half"))
                workingDays=requestRepresentation(h.getStart_date(),h.getEnd_date(),workingDays,DayType.HOME_OFFICE_PENDING_FIRST_HALF);
            else if (h.getStatus_id().getName().equals("UNRESOLVED")&&h.getHalf_day().getType().equals("Second half"))
                workingDays=requestRepresentation(h.getStart_date(),h.getEnd_date(),workingDays,DayType.HOME_OFFICE_PENDING_SECOND_HALF);
            else if (h.getStatus_id().getName().equals("UNRESOLVED")&&h.getHalf_day().getType().equals("Whole day"))
                workingDays=requestRepresentation(h.getStart_date(),h.getEnd_date(),workingDays,DayType.HOME_OFFICE_PENDING_WHOLE_DAY);
            else if (h.getStatus_id().getName().equals("ACCEPTED")&&h.getHalf_day().getType().equals("First half"))
                workingDays=requestRepresentation(h.getStart_date(),h.getEnd_date(),workingDays,DayType.HOME_OFFICE_FIRST_HALF);
            else if (h.getStatus_id().getName().equals("ACCEPTED")&&h.getHalf_day().getType().equals("Second half"))
                workingDays=requestRepresentation(h.getStart_date(),h.getEnd_date(),workingDays,DayType.HOME_OFFICE_SECOND_HALF);
            else if (h.getStatus_id().getName().equals("ACCEPTED")&&h.getHalf_day().getType().equals("Whole day"))
                workingDays=requestRepresentation(h.getStart_date(),h.getEnd_date(),workingDays,DayType.HOME_OFFICE_WHOLE_DAY);
        }

        for (VacationRequestEpo v : vacationRequestEpoList.getVacationreqepolist()){
        if(v.getStatus_id().getName().equals("UNRESOLVED"))
            workingDays=requestRepresentation(v.getStart_date(),v.getEnd_date(),workingDays,DayType.VACATION_PENDING);
            else if (v.getStatus_id().getName().equals("ACCEPTED"))
                workingDays=requestRepresentation(v.getStart_date(),v.getEnd_date(),workingDays,DayType.VACATION);
        }
        return workingDays;
    }

    public List<Day> initWorkingDays(Month month, Year year) {
        int dayInMonth=1;
        LocalDate firstDayOfMonth = Year.of(year.getValue()).atMonth(month.getValue()).atDay(dayInMonth);
        LocalDate lastDayOfMonth = Year.of(year.getValue()).atMonth(month.getValue()).atEndOfMonth();
        List<Day> workingDays = new ArrayList<>();
        int outOfBounds = 0;

        if (firstDayOfMonth.getDayOfWeek()!= DayOfWeek.MONDAY&& firstDayOfMonth.getDayOfWeek()!= DayOfWeek.SUNDAY
                && firstDayOfMonth.getDayOfWeek()!= DayOfWeek.SATURDAY){
        outOfBounds=firstDayOfMonth.getDayOfWeek().getValue();
        }

        while (outOfBounds>1){
            workingDays.add(new Day(month.getValue(),year.getValue(),DayType.OUT_OF_BOUNDS));
            outOfBounds--;
        }
        do{
            if (firstDayOfMonth.getDayOfWeek()!= DayOfWeek.SUNDAY
                    && firstDayOfMonth.getDayOfWeek()!= DayOfWeek.SATURDAY){
                workingDays.add(new Day(firstDayOfMonth.getDayOfMonth(),month.getValue(),year.getValue(),DayType.UNINITIALIZED));
            }
            dayInMonth++;
            if (dayInMonth<=lastDayOfMonth.getDayOfMonth())
            firstDayOfMonth = firstDayOfMonth = Year.of(year.getValue()).atMonth(month.getValue()).atDay(dayInMonth);
        }while (dayInMonth<=lastDayOfMonth.getDayOfMonth());

        while (workingDays.size()<CALENDAR_SIZE){
            workingDays.add(new Day(month.getValue(),year.getValue(),DayType.OUT_OF_BOUNDS));
        }
        return workingDays;
    }
}
