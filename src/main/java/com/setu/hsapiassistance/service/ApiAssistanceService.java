/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.setu.hsapiassistance.service;

import com.setu.hsapiassistance.model.APIUsageLimitDTO;
import com.setu.hsapiassistance.model.history.History;
import com.setu.hsapiassistance.service.api.ApiAssistant;
import com.setu.hsapiassistance.service.api.ApiAssistantImpl;
import com.setu.hsapiassistance.service.api.http.APILimitExceededException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @date May 7, 2017
 * @author setu
 */
public class ApiAssistanceService {

    private final String apiKey;

    public ApiAssistanceService(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean generateTimelineReport(String email) throws APILimitExceededException {
        boolean success = false;

        HistoryFinderService historyFinderService = new HistoryFinderService(apiKey);
        List<History> histories = historyFinderService.getAllHistory(email);
        ReportService reportService = new ReportService();
        Workbook wb = reportService.createReport(histories);

        try {
            createFile(wb);
            success = true;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return success;
    }

    public boolean generateTimelineReportByListId(String listId, int max) throws APILimitExceededException {
        HistoryFinderService historyFinderService = new HistoryFinderService(apiKey);
        List<History> histories = historyFinderService.getAllHistoryByListId(listId, max);
        return generateReport(histories);
    }

    public boolean generateTimelineReportByCSVEmailList(String csvFileName, int max) throws APILimitExceededException {
        HistoryFinderService historyFinderService = new HistoryFinderService(apiKey);
        List<History> histories = historyFinderService.getAllHistoryByCSVEmailList(csvFileName, max);
        return generateReport(histories);
    }
    
    public APIUsageLimitDTO getAPIUsageLimit() throws APILimitExceededException{
        ApiAssistant apiAssistant = new ApiAssistantImpl(apiKey);
        return apiAssistant.getAPIUsageLimit();
    }

    private boolean generateReport(List<History> histories) {
        boolean success = false;
        ReportService reportService = new ReportService();
        Workbook wb = reportService.createReport(histories);

        try {
            createFile(wb);
            success = true;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return success;
    }

    private void createFile(Workbook wb) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream("Timeline.xls")) {
            wb.write(fileOut);
        }
    }

}
