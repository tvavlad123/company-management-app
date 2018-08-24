package com.mhp.requestmicro.service;

import com.mhp.requestmicro.entity.export.UserInfo;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExportServiceImpl implements IExportService{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExportServiceImpl.class);
    @Autowired
    private IHomeOfficeRequestService homeOfficeRequestService;
    @Autowired
    private IVacationRequestService vacationRequestService;

    public void generateExcel(long id,String excelFilePath){
//        List<UserInfo> vacationRequests,List<UserInfo> homeRequests,

        HomeOfficeRequestEpoList homeOfficeRequests = homeOfficeRequestService.getAllHomeOfficeRequestsByTeamAndYear(id,2017);
        VacationRequestEpoList  vacationRequests = vacationRequestService.getAllVacationRequestsByTeamAndYear(id,2017);
        List<UserInfo> vr = getVR();
        List<UserInfo> hr = getHR();


        try {
            writeExcel(vr,hr, excelFilePath);
        } catch (IOException e) {
        LOGGER.error(e.getMessage());
        }
    }


    private void writeExcel(List<UserInfo> vacationRequests, List<UserInfo> homeRequests, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;
        createRequestHeader(sheet,"VacReq",rowCount);

        for (UserInfo userInfo : vacationRequests) {
            Row row = sheet.createRow(++rowCount);
            writeUserInfo(userInfo, row);
        }
        rowCount=rowCount+2;
        createRequestHeader(sheet,"HomeReq",rowCount);

        for (UserInfo userInfo : homeRequests) {
            Row row = sheet.createRow(++rowCount);
            writeUserInfo(userInfo, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }



    private List<UserInfo> getVR() {
        UserInfo u1 = new UserInfo("Iosif",0,0,15,0,0,0,10,0,0,0,0,0,10);
        UserInfo u2 = new UserInfo("Stefan",0,1,0,1,0,1,0,1,0,1,1,0,20);
        UserInfo u3 = new UserInfo("Cristina",0,0,20,0,0,0,0,0,0,0,0,0,5);
        UserInfo u4 = new UserInfo("Matei",10,0,0,0,0,0,10,0,0,0,5,0,0);
        List<UserInfo> infoList = Arrays.asList(u1, u2, u3, u4);
        return infoList;
    }

    private List<UserInfo> getHR() {
        UserInfo u1 = new UserInfo("Iosif",2,0,2,0,1,1.5,1,0,4,0,0.5,0,12);
        UserInfo u2 = new UserInfo("Stefan",5,0,2.5,0,0,2.5,0,0,0,0,0,0,14);
        UserInfo u3 = new UserInfo("Cristina",10,0,5,0,0,0,0,5,0,0,0,0,4);
        UserInfo u4 = new UserInfo("Matei",0,0,0,0,0,0,0,0,0,0,0,0,24);
        List<UserInfo> infoList = Arrays.asList(u1, u2, u3, u4);
        return infoList;
    }

    private Workbook getWorkbook(String excelFilePath)
            throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }
    private void writeUserInfo(UserInfo userInfo, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(userInfo.getFullName());
        cell= row.createCell(1);
        cell.setCellValue(userInfo.getJan());
        cell= row.createCell(2);
        cell.setCellValue(userInfo.getFeb());
        cell= row.createCell(3);
        cell.setCellValue(userInfo.getMar());
        cell= row.createCell(4);
        cell.setCellValue(userInfo.getApr());
        cell= row.createCell(5);
        cell.setCellValue(userInfo.getMay());
        cell= row.createCell(6);
        cell.setCellValue(userInfo.getJun());
        cell= row.createCell(7);
        cell.setCellValue(userInfo.getJul());
        cell= row.createCell(8);
        cell.setCellValue(userInfo.getAug());
        cell= row.createCell(9);
        cell.setCellValue(userInfo.getSept());
        cell= row.createCell(10);
        cell.setCellValue(userInfo.getOct());
        cell= row.createCell(11);
        cell.setCellValue(userInfo.getNov());
        cell= row.createCell(12);
        cell.setCellValue(userInfo.getDec());
        cell= row.createCell(14);
        cell.setCellValue(userInfo.getRemainingDays());
    }
    private void createRequestHeader(Sheet sheet,String name, int rowCount) {
        Row row = sheet.createRow(rowCount);
        Cell cell = row.createCell(0);
        cell.setCellValue(name);
        cell = row.createCell(1);
        cell.setCellValue("Jan");
        cell = row.createCell(2);
        cell.setCellValue("Feb");
        cell = row.createCell(3);
        cell.setCellValue("Mar");
        cell = row.createCell(4);
        cell.setCellValue("Apr");
        cell = row.createCell(5);
        cell.setCellValue("May");
        cell = row.createCell(6);
        cell.setCellValue("Jun");
        cell = row.createCell(7);
        cell.setCellValue("Jul");
        cell = row.createCell(8);
        cell.setCellValue("Aug");
        cell = row.createCell(9);
        cell.setCellValue("Sept");
        cell = row.createCell(10);
        cell.setCellValue("Oct");
        cell = row.createCell(11);
        cell.setCellValue("Nov");
        cell = row.createCell(12);
        cell.setCellValue("Dec");
        cell = row.createCell(14);
        cell.setCellValue("RemDays");
    }
}
