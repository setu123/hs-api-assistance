package com.setu.hsapiassistance.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;


/**
 * @date May 4, 2017
 * @author setu
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsListDTO
{
    @JsonProperty("contacts")
    private List<ContactDTO> contacts;

    @JsonProperty("vid-offset")
    private Long vidOffset;

    @JsonProperty("has-more")
    private boolean hasMore;


    public List<ContactDTO> getContacts()
    {
        return contacts;
    }


    public void setContacts(List<ContactDTO> contacts)
    {
        this.contacts = contacts;
    }


    public boolean isHasMore()
    {
        return hasMore;
    }


    public void setHasMore(boolean hasMore)
    {
        this.hasMore = hasMore;
    }


    public Long getVidOffset()
    {
        return vidOffset;
    }


    public void setVidOffset(Long vidOffset)
    {
        this.vidOffset = vidOffset;
    }


}
