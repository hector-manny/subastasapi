package latmobile.server.subasta.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import latmobile.server.subasta.entity.Municipio;

public interface MunicipioService extends JpaRepository<Municipio, Integer>{
	
	@Query("SELECT m FROM Municipio m WHERE m.departamento.idDepartamento=?1")
	public List<Municipio> findByDepartamento(int idDepartamento);
	
	@Query("SELECT m FROM Municipio m WHERE m.departamento.nombre=?1")
	public List<Municipio> findByNombre(String nombre);

}
