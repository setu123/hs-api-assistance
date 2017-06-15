package com.setu.hsapiassistance.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author setu
 * @date Jun 16, 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIUsageLimitDTO {
    private String name;
    private int usageLimit;
    private int currentUsage;
    private long collectedAt;
    private String fetchStatus;
    private long resetsAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(int currentUsage) {
        this.currentUsage = currentUsage;
    }

    public long getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(long collectedAt) {
        this.collectedAt = collectedAt;
    }

    public String getFetchStatus() {
        return fetchStatus;
    }

    public void setFetchStatus(String fetchStatus) {
        this.fetchStatus = fetchStatus;
    }

    public long getResetsAt() {
        return resetsAt;
    }

    public void setResetsAt(long resetsAt) {
        this.resetsAt = resetsAt;
    }
}
