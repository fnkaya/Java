package config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConfig {

    private static final EntityManagerFactory entityManagerFactory;

    private JpaConfig(){}

    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("p-unit");
    }
}
