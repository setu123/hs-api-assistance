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
                           "properties"
                   })
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO
{
    @JsonProperty("vid")
    private int vid;

    @JsonProperty("properties")
    private Map<String, Map<String, Object>> properties;


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


}
