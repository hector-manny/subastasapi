package latmobile.server.subasta.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.EstadoUsuario;

public interface EstadoUsuarioService extends JpaRepository<EstadoUsuario, Integer>{

	Optional<EstadoUsuario> findByNombre(String nombre);

}
