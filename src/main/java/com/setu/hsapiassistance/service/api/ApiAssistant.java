package com.setu.hsapiassistance.service.api;

import com.setu.hsapiassistance.model.APIUsageLimitDTO;
import com.setu.hsapiassistance.model.CampaignDTO;
import com.setu.hsapiassistance.model.ContactDTO;
import com.setu.hsapiassistance.model.EmailEventListDTO;
import com.setu.hsapiassistance.service.api.http.APILimitExceededException;
import java.util.List;

/**
 * @date May 4, 2017
 * @author setu
 */
public interface ApiAssistant {

    ContactDTO getContactByEmail(String email) throws APILimitExceededException;
    
//    ContactsListDTO getContactList(Integer offset) throws APILimitExceededException;
    
    EmailEventListDTO getEmailEventList(String email) throws APILimitExceededException;
    
    CampaignDTO getCampaign(Integer appId, Long campaignId) throws APILimitExceededException;
    
    List<Object> getContactsByListId(String listId) throws APILimitExceededException;
    
    APIUsageLimitDTO getAPIUsageLimit() throws APILimitExceededException;
}
