package com.setu.hsapiassistance.main;

import com.setu.hsapiassistance.model.ContactDTO;
import com.setu.hsapiassistance.model.history.History;
import com.setu.hsapiassistance.service.HistoryFinderService;
import com.setu.hsapiassistance.service.api.ApiAssistant;
import com.setu.hsapiassistance.service.api.ApiAssistantImpl;
import java.util.List;

/**
 * @date May 4, 2017
 * @author setu
 */
public class Main {

    public static void main(String[] args) {
        String apiKey = "";
        ApiAssistant apiAssistant = new ApiAssistantImpl(apiKey);
        String email = "luca.sintini@saipem.com";
        ContactDTO contactDTO = apiAssistant.getContactByEmail(email);
        HistoryFinderService service = new HistoryFinderService();
        List<History> historyList = service.getAllHistory(contactDTO);
//        System.out.println("contact= " + contactDTO);
        
        for(History history: historyList){
            System.out.println("date; " + history.getDate() + ", action: " + history.getAction());
        }
    }
}
