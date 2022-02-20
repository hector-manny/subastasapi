package latmobile.server.subasta.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.Departamento;

public interface DepartamentoService extends JpaRepository<Departamento, Integer>{

}
