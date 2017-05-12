/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.setu.hsapiassistance.service;

import com.setu.hsapiassistance.model.CampaignDTO;
import com.setu.hsapiassistance.model.ContactDTO;
import com.setu.hsapiassistance.model.EmailEventDTO;
import com.setu.hsapiassistance.model.EmailEventListDTO;
import com.setu.hsapiassistance.model.history.EmailEventHistory;
import com.setu.hsapiassistance.model.history.EmailEventHistory.EventType;
import com.setu.hsapiassistance.model.history.FormSubmissionHistory;
import com.setu.hsapiassistance.model.history.History;
import com.setu.hsapiassistance.model.history.PageViewHistory;
import com.setu.hsapiassistance.service.api.ApiAssistant;
import com.setu.hsapiassistance.service.api.ApiAssistantImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @date May 4, 2017
 * @author setu
 */
public class HistoryFinderService {
    
    private final ApiAssistant apiAssistant;

    public HistoryFinderService(String apiKey) {
        apiAssistant = new ApiAssistantImpl(apiKey);
    }
    
    public List<History> getAllHistory(String email){
        ContactDTO contactDTO = apiAssistant.getContactByEmail(email);
        return getAllHistory(contactDTO);
    }
    
    public List<History> getAllHistory(ContactDTO contactDTO){
        List<History> allHistory = new ArrayList<>();
        allHistory.addAll(getFormSubmissionHistories(contactDTO));
        allHistory.addAll(getPageViewHistories(contactDTO));
        allHistory.addAll(getEmailEventHistories(contactDTO));
        
        Collections.sort(allHistory);
        Collections.reverse(allHistory);
        
        return allHistory;
    }
    
    public List<History> getAllHistoryByListId(String listId, int max){
        List<String> emails = getEmailsForList(listId);
        Collections.sort(emails);
        List<History> allHistory = new ArrayList<>();
        
        for(int i=0; i<emails.size(); i++){
            
            if(i >= max)
                break;
            
            String email = emails.get(i);
            List<History> singleContactHistory = getAllHistory(email);
            allHistory.addAll(singleContactHistory);
        }
        
        return allHistory;
    }
    
    private List<String> getEmailsForList(String listId){
        List<Object> contacts = apiAssistant.getContactsByListId(listId);
        List<String> emails = new ArrayList<>();
        
        outerloop:
        for(Object obj: contacts){
            Map<String, Object> contactMap = (Map<String, Object>) obj;
            List<Object> identityProfiles = (List<Object>) contactMap.get("identity-profiles");
            List<Map<String, Object>> identities = (List<Map<String, Object>>) ((Map<String, Object>)identityProfiles.get(0)).get("identities");
            
            for(Map<String, Object> identity: identities){
                String type = (String) identity.get("type");
                if("EMAIL".equals(type)){
                    emails.add((String) identity.get("value"));
                    continue outerloop;
                }
            }
        }
        
        return emails;
    }
    
    public List<History> getFormSubmissionHistories(ContactDTO contactDTO){
        List<Map<String, Object>> formSubmissions = (List) contactDTO.getFormSubmissions();
        List<History> submissionHistoryList = new ArrayList<>();
        for(Map<String, Object> submission: formSubmissions){
            FormSubmissionHistory history = new FormSubmissionHistory();
            Long timestamp = (Long) submission.get("timestamp");
            history.setDate(new Date(timestamp));
            history.setTitle((String) submission.get("title"));
            history.setPageUrl((String) submission.get("page-url"));
            history.setEmail(contactDTO.getEmail());
            submissionHistoryList.add(history);
        }
        return submissionHistoryList;
    }
    
    public List<History> getPageViewHistories(ContactDTO contactDTO){
        List<Map<String, Object>> pageViews = (List) contactDTO.getPageViews();
        List<History> pageViewList = new ArrayList<>();
        for(Map<String, Object> pageView: pageViews){
            PageViewHistory history = new PageViewHistory();
            Long timestamp = (Long) pageView.get("timestamp");
            history.setDate(new Date(timestamp));
            history.setUrl((String) pageView.get("value"));
            history.setEmail(contactDTO.getEmail());
            
            if(history.getUrl()!=null && !history.getUrl().isEmpty())
                pageViewList.add(history);
        }
        return pageViewList;
    }
    
    public List<History> getEmailEventHistories(ContactDTO contactDTO){
        EmailEventListDTO emailEventList = apiAssistant.getEmailEventList(contactDTO.getEmail());
        List<History> emailEventHistoryList = new ArrayList<>();
        
        for(EmailEventDTO emailEvent: emailEventList.getEvents()){
            
            if(!isRequiredEmailEvent(emailEvent))
                continue;
            
            CampaignDTO campaignDTO = apiAssistant.getCampaign(emailEvent.getAppId(), emailEvent.getEmailCampaignId());
            emailEvent.setCampaignName(campaignDTO.getName());
            EmailEventHistory history = new EmailEventHistory();
            history.setDate(emailEvent.getCreated());
            history.setEventType(emailEvent.getType());
            history.setCampaignName(campaignDTO.getName());
            history.setEmail(contactDTO.getEmail());
            
            if(history.getEventType() != null)
                emailEventHistoryList.add(history);
        }
        
        return emailEventHistoryList;
    }
    
    private boolean isRequiredEmailEvent(EmailEventDTO emailEvent){
        
        for(EventType eventType: EmailEventHistory.EventType.values()){
            if(eventType.toString().equals(emailEvent.getType()))
                return true;
        }
        
        return false;
    }
    
}
