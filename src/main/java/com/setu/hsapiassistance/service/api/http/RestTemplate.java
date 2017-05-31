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
    private final long MINIMUM_DELAY_TIME = 2000;
    private final long MAXIMUM_DELAY_TIME = 10000;

    public RestTemplate() {
        objectMapper = new ObjectMapper();
    }

    public <T extends Object> T getForObject(String url, Class<T> responseType) throws RestException {
//        System.out.println("Url to connect: " + url);
        try {
            String responseString = getResponseString(url);
            T responseObject = null;
            
            if(responseString != null)
                responseObject = getObjectMapper().readValue(responseString, responseType);
            
            return responseObject;
        } catch (IOException ex) {
            throw new RestException(ex);
        }
    }
   
    private String getResponseString(String url){
        return getResponseString(url, MINIMUM_DELAY_TIME);
    }
   
    private String getResponseString(String url, long retryIn){
        String responseString = null;
       
        try {
            responseString = Request.Get(url)
                    .connectTimeout(60000)
                    .socketTimeout(60000)
                    .execute().returnContent().asString();
        } catch (IOException ex) {
//            System.out.println("Exception caught: " + ex.getMessage());
            try {
                Thread.sleep(retryIn);
            } catch (InterruptedException ex1) {
                System.err.println("Thread sleep interrupted");
            }
           
            if(retryIn*2 <= MAXIMUM_DELAY_TIME)
                return getResponseString(url, retryIn*2);
            else{
                System.out.println("Giving up " + url);
                return responseString;
            }
        }
       
        return responseString;
    }

    /**
     * @return the objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}