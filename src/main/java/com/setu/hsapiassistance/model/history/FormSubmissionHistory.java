
package com.setu.hsapiassistance.model.history;

import java.util.Date;

/**
 * @date May 4, 2017
 * @author setu
 */
public class FormSubmissionHistory extends History{
    private Date date;
    private String pageUrl;
    private String title;
    private String email;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getAction() {
        return "Submitted " + title;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the pageUrl
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * @param pageUrl the pageUrl to set
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
