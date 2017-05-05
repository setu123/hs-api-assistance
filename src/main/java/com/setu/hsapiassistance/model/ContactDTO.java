package com.setu.hsapiassistance.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.beans.Transient;
import java.util.List;
import java.util.Map;


/**
 * @date May 4, 2017
 * @author setu
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
                           "vid",
                           "properties",
                           "form-submissions"
                   })
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO
{
    @JsonProperty("vid")
    private int vid;

    @JsonProperty("properties")
    private Map<String, Map<String, Object>> properties;
    
    @JsonProperty("form-submissions")
    private List<Map<String, Object>> formSubmissions;
    
    private List<Map<String, Object>> pageViews;

    private String email;

    public int getVid()
    {
        return vid;
    }


    public void setVid(int vid)
    {
        this.vid = vid;
    }


    public Map<String, Map<String, Object>> getProperties()
    {
        return properties;
    }


    public void setProperties(Map<String, Map<String, Object>> properties)
    {
        this.properties = properties;
    }

    @Transient
    public String getPropertyValue(String propertyName){
        if(getProperties().get(propertyName)!=null)
            return getProperties().get(propertyName).get("value").toString();
        else
            return "";
    }

    @Transient
    public String getSourceType(String propertyName){
        if(getProperties().get(propertyName)!=null){
            if( getProperties().get(propertyName).get("versions") != null ){
                List versions = (List)getProperties().get(propertyName).get("versions");
                if(versions.size() > 0 ){
                    Map versionData = (Map)versions.get(0);
                    return versionData.get("source-type").toString();
                }
            }
            else
                return "";
        }
        return "";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        if(properties != null){
            Map<String, Object> emailMap = properties.get("email");
            email = (String) emailMap.get("value");
        }
        return email;
    }

    @Override
    public String toString() {
        return "ContactDTO{" + "vid=" + vid + ", email=" + getEmail() + '}';
    }

    /**
     * @return the formSubmissions
     */
    public List<Map<String, Object>> getFormSubmissions() {
        return formSubmissions;
    }

    /**
     * @param formSubmissions the formSubmissions to set
     */
    public void setFormSubmissions(List<Map<String, Object>> formSubmissions) {
        this.formSubmissions = formSubmissions;
    }

    /**
     * @return the pageViews
     */
    public List<Map<String, Object>> getPageViews() {
        Map<String, Object> lastUrl = properties.get("hs_analytics_last_url");
        if(lastUrl != null){
            pageViews = (List<Map<String, Object>>) lastUrl.get("versions");
        }
        
        return pageViews;
    }    
}
