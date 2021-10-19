package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import entities.Carrera;
import entities.Estudiante;
import model.Genero;
import model.TipoOrdenamiento;
import utils.EntMF;

import javax.persistence.Query;

public class EstudianteRepository extends AbstractRepository<Estudiante> {

	
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

	public List<Estudiante> getAllEstudiantes(){
		String query = "SELECT e "
				+ "from Estudiante e ";
		Query nativeQuery = entityManager.createQuery(query);
		List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();
		return getEstudiantes(nativeQueryResultList);
	}

	public List<Estudiante> estudiantesGenero(Genero g) {
		String query = "SELECT e "
				+ "from Estudiante e "
				+ "where e.genero = ?1 ";
		Query nativeQuery = entityManager.createQuery(query).setParameter(1, g);
		List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();
		return getEstudiantes(nativeQueryResultList);
	}
	public List<Estudiante> estudiantesOrdenados(TipoOrdenamiento tipoOrdenamiento) {
		String query = "SELECT e "
				+ "from Estudiante e "
				+ "order by e.estudianteId ";
		if(tipoOrdenamiento == TipoOrdenamiento.ASCENDENTE) {
			query += "ASC";
		} else if (tipoOrdenamiento == TipoOrdenamiento.DESCENDENTE) {
			query += "DESC";
		}
		Query nativeQuery = entityManager.createQuery(query);
		List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();
		return getEstudiantes(nativeQueryResultList);
	}

	private List<Estudiante> getEstudiantes(List<Estudiante> nativeQueryResultList) {
		List<Estudiante> estudiantes = new ArrayList<>();
		for (int i = 0; i < nativeQueryResultList.size(); i++) {
			Estudiante estudiante = new Estudiante(
					nativeQueryResultList.get(i).getEstudianteId(),
					nativeQueryResultList.get(i).getCiudad(),
					nativeQueryResultList.get(i).getEdad(),
					nativeQueryResultList.get(i).getGenero(),
					nativeQueryResultList.get(i).getNombre(),
					nativeQueryResultList.get(i).getNumDocumento()
			);
			estudiantes.add(estudiante);
		}
		return estudiantes;
	}

	public List<Estudiante> getEstudiantesByCarreraCiudad(Carrera c, String ciudad) {
		int id = c.getCarreraId();

		Query nativeQuery = entityManager.createQuery("SELECT e"
				+ " from Estudiante e "
				+ "JOIN EstudianteCarrera ec on ec.estudiante = e "
				+ "JOIN ec.carrera c on c.carreraId = ?1 "
				+ "WHERE e.ciudad like ?2").setParameter(1,id).setParameter(2,ciudad);

		List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();

		return getEstudiantes(nativeQueryResultList);
	}

}
