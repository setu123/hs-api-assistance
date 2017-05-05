package com.setu.hsapiassistance.main;

import com.setu.hsapiassistance.model.history.History;
import com.setu.hsapiassistance.service.HistoryFinderService;
import java.util.Collections;
import java.util.List;

/**
 * @date May 4, 2017
 * @author setu
 */
public class Main {

    public static void main(String[] args) {
        String apiKey = "";
        
        if(args.length > 0)
            apiKey = args[0];
        else if(System.getProperty("HUBSPOT_API_KEY") != null)
            apiKey = System.getProperty("HUBSPOT_API_KEY");
        
        System.out.println("api_key: " + System.getProperty("HUBSPOT_API_KEY"));
        
        String email = "luca.sintini@saipem.com";
        HistoryFinderService service = new HistoryFinderService(apiKey);
        List<History> historyList = service.getAllHistory(email);
        
        for(History history: historyList){
            System.out.println("date; " + history.getDate() + ", action: " + history.getAction());
        }
        
        System.out.println("After sorting....");
        
        Collections.sort(historyList);
        
        for(History history: historyList){
            System.out.println("date; " + history.getDate() + ", action: " + history.getAction());
        }
    }
}
