package hibernate;

import config.HibernateConfig;
import config.JpaConfig;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class JpaTest {

    Logger logger = LogManager.getLogger();

    @Test
    public void testJpaBootstrapping(){
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        transaction.commit();
        entityManager.close();
        JpaConfig.getEntityManagerFactory().close();
    }

    @Test //? hibernate.allow_update_outside_transaction=true
    public void testWithoutTransaction(){
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        Pet kara = new Pet("Kara", new Date());
        entityManager.persist(kara);
        entityManager.flush();
        entityManager.close();
    }

    @Test
    public void testFind(){
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        Pet pet = entityManager.find(Pet.class, 1L);
        logger.warn(pet.toString() +" "+ pet.getClass());
        Pet pet2 = entityManager.find(Pet.class, 1L);
        logger.warn(pet == pet2);
    }

    @Test
    public void testGetReference(){
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        Pet pet = entityManager.getReference(Pet.class, 1L);
        logger.warn(pet.toString() +" "+ pet.getClass());
    }
}
