package hibernate;

import config.HibernateConfig;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HibernateTests {

    Logger logger = LogManager.getLogger();

    @Test
    public void testHibernateBootstrapping(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
    }

    @Test
    public void testPersist(){
        Pet beyaz = new Pet();
        beyaz.setName("Beyaz");

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(beyaz);

        transaction.commit();
        session.close();
    }

    @Test
    public void testMerge(){
        Pet beyaz = new Pet();
        beyaz.setName("Beyaz");

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Pet mergedPet = (Pet) session.merge(beyaz);
        beyaz.setName("Mahmut Beyaz");

        logger.warn("Pet: " + Optional.ofNullable(beyaz.getId()));
        logger.warn("Merged Pet: " + mergedPet.getId());
        logger.warn("Equality => " + (beyaz == mergedPet));
        logger.warn(beyaz +" --- "+ mergedPet);

        transaction.commit();
        session.close();
    }

    @Test
    public void testUpdate(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Pet pet = session.get(Pet.class, 1L);
        pet.setName("Test Update");

        transaction.commit();
        session.close();
    }

    @Test //? Getter Setter olmadan çalışır
    public void testFieldLevelAccess(){
        Pet beyaz = new Pet("Beyaz", new Date());

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(beyaz);
        transaction.commit();
        session.close();

        session = HibernateConfig.getSessionFactory().openSession();
        System.out.println(session.get(Pet.class, 1L));
        session.close();
    }

    @Test //? Persist with flush (hibernate.allow_update_outside_transaction=true)
    public void testWithoutTransaction(){
        Pet beyaz = new Pet("Beyaz", new Date());

        Session session = HibernateConfig.getSessionFactory().openSession();
        session.persist(beyaz);
        session.flush();
        session.close();
    }

    @Test
    public void testCheckNullability(){
        Pet pet = new Pet();

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(pet);
        transaction.commit();
        session.close();
    }

    @Test
    public void testCompositePK(){
        OwnerCompositePK.OwnerId id = new OwnerCompositePK.OwnerId("Muhammed Said", "Kaya");
        OwnerCompositePK owner = new OwnerCompositePK(id);

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(owner);
        transaction.commit();
        session.close();

        session = HibernateConfig.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.persist(owner);
        transaction.commit();
        session.close();
    }

    @Test
    public void testEmbeddable(){
        Owner owner = new Owner("Furkan Kaya",
                new Address("İşçi Blokları", "05554443322"),
                Owner.Rating.STANDART);

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(owner);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGet(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Pet pet = session.get(Pet.class, 1L);
        logger.warn(pet.toString() +" "+ pet.getClass());
        Pet pet2 = session.get(Pet.class, 1L);
        logger.warn(pet == pet2);
        session.close();
    }

    @Test //? Lazy initialization with proxy class
    public void testLoad(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Pet pet = session.load(Pet.class, 1L);
        logger.warn(pet.toString() +" "+ pet.getClass());
        Pet pet2 = session.load(Pet.class, 1L);
        logger.warn(pet == pet2);
    }

    @Test
    public void testIdentifierLoadAccess(){
        Session session = HibernateConfig.getSessionFactory().openSession();

        IdentifierLoadAccess<Pet> petIdentifierLoadAccess = session.byId(Pet.class);
        Pet pet = petIdentifierLoadAccess.load(1L);
        logger.warn(pet.toString() +" "+ pet.getClass());

        Pet pet2 = petIdentifierLoadAccess.getReference(2L);
        logger.warn(pet2.toString() +" "+ pet2.getClass());
    }

    @Test
    public void testMultiIdentifierLoadAccess(){
        Session session = HibernateConfig.getSessionFactory().openSession();

        MultiIdentifierLoadAccess<Pet> petMultiIdentifierLoadAccess = session.byMultipleIds(Pet.class);
        List<Pet> petList = petMultiIdentifierLoadAccess.multiLoad(1L, 2L, 3L, 4L, 5L);
        petList.forEach(System.out::println);
    }

    @Test //? (hibernate.generate_statistics=true)
    public void testStatistics(){
        Session session = HibernateConfig.getSessionFactory().openSession();

        session.persist(new Pet("Limon", new Date()));
        session.flush();
        session.get(Pet.class, 1L);
        session.createQuery("SELECT p.name FROM Pet p").getResultList();

        Statistics statistics = HibernateConfig.getSessionFactory().getStatistics();
        EntityStatistics entityStatistics = statistics.getEntityStatistics("entity.Pet");
        QueryStatistics queryStatistics = statistics.getQueryStatistics("SELECT p.name FROM Pet p");

        logger.warn("Load count: " + entityStatistics.getLoadCount());
        logger.warn("Insert count: " + entityStatistics.getInsertCount());
        logger.warn("Query exec count: " + queryStatistics.getExecutionCount());
        logger.warn("Query avg exec time: " + queryStatistics.getExecutionAvgTime());
    }
}
