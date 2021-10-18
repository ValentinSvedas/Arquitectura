package repository;

import javax.persistence.EntityManager;

public class AbstractRepository<T> {
	Class<T> entityClass;
     EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
    }

    public void add(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }
    
      public T findById(int id) {
    	T entity = this.entityManager.find(entityClass, id);
    	return entity;
    }
    
    public void close(){
        entityManager.close();
    }
}
