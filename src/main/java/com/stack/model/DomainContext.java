package com.stack.model;

import com.stack.model.entities.Entities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DomainContext {

    private static SessionFactory sessionFactory = null;

    public static Session openSession() {
        return getSessionFactory().openSession();
    }

    private static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                for (Entities entity : Entities.values()) {
                    configuration.addAnnotatedClass(entity.value);
                }

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static void closeSession(Session session) {
        if(session != null){
            session.close();
        }
    }
}