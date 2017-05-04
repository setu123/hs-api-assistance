
package com.setu.hsapiassistance.model.history;

import com.setu.hsapiassistance.model.history.History;
import java.util.Date;

/**
 * @date May 4, 2017
 * @author setu
 */
public class EmailEventHistory implements History{
    enum EventType{
        OPEN, CLICK 
    }

    @Override
    public Date getDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
