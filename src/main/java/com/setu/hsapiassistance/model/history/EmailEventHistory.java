
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
        else if(EventType.SENT.toString().equals(eventTypeString))
            eventType = EventType.SENT;
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
        OPEN, CLICK, SENT 
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getAction() {
        String action = null;
        
        if(null != eventType)
            switch (eventType) {
            case OPEN:
                action = "Opened email " + campaignName;
                break;
            case CLICK:
                action = "Clicked email " + campaignName;
                break;
            case SENT:
                action = "Was sent email " + campaignName;
                break;
            default:
                break;
        }
        
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
