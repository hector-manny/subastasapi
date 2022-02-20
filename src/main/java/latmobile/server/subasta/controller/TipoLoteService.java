package latmobile.server.subasta.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.TipoLote;

public interface TipoLoteService extends JpaRepository<TipoLote, Integer>{

}
