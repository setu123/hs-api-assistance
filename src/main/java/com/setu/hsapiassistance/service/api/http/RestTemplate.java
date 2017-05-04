package com.setu.hsapiassistance.service.api.http;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.fluent.Request;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @date May 4, 2017
 * @author setu
 */
public class RestTemplate {
    
    private ObjectMapper objectMapper;

    public RestTemplate() {
        objectMapper = new ObjectMapper();
    }

    public <T extends Object> T getForObject(String url, Class<T> responseType) throws RestException {
        System.out.println("Url to connect: " + url);
        try {
            String responseString = getResponseString(url);
            T responseObject = objectMapper.readValue(responseString, responseType);
            return responseObject;
        } catch (IOException ex) {
            throw new RestException(ex);
        }
    }
    
    private String getResponseString(String url){
        String responseString = null;
        
        try {
            responseString = Request.Get(url)
                    .connectTimeout(60000)
                    .socketTimeout(60000)
                    .execute().returnContent().asString();
        } catch (IOException ex) {
            Logger.getLogger(RestTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return responseString;
    }
}
