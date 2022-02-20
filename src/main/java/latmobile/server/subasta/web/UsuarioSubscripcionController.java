package latmobile.server.subasta.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import latmobile.server.subasta.controller.UsuarioSuscripcionService;
import latmobile.server.subasta.entity.UsuarioSuscripcion;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usersubscripcion")

public class UsuarioSubscripcionController {
	@Autowired
	private UsuarioSuscripcionService usuarioSubscripcionService;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")

	public ResponseEntity<?> porId(@PathVariable int id) {
		try {
			ArrayList<UsuarioSuscripcion> encontrado = usuarioSubscripcionService.subscripcionPorIdUsuario(id);
			//array de los objetos
			List<Object> lista = new ArrayList<>();

			lista.addAll(encontrado);


			return ResponseEntity.ok(new MessageResponse(true, 1, lista, "Subscripciones por Usuario"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener el usuario con el id"));
		}
	}

}

