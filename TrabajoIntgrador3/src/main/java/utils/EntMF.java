package utils;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntMF implements ServletContextListener {

	public static javax.persistence.EntityManagerFactory emf;
	
	public void contextInitialized(ServletContextEvent arg) {
		emf =Persistence.createEntityManagerFactory("PersistenceUnit");
	}
	
	public void destroyContext(ServletContextEvent arg) {
		emf.close();
	}
	public static EntityManager getEntityManager() {
		if(Objects.equals(emf,null)) {
			throw new IllegalStateException("Contexto no inicializado");
		}
		return emf.createEntityManager();
	}
}
