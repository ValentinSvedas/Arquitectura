package repository;

import java.util.Objects;


import entities.Estudiante;
import utils.EntMF;

public class EstudianteRepository extends AbstractRepository<Estudiante>{

	
	private static EstudianteRepository estudianteRepository;

	
	private EstudianteRepository() {
		super(EntMF.getEntityManager());
	}
	
	public static EstudianteRepository getInstance() {
		if(Objects.equals(estudianteRepository, null)) {
			estudianteRepository = new EstudianteRepository();
		}
		return estudianteRepository;
	}

}
