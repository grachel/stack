package com.stack.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DomainContext {

    private Session currentSession;
    private static DomainContext ourInstance = null;

    public static DomainContext getInstance() {
        if(ourInstance == null){
            ourInstance = new DomainContext();
        }
        return ourInstance;
    }

    private DomainContext() {
        currentSession = openCurrentSession();
    }

    private Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    private void closeCurrentSession() {
        currentSession.close();
        currentSession = null;
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session getSession() {
        return currentSession;
    }

    @Override
    protected void finalize() throws Throwable {
        if (currentSession != null) {
            closeCurrentSession();
        }
        super.finalize();
    }
}
