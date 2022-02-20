package latmobile.server.subasta.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.EstadoLote;

public interface EstadoLoteService extends JpaRepository<EstadoLote, Integer> {
	
	Optional<EstadoLote> findByNombre(String nombre);

}
