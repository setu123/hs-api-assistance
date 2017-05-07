package com.setu.hsapiassistance.model.history;

import java.util.Date;

/**
 * @date May 4, 2017
 * @author setu
 */
public abstract class History implements Comparable<History> {

    public abstract String getEmail();

    public abstract Date getDate();

    public abstract String getAction();

    @Override
    public int compareTo(History o) {
        if (o == null) {
            return 1;
        } else {
            return this.getDate().compareTo(o.getDate());
        }
    }
}
