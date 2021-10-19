package repository;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

public class AbstractRepository<T> {
	Class<T> entityClass;
    EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityClass = (Class<T>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
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
