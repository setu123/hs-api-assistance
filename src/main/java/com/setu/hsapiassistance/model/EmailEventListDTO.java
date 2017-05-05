
package com.setu.hsapiassistance.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @date May 4, 2017
 * @author setu
 */
public class EmailEventListDTO {
    @JsonProperty("events")
    private List<EmailEventDTO> events;

    @JsonProperty("offset")
    private String offset;

    @JsonProperty("hasMore")
    private boolean hasMore;

    /**
     * @return the events
     */
    public List<EmailEventDTO> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<EmailEventDTO> events) {
        this.events = events;
    }

    /**
     * @return the vidOffset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * @param vidOffset the vidOffset to set
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * @return the hasMore
     */
    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * @param hasMore the hasMore to set
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
