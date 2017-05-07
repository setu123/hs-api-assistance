package com.setu.hsapiassistance.main;

import com.setu.hsapiassistance.service.ApiAssistanceService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date May 4, 2017
 * @author setu
 */
public class Main {

    public static void main(String[] args) {

        Date startTime = new Date();
        
        Map<String, String> params = getParams(args);

        if (params.get("key") == null) {
            String systemApiKey = System.getProperty("HUBSPOT_API_KEY");
            if (systemApiKey != null) {
                params.put("key", systemApiKey);
            }
        }

        if (params.get("key") == null) {
            System.out.println("Api key is missing. Please try again");
            System.exit(0);
        }

        ApiAssistanceService service = new ApiAssistanceService(params.get("key"));

        if(params.get("contacts") != null)
            service.generateTimelineReport(params.get("contacts"));
        
        printElapsedTime(startTime);
    }
    
    private static void printElapsedTime(Date startTime){
        Date endTime = new Date();
        long seconds = (endTime.getTime() - startTime.getTime())/1000;
        
        int hour = 0;
        if(seconds >= 3600){
            hour = (int) (seconds/3600);
            seconds = seconds%3600;
        }
        
        int minute = 0;
        if(seconds >= 60){
            minute = (int) (seconds/60);
            seconds = seconds%36;
        }
        
        StringBuilder buffer = new StringBuilder();
        buffer.append("Import succeeded in : ");
        if(hour > 0){
            buffer.append(hour).append(" hours ");
        }
        if(minute > 0){
            buffer.append(minute).append(" minutes ");
        }
        if(seconds > 0){
            buffer.append(seconds).append(" seconds");
        }
        
        System.out.println(buffer);
    }

    private static Map<String, String> getParams(String[] args) {
        Map<String, String> parameterMap = new HashMap<>();
        for (String arg : args) {
            String[] keyPair = arg.split("=");
            if (keyPair.length != 2) {
                System.out.println("Invalid parameter '" + arg + "'. Please try again");
                System.exit(0);
            }
            parameterMap.put(keyPair[0].trim(), keyPair[1].trim());
        }

        return parameterMap;
    }
}
