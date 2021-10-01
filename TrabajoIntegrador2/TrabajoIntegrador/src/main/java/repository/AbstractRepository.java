package repository;

import javax.persistence.EntityManager;

public class AbstractRepository<T> {
    protected EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void close(){
        entityManager.close();
    }
}
