package latmobile.server.subasta.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.Subasta;

public interface SubastaService extends JpaRepository<Subasta, Integer>{
	
}
