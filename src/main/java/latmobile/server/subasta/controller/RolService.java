package latmobile.server.subasta.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.Rol;

public interface RolService extends JpaRepository<Rol, Integer>{

	Optional<Rol> findByNombre(String nombre);

}
