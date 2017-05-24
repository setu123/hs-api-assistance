/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.setu.hsapiassistance.service;

import com.setu.hsapiassistance.model.history.History;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @date May 7, 2017
 * @author setu
 */
public class ReportService {

    Workbook createReport(List<History> histories) {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell contactHeader = row.createCell(0);
        contactHeader.setCellValue("Contact");
        Cell dateHeader = row.createCell(1);
        dateHeader.setCellValue("Date");
        Cell actionHeader = row.createCell(2);
        actionHeader.setCellValue("Action");
        
        for(int i=0; i<histories.size(); i++){
            row = sheet.createRow(i+1);
            Cell contact = row.createCell(0);
            contact.setCellValue(histories.get(i).getEmail());
            Cell date = row.createCell(1);
            date.setCellValue(getFormattedDate(histories.get(i).getDate()));
            Cell action = row.createCell(2);
            action.setCellValue(histories.get(i).getAction());
        }
        
        return wb;
    }
    
    private String getFormattedDate(Date date){
        String datePattern = "MM/dd/yyyy hh:mm a";
        
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        return dateFormat.format(date);
    }
}
