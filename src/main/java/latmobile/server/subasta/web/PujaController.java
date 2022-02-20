package latmobile.server.subasta.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import latmobile.server.subasta.controller.LoteService;
import latmobile.server.subasta.controller.PujaService;
import latmobile.server.subasta.controller.UsuarioService;
import latmobile.server.subasta.entity.Lote;
import latmobile.server.subasta.entity.Puja;
import latmobile.server.subasta.entity.Usuario;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/puja")
public class PujaController {
	
	@Autowired
	private PujaService pujaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LoteService loteService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")

	public ResponseEntity<?> postSubasta(@RequestBody Map<String, Object> payload) {
		try {
			if ((payload.containsKey("idLote")) && (payload.get("idLote")!=null) && (payload.containsKey("idUsuario")) && (payload.get("idUsuario")!=null) && (payload.containsKey("monto")) && (payload.get("monto")!=null) ) {
				BigDecimal monto = new BigDecimal(""+payload.get("monto"));
				Date fecha = new Date();
				Usuario user = usuarioService.findById(Integer.parseInt(""+payload.get("idUsuario"))).get();
				Lote lot = loteService.findById(Integer.parseInt(""+payload.get("idLote"))).get();
				Puja nuevaPuja = new Puja();
				nuevaPuja.setFecha(fecha);
				nuevaPuja.setUsuario(user);
				nuevaPuja.setLote(lot);
				nuevaPuja.setMonto(monto);
				pujaService.save(nuevaPuja);
				return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Puja creada con exito"));
			}else {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "error en los datos enviados"));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "ocurrio un error"));
		}
	}
	
	@GetMapping("/last/{id}")
	@PreAuthorize("hasRole('USUARIO')")

	public ResponseEntity<?> verificar(@PathVariable int id) {
		try {
			//System.out.println("id:"+id);
			List<Puja> pujas = pujaService.pujasDescendete(id);
			//System.out.println(pujas.size());
			if (pujas.size()==0) {
				return ResponseEntity.ok(new MessageResponse(true, 1, 0, "el lote solicitado no tiene pujas disponibles"));
			}else {
				return ResponseEntity.ok(new MessageResponse(true, 1, pujas.get(0).getMonto(), "Ultima puja del lote "+id));
			}
		} catch (Exception e) {
			System.out.println("excepcion: "+e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "ocurrio un error"));
			//return ResponseEntity.badRequest().body(new MessageResponse("error al obtener la puja"));
		}
	}
	
	@MessageMapping("/nuevapuja")
    @SendTo("/topic/pujas")

	public ResponseEntity<?> websocket(Map<String, Object> payload) {
		try {
			if ((payload.containsKey("idLote")) && (payload.get("idLote")!=null) && (payload.containsKey("idUsuario")) && (payload.get("idUsuario")!=null) && (payload.containsKey("monto")) && (payload.get("monto")!=null) ) {
				BigDecimal monto = new BigDecimal(""+payload.get("monto"));
				Date fecha = new Date();
				Usuario user = usuarioService.findById(Integer.parseInt(""+payload.get("idUsuario"))).get();
				Lote lot = loteService.findById(Integer.parseInt(""+payload.get("idLote"))).get();
				Puja nuevaPuja = new Puja();
				nuevaPuja.setFecha(fecha);
				nuevaPuja.setUsuario(user);
				nuevaPuja.setLote(lot);
				nuevaPuja.setMonto(monto);
				pujaService.save(nuevaPuja);
				int id = Integer.parseInt(""+payload.get("idLote"));
				List<Puja> pujas = pujaService.pujasDescendete(id);
				if (pujas.size()==0) {
					return ResponseEntity.ok(new MessageResponse(true, 1, 0, "el lote solicitado no tiene pujas disponibles"));
				}else {
					return ResponseEntity.ok(new MessageResponse(true, 1, pujas.get(0).getMonto(), "Ultima puja del lote "+id));
				}
			}else {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Parece que no has enviado todos los datos requeridos"));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No pudimos obtener la ultima puja del lote "));
		}
	}

}
