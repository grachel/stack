package com.stack.model.dao;

import org.hibernate.Session;

class Common {
    Session session = null;
    
    public void close(){
        DomainContext.closeSession(session);
    }
    
}
