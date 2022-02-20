package latmobile.server.subasta.controller;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import latmobile.server.subasta.entity.Lote;
import latmobile.server.subasta.entity.LoteParticipantes;

public interface LoteService extends JpaRepository<Lote, Integer> {
	
	@Query("SELECT l FROM Lote l WHERE l.idLote IN (SELECT u.idLote FROM UsuarioSuscripcion u WHERE u.usuario.idUsuario=?1)")
	public List<Lote> lotesPorUsuario(Integer idUsuario);
	
	@Query("SELECT lp FROM LoteParticipantes lp WHERE lp.lote.idLote=?1 AND lp.idUsuario=?2")
	public List<LoteParticipantes> permisoLotePorUsuario(Integer idLote, Integer idUsuario);
}
