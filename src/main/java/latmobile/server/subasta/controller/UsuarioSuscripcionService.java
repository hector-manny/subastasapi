package latmobile.server.subasta.controller;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import latmobile.server.subasta.entity.UsuarioSuscripcion;

public interface UsuarioSuscripcionService extends JpaRepository<UsuarioSuscripcion, Integer>{
	
	@Query("SELECT us FROM UsuarioSuscripcion us WHERE us.usuario.idUsuario=?1 AND us.idLote=?2")
	public UsuarioSuscripcion suscripcionPorUsuarioAndLote(Integer id, Integer idlote);
	
	@Query("SELECT us FROM UsuarioSuscripcion us WHERE us.usuario.idUsuario=?1")
	public ArrayList<UsuarioSuscripcion> subscripcionPorIdUsuario(Integer id);

}
