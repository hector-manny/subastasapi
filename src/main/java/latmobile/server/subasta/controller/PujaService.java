package latmobile.server.subasta.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import latmobile.server.subasta.entity.Puja;

public interface PujaService extends JpaRepository<Puja, Integer>{
	
	@Query("SELECT p FROM Puja p WHERE p.lote.idLote=?1 ORDER BY p.idPuja DESC")
	public List<Puja> pujasDescendete(Integer idLote);

}
