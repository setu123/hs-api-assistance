
package com.setu.hsapiassistance.model.history;

import java.util.Date;

/**
 * @date May 4, 2017
 * @author setu
 */
public class PageViewHistory extends History{
    private String url;
    private Date date;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getAction() {
        return "Visited " + url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
