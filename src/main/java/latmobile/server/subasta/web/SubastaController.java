package latmobile.server.subasta.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import latmobile.server.subasta.controller.LoteParticipantesService;
import latmobile.server.subasta.controller.LoteService;
import latmobile.server.subasta.controller.SubastaService;
import latmobile.server.subasta.entity.Lote;
import latmobile.server.subasta.entity.LoteParticipantes;
import latmobile.server.subasta.entity.Subasta;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subasta")
public class SubastaController {
	
	@Autowired
	private SubastaService subastaService;
	
	@Autowired
	private LoteParticipantesService loteParticipantesService;
	
	@Autowired
	private LoteService loteService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> postSubasta(@RequestBody Subasta payload) {
		try {
			subastaService.save(payload);
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Subasta creada con exito"));
		} catch (Exception e) {
			System.out.println("-------excepcion de subasta: "+e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se pudo crear la subasta"));
		}
	}
	
	@GetMapping("")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> subastas(){
		try {
			List<Subasta> subastas = subastaService.findAll().stream().filter((Subasta sub) -> {
                return sub.getDelete() == false;
            }).collect(Collectors.toList());
			//return ResponseEntity.ok(subastas);
			return ResponseEntity.ok(new MessageResponse(true, 1, subastas, "Lista de subastas"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Ocurrio un error al intentar obtener las subastas"));
		}
	}
	
	@GetMapping("/activas")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> activas(){
		try {
			List<Subasta> filtradas = subastaService.findAll().stream().filter((Subasta sub) -> {
                return sub.getActivo() == true;
            }).collect(Collectors.toList());
			return ResponseEntity.ok(new MessageResponse(true, 1, filtradas, "Lista de subastas activas"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Ocurrio un error al intentar obtener las subastas"));
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> live(@PathVariable int id){
		try {
			List<Object> lista = new ArrayList<>();
			lista.add(subastaService.findById(id).get());
			return ResponseEntity.ok(new MessageResponse(true, 1,lista , "Subasta con identificador: "+id));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(false, 7, new ArrayList<>(), "No se encontro la subasta por id"));
		}
	}
	
	
	@GetMapping("/{id}/lotes")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> lotesPorSubasta(@PathVariable int id){
		try {
			return ResponseEntity.ok(new MessageResponse(true, 1, subastaService.findById(id).get().getLoteList(), "Lotes de la Subasta con identificador: "+id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los lotes por id"));
		}
	}
	
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> editSubasta(@RequestBody Subasta payload) {
		try {
			List<Lote> lotes;
			Subasta s = subastaService.findById(payload.getIdSubasta()).get();
			lotes =s.getLoteList();
			System.out.println("cantidad de lotes"+lotes.size());
			for (Lote lote : lotes) {
				lote.setFechaInicio(s.getFechaInicio());
				lote.setHoraInicio(s.getHoraDefecto());
				lote.setFechaFin(s.getFechaFin());
				lote.setHoraFin(s.getHoraFin());
				loteService.save(lote);
			}
			subastaService.save(payload);
			//System.out.println("se edita subasta: "+payload.getIdSubasta());
			//return ResponseEntity.ok("Subasta editada con exito");
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Subasta con identificador: "+payload.getIdSubasta()+"editada"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al editar la subasta"));
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> deleteSubasta(@PathVariable Integer id) {
		//System.out.println("se elimina subasta: "+payload.getIdSubasta());
		try {
			//payload.setDelete(false);
			//subastaService.save(payload);
			Subasta borrar = subastaService.findById(id).get();
			System.out.println("se elimina subasta: "+id);
			borrar.setDelete(true);
			subastaService.save(borrar);
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Subasta eliminada con exito "));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al eliminar la subasta"));
		}
	}
	
	@PostMapping(path = "/addparticipante",consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> añadirParticipante(@RequestBody Map<String, Object> payload) {
		try {
			//subastaService.save(payload);
			if ((payload.containsKey("idSubasta")) && (payload.get("idSubasta")!=null) && (payload.containsKey("idUsuario")) && (payload.get("idUsuario")!=null) ) {
				Subasta encontrada = subastaService.findById(Integer.parseInt(""+payload.get("idSubasta"))).get();
				Boolean tiene = false;
				List<LoteParticipantes> par;
				for (Lote lote : encontrada.getLoteList()) {
					par = loteParticipantesService.tieneParticipantes(Integer.parseInt(""+payload.get("idUsuario")), lote.getIdLote());
					//System.out.println("tamanio: "+par.size());
					if(par.size()>0) {
						tiene=true;
					}
				}
				if(!tiene) {
					for (Lote lote : encontrada.getLoteList()) {
						LoteParticipantes nuevo = new LoteParticipantes();
						nuevo.setIdUsuario(Integer.parseInt(""+payload.get("idUsuario")));
						nuevo.setLote(lote);
						loteParticipantesService.save(nuevo);
					}
					return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Participacion creada con exito"));
				}else {
					return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Parece ser que ya estas partocipando en esta subasta"));
				}
				
			}else {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se enviaron todos loo datos"));
			}
			
		} catch (Exception e) {
			System.out.println("-------excepcion de participar: "+e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se pudo crear la participacion"));
		}
	}

}
