package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class HibernateManager {
	
	private static HibernateManager controller;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    private HibernateManager() {
    }

    public static HibernateManager getInstance() {
        if (controller == null)
            controller = new HibernateManager();
        return controller;
    }

    public void open() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        manager = entityManagerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void close() {
        manager.close();
        entityManagerFactory.close();
    }

	
}
