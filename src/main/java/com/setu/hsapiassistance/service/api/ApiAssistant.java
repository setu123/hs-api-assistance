package com.setu.hsapiassistance.service.api;

import com.setu.hsapiassistance.model.CampaignDTO;
import com.setu.hsapiassistance.model.ContactDTO;
import com.setu.hsapiassistance.model.ContactsListDTO;
import com.setu.hsapiassistance.model.EmailEventListDTO;
import java.util.List;

/**
 * @date May 4, 2017
 * @author setu
 */
public interface ApiAssistant {

    ContactDTO getContactByEmail(String email);
    
    ContactsListDTO getContactList(Integer offset);
    
    EmailEventListDTO getEmailEventList(String email);
    
    CampaignDTO getCampaign(Integer appId, Long campaignId);
    
    List<Object> getContactsByListId(String listId);
}
