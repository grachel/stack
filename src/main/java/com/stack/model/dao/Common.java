package com.stack.model.dao;

import com.stack.model.DomainContext;
import org.hibernate.Session;

public class Common {

    protected static Session getSession() {
        return DomainContext.getInstance().getSession();
    }

}
