package latmobile.server.subasta.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.Parametro;

public interface ParametroService extends JpaRepository<Parametro, Integer>{
	
	Optional<Parametro> findByNombre(String nombre);

}
