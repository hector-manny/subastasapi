package latmobile.server.subasta.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.EstadoSub;

public interface EstadoSubService extends JpaRepository<EstadoSub, Integer>{
	
	Optional<EstadoSub> findByNombre(String nombre);

}
