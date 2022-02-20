package latmobile.server.subasta.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import latmobile.server.subasta.entity.LoteParticipantes;

public interface LoteParticipantesService extends JpaRepository<LoteParticipantes, Integer>{
	
	
	@Query("SELECT u FROM LoteParticipantes u WHERE u.idUsuario=?1 AND u.lote.idLote=?2")
	public List<LoteParticipantes> tieneParticipantes(Integer id, Integer idlote);
}
