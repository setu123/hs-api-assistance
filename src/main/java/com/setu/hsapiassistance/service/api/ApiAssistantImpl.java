
package com.setu.hsapiassistance.service.api;

import com.setu.hsapiassistance.model.APIUsageLimitDTO;
import com.setu.hsapiassistance.model.CampaignDTO;
import com.setu.hsapiassistance.model.ContactDTO;
import com.setu.hsapiassistance.model.EmailEventListDTO;
import com.setu.hsapiassistance.service.api.http.APILimitExceededException;
import com.setu.hsapiassistance.service.api.http.RestException;
import com.setu.hsapiassistance.service.api.http.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date May 4, 2017
 * @author setu
 */
public class ApiAssistantImpl implements ApiAssistant{
    RestTemplate restTemplate;
    
    private final String apiKey;
    private static final String BASE_URL = "http://api.hubapi.com";
    private final Map<String, CampaignDTO> campaignCache;

    public ApiAssistantImpl(String apiKey) {
        this.apiKey = apiKey;
        restTemplate = new RestTemplate();
        campaignCache = new HashMap<>();
    }

    @Override
    public ContactDTO getContactByEmail(String email) throws APILimitExceededException {
        ContactDTO contact = null;
        try {
            String url = getContactByEmailUrl(email);
            contact = restTemplate.getForObject(url, ContactDTO.class);
        } catch (RestException ex) {
//            System.err.println("RestException caught: " + ex.getMessage());
        }
        
        return contact;
    }

//    @Override
//    public ContactsListDTO getContactList(Integer offset) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public EmailEventListDTO getEmailEventList(String email) throws APILimitExceededException {
        
        return getEmailEventList(email, null);
    }
        
    private EmailEventListDTO getEmailEventList(String email, String offset) throws APILimitExceededException {
        EmailEventListDTO emailEventList = null;
        try {
            String url = getEmailEventListUrl(email, offset);
            emailEventList = restTemplate.getForObject(url, EmailEventListDTO.class);
        } catch (RestException ex) {
//            System.err.println("RestException caught: " + ex.getMessage());
        }
        
        if(emailEventList!=null && emailEventList.isHasMore()){
            offset = emailEventList.getOffset();
            EmailEventListDTO nestedEmailEventList = getEmailEventList(email, offset);
            if(nestedEmailEventList != null)
                emailEventList.getEvents().addAll(nestedEmailEventList.getEvents());
        }
        
        return emailEventList;
    }

    @Override
    public CampaignDTO getCampaign(Integer appId, Long campaignId) throws APILimitExceededException {
        String campaignCacheKey = appId + "_" + campaignId;
        if(campaignCache.get(campaignCacheKey) != null)
            return campaignCache.get(campaignCacheKey);
        
        CampaignDTO campaign = null;
        
        try {            
            String url = getCampaignUrl(appId, campaignId);
            campaign = restTemplate.getForObject(url, CampaignDTO.class);
        } catch (RestException ex) {
//            System.err.println("RestException caught: " + ex.getMessage());
        }
        
        if(campaign != null)
            campaignCache.put(campaignCacheKey, campaign);
        
        return campaign;
    }
    
    @Override
    public List<Object> getContactsByListId(String listId) throws APILimitExceededException {
        List contacts = new ArrayList();
        
        try {            
            String url = getContactsByListIdUrl(listId);
            Map<String, List<Object>> contactsMap = restTemplate.getForObject(url, Map.class);
            
            if(contactsMap != null)
                contacts = contactsMap.get("contacts");
        } catch (RestException ex) {
//            System.err.println("RestException caught: " + ex.getMessage());
        }
        
        return contacts;
    }
    
    private String getContactByEmailUrl(String email){
        return BASE_URL + "/contacts/v1/contact/email" + "/" + email + "/profile" + "?hapikey=" + apiKey;
    }
    
    private String  getEmailEventListUrl(String email, String offset){
        String offsetToAppend = (offset==null) ? "":"&offset="+offset;
        return BASE_URL + "/email/public/v1/events" + "?hapikey=" + apiKey + "&recipient=" + email + "&limit=1000" + offsetToAppend;
    }
    
    private String getCampaignUrl(Integer appId, Long campaignId){
        return BASE_URL + "/email/public/v1/campaigns/" + campaignId + "?hapikey=" + apiKey + "&appId=" + appId;
    }

    private String getContactsByListIdUrl(String listId){
        return BASE_URL + "/contacts/v1/lists/" + listId + "/contacts/all" + "?hapikey=" + apiKey;
    }
    
    private String getAPILimitUrl(){
        return BASE_URL + "/integrations/v1/limit/daily" + "?hapikey=" + apiKey;
    }

    @Override
    public APIUsageLimitDTO getAPIUsageLimit() throws APILimitExceededException {
        APIUsageLimitDTO usageDTO = null;
        try {
        String url = getAPILimitUrl();
            APIUsageLimitDTO[] usageDTOArray = restTemplate.getForObject(url, APIUsageLimitDTO[].class);
            if(usageDTOArray!=null && usageDTOArray.length == 1)
                usageDTO = usageDTOArray[0];
        } catch (RestException ex) {
            System.err.println("Fatal error. Unexpected exception caught: " + ex.getMessage());
            }
        
        return usageDTO;
    }
}
