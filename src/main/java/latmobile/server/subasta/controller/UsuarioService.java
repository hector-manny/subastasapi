package latmobile.server.subasta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import latmobile.server.subasta.entity.Usuario;

public interface UsuarioService extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByCorreo(String correo);

	Boolean existsByCorreo(String username);

	@Query("SELECT u FROM Usuario u WHERE u.estadoUsuario.nombre='NO VERIFICADO'")
	List<Usuario> usuariosNoVerificados();
	
	@Query("SELECT u FROM Usuario u WHERE u.idUsuario IN (SELECT u.idUsuario FROM LoteParticipantes u WHERE u.lote.idLote=?1) AND u.idUsuario NOT IN (SELECT us.usuario.idUsuario FROM UsuarioSuscripcion us WHERE us.idLote=?1) ")
	public List<Usuario> usuariosPermisoInscripcion(Integer idLote);
	
	@Query("SELECT u FROM Usuario u WHERE u.idUsuario IN (SELECT us.usuario.idUsuario FROM UsuarioSuscripcion us WHERE us.idLote=?1 AND us.valid=1)")
	public List<Usuario> usuariosInscritosValidados(Integer idLote);
	
	@Query("SELECT u FROM Usuario u WHERE u.idUsuario IN (SELECT us.usuario.idUsuario FROM UsuarioSuscripcion us WHERE us.idLote=?1 AND us.valid=0)")
	public List<Usuario> usuariosInscritosNoValidados(Integer idLote);

	@Query("SELECT u FROM Usuario u WHERE u.estadoUsuario.nombre='VERIFICADO'")
	List<Usuario> usuariosVerificados();
	
	@Query("SELECT u FROM Usuario u WHERE u.rol.nombre='USUARIO' AND u.estadoUsuario.nombre='VERIFICADO' AND u.idUsuario NOT IN (SELECT u.idUsuario FROM LoteParticipantes u WHERE u.lote.idLote=?1)")
	List<Usuario> usuariosNoInscritosLote(Integer idLote);
	
	Optional<Usuario> findByToken(String token);
}
