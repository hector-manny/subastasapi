package latmobile.server.subasta.web;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import latmobile.server.subasta.controller.LoteParticipantesService;
import latmobile.server.subasta.controller.LoteService;
import latmobile.server.subasta.controller.UsuarioService;
import latmobile.server.subasta.controller.UsuarioSuscripcionService;
import latmobile.server.subasta.entity.LoteParticipantes;
import latmobile.server.subasta.entity.Usuario;
import latmobile.server.subasta.entity.UsuarioSuscripcion;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LoteService loteService;
	
	@Autowired
	private LoteParticipantesService loteParticipantesService;
	
	@Autowired
	private UsuarioSuscripcionService usuarioSuscripcionService;
	
	@Autowired
    private JavaMailSender emailSender;

	@Qualifier("getFreeMarkerConfiguration")
	@Autowired
	private Configuration freemakerConfig;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> porId(@PathVariable int id) {
		try {
			Usuario encontrado = usuarioService.findById(id).get();
			List<Object> lista = new ArrayList<>();
			lista.add(encontrado);
			return ResponseEntity.ok(new MessageResponse(true, 1, lista, "Usuario encontrado"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener el usuario con el id"));
		}
	}
	
	@GetMapping("/lote/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> usuariosPorLoteNo(@PathVariable int id) {
		try {
			List<Usuario> users = usuarioService.usuariosInscritosNoValidados(id);
			return ResponseEntity.ok(new MessageResponse(true, 1, users, "Usuarios del lote: "+id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los usuarios con el id "+id+" de lote"));
		}
	}
	@GetMapping("/lote/{id}/valid")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> usuariosPorLoteSi(@PathVariable int id) {
		try {
			List<Usuario> users = usuarioService.usuariosInscritosValidados(id);
			return ResponseEntity.ok(new MessageResponse(true, 1, users, "Usuarios del lote: "+id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los usuarios con el id "+id+" de lote"));
		}
	}
	
	@GetMapping("/verificados")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> usuariosVerificados() {
		try {
			List<Usuario> users = usuarioService.usuariosVerificados();
			return ResponseEntity.ok(new MessageResponse(true, 1, users, "Usuarios verificados"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los usuarios verificados"));
		}
	}
	
	@GetMapping("/lote/{id}/unsuscribe")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> noSuscritosLote(@PathVariable int id) {
		try {
			List<Usuario> users = usuarioService.usuariosPermisoInscripcion(id);
			return ResponseEntity.ok(new MessageResponse(true, 1, users, "Usuarios no suscritos para el lote "+id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los usuarios no suscritos"));
		}
	}
	
	@GetMapping("/{id}/unsublote/{idlote}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> unsub(@PathVariable("idlote") Integer idLote,@PathVariable("id") Integer id){
		try {
			LoteParticipantes sub = loteService.permisoLotePorUsuario(id, idLote).get(0);
			loteParticipantesService.delete(sub);
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Inscripcion eliminada"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al realizar la desinscripcion"));
		}
	}
	
	@GetMapping("/noverificados")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> usuariosNoVerificados() {
		try {
			List<Usuario> users = usuarioService.usuariosNoVerificados();
			return ResponseEntity.ok(new MessageResponse(true, 1, users, "Usuarios no verificados"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al obtener los usuarios no verificados"));
		}
	}
	
	@GetMapping("/verificar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> verificar(@PathVariable int id) {
		try {
			Usuario encontrado = usuarioService.findById(id).get();
			//EstadoUsuario estado = estadoUsuarioService.findById(2).get();
			//encontrado.setEstadoUsuario(estado);
			/**/
			MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helperMessage =  new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
	        String confirmationToken =  UUID.randomUUID().toString();
	        Template t = freemakerConfig.getTemplate("pass.flth");
	        Map<String, Object> model = new HashMap<>();
	        model.put("codigo", "http://localhost/page/pass.html?t="+confirmationToken);
	        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
	        
	        helperMessage.setFrom("noreply.subasta@gmail.com");
	        helperMessage.setTo(encontrado.getCorreo());
	        encontrado.setToken(confirmationToken);
	        helperMessage.setSubject("Establecer contraseña");
	        helperMessage.setText(html,true);
			/**/
			usuarioService.save(encontrado);
			emailSender.send(message);
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Usuarios verificado"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se pudo verificar el usuario"));
		}
	}
	
	@GetMapping("/{id}/inslote/{idlote}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> inscripcionPorUser(@PathVariable("id") int id, @PathVariable("idlote") int idlote) {
		try {
			UsuarioSuscripcion ins = usuarioSuscripcionService.suscripcionPorUsuarioAndLote(id, idlote);
			List<Object> lista = new ArrayList<>();
			if (ins == null) {
				lista.add(false);
				return ResponseEntity.ok(new MessageResponse(true, 1, lista, "El usuario no tiene una inscripcion activa previa"));
			}else {
				lista.add(ins);
				return ResponseEntity.ok(new MessageResponse(true, 1, lista, "El usuario tiene una inscripcion activa previa"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(false, 7, new ArrayList<>(), "Error al obtener la inscripcion"));
		}
	}
	

}
