
package com.setu.hsapiassistance.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @date May 4, 2017
 * @author setu
 */
public class EmailEventListDTO {
    @JsonProperty("contacts")
    private List<EmailEventDTO> events;

    @JsonProperty("vid-offset")
    private Long vidOffset;

    @JsonProperty("has-more")
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
    public Long getVidOffset() {
        return vidOffset;
    }

    /**
     * @param vidOffset the vidOffset to set
     */
    public void setVidOffset(Long vidOffset) {
        this.vidOffset = vidOffset;
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
