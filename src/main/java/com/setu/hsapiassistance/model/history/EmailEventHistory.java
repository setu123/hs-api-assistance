
package com.setu.hsapiassistance.model.history;

import java.util.Date;

/**
 * @date May 4, 2017
 * @author setu
 */
public class EmailEventHistory extends History{
    private Date date;
    private EventType eventType;
    private String campaignName;
    private String email;

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventTypeString the eventType to set
     */
    public void setEventType(String eventTypeString) {
        if(EventType.OPEN.toString().equals(eventTypeString))
            eventType = EventType.OPEN;
        else if(EventType.CLICK.toString().equals(eventTypeString))
            eventType = EventType.CLICK;
    }

    /**
     * @return the campaignName
     */
    public String getCampaignName() {
        return campaignName;
    }

    /**
     * @param campaignName the campaignName to set
     */
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
    
    public enum EventType{
        OPEN, CLICK 
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getAction() {
        String action = null;
        
        if(EventType.OPEN.equals(eventType))
            action = "Opened email " + campaignName;
        else if(EventType.CLICK.equals(eventType))
            action = "Clicked email " + campaignName;
        
        return action;
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
