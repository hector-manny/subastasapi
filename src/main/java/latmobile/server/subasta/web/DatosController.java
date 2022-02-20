package latmobile.server.subasta.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import latmobile.server.subasta.controller.DepartamentoService;
import latmobile.server.subasta.controller.MunicipioService;
import latmobile.server.subasta.entity.Departamento;
import latmobile.server.subasta.entity.Municipio;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/datos")
public class DatosController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private MunicipioService municipioService;
	
	
	@GetMapping("/departamentos")
	public ResponseEntity<?> departamentos(){
		try {
			List<Departamento> departamentos = departamentoService.findAll();
			return ResponseEntity.ok(new MessageResponse(true, 1, departamentos, "Departamentos"));
		} catch (Exception e) {
			System.out.println("excepcion de departamentos: "+e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los departamentos, verificar datos enviados"));
		}
	}
	
	@GetMapping("/municipios/{name}")
	public ResponseEntity<?> municipiosPorDepartamento(@PathVariable String name){
		try {
			List<Municipio> municipios = municipioService.findByNombre(name);
			return ResponseEntity.ok(new MessageResponse(true, 1, municipios, "Municipios del departamento de "+name));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los municipios, veririficar datos enviados"));
		}
	}

}
