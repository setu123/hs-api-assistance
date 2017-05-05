package com.setu.hsapiassistance.model;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @date May 4, 2017
 * @author setu
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailEventDTO {

    private String type;
    private Integer appId;
    private Long emailCampaignId;
    private Date created;
    private String campaignName;

    public String getCampaignName() {        
        return campaignName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return the emailCampaignId
     */
    public Long getEmailCampaignId() {
        return emailCampaignId;
    }

    /**
     * @param emailCampaignId the emailCampaignId to set
     */
    public void setEmailCampaignId(Long emailCampaignId) {
        this.emailCampaignId = emailCampaignId;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @param campaignName the campaignName to set
     */
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
