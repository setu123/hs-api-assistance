
package com.setu.hsapiassistance.service.api;

import com.setu.hsapiassistance.model.CampaignDTO;
import com.setu.hsapiassistance.model.ContactDTO;
import com.setu.hsapiassistance.model.ContactsListDTO;
import com.setu.hsapiassistance.model.EmailEventListDTO;
import com.setu.hsapiassistance.service.api.http.RestException;
import com.setu.hsapiassistance.service.api.http.RestTemplate;

/**
 * @date May 4, 2017
 * @author setu
 */
public class ApiAssistantImpl implements ApiAssistant{
    RestTemplate restTemplate;
    
    private final String apiKey;
    private static final String BASE_URL = "http://api.hubapi.com";

    public ApiAssistantImpl(String apiKey) {
        this.apiKey = apiKey;
        restTemplate = new RestTemplate();
        
    }

    @Override
    public ContactDTO getContactByEmail(String email) {
        ContactDTO contact = null;
        try {
            String url = getContactByEmailUrl(email);
            contact = restTemplate.getForObject(url, ContactDTO.class);
        } catch (RestException ex) {
            System.err.println("RestException caught: " + ex.getMessage());
        }
        
        return contact;
    }

    @Override
    public ContactsListDTO getContactList(Integer offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmailEventListDTO getEmailEventList(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CampaignDTO getCampaign(Integer appId, Long campaignId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String getContactByEmailUrl(String email){
        return BASE_URL + "/contacts/v1/contact/email" + "/" + email + "/profile" + "?hapikey=" + apiKey;
    }

}
