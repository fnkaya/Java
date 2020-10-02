package config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static final SessionFactory sessionFactory;

    private HibernateConfig(){}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static {
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();

    }
}
