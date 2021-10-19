package utils;

import rest.CarreraController;
import rest.EstudianteCarreraController;
import rest.EstudianteController;

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
		emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		EstudianteController estudianteController = new EstudianteController();
		estudianteController.createEstudiantes();

		CarreraController carreraController = new CarreraController();
		carreraController.createCarreras();

		EstudianteCarreraController estudianteCarreraController = new EstudianteCarreraController();
		estudianteCarreraController.addEstudiantesCarrera();

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		emf.close();
	}

	public static EntityManager getEntityManager() {
		if(Objects.equals(emf,null)) {
			throw new IllegalStateException("Contexto no inicializado");
		}
		return emf.createEntityManager();
	}
}
