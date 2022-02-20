package latmobile.server.subasta.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import latmobile.server.subasta.controller.ParametroService;
import latmobile.server.subasta.entity.Parametro;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/parametro")
public class ParametroController {
	
	@Autowired
	private ParametroService parametroService;
	
	@GetMapping("/nombre/{nombre}")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> parametroPorNombre(@PathVariable String nombre) {
		try {
			return ResponseEntity.ok(new MessageResponse(true, 1, parametroService.findByNombre(nombre).get(), "Parametro: "+nombre));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se encontro el parametro"));
		}
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> editar(@RequestBody Parametro nuevoParametro) {
		try {
			Parametro encontrado = parametroService.findByNombre(nuevoParametro.getNombre()).get();
			encontrado.setValor(nuevoParametro.getValor());
			parametroService.save(encontrado);
			return ResponseEntity.ok(new MessageResponse(true, 1, encontrado, "parametro editado con exito"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "ocurrio un error al editar el parametro"));
		}
	}

}
