package com.stack.model.dao;

import com.stack.model.DomainContext;
import org.hibernate.Session;

class Common {
    Session session = null;
    
    public void close(){
        DomainContext.closeSession(session);
    }
    
}
