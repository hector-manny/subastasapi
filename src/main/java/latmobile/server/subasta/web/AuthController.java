package latmobile.server.subasta.web;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import freemarker.template.Configuration;
import freemarker.template.Template;
import latmobile.server.subasta.controller.EstadoUsuarioService;
import latmobile.server.subasta.controller.RolService;
import latmobile.server.subasta.controller.UsuarioService;
import latmobile.server.subasta.entity.EstadoUsuario;
import latmobile.server.subasta.entity.Rol;
import latmobile.server.subasta.entity.Usuario;
import latmobile.server.subasta.security.JwtUtils;
import latmobile.server.subasta.security.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EstadoUsuarioService estadoUsuarioService;

	@Autowired
	private RolService rolService;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
    private JavaMailSender emailSender;

	@Qualifier("getFreeMarkerConfiguration")
	@Autowired
	private Configuration freemakerConfig;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		Usuario encontrado = usuarioService.findByCorreo(loginRequest.getCorreo()).get();
		String jwt = "";
		if (encontrado.getCodigo().equals(loginRequest.getCodigo())) {
			jwt = jwtUtils.generateJwtToken(authentication);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			List<Object> lista = new ArrayList<>();
			lista.add( new JwtResponse(jwt,
					 userDetails.getId(),
					 userDetails.getEmail(),
					 roles));
			return ResponseEntity.ok(new MessageResponse(true, 1,lista, "Inicio de sesion correcto"));
		}else {
			return ResponseEntity.ok().body(new MessageResponse(false,5,new ArrayList<>(),"Error codigo de verificacion"));
		}
	}

	@PostMapping("/signincode")
	public ResponseEntity<?> generarCodigo(@RequestBody Map<String, Object> payload){
		try {
			if((payload.containsKey("correo")) && (payload.get("correo")!=null) && (payload.containsKey("password")) && (payload.get("password")!=null)) {
				Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(""+payload.get("correo"), ""+payload.get("password")));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				Usuario user = usuarioService.findByCorreo(""+payload.get("correo")).get();
				if (user!=null) {
					SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
			        String codigo = "";
			        for (int i = 0; i < 5; i++) {
			        	codigo = codigo + number.nextInt(9);
					}
			        MimeMessage message = emailSender.createMimeMessage();
			        MimeMessageHelper helperMessage =  new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			        Template t = freemakerConfig.getTemplate("email.flth");
			        Map<String, Object> model = new HashMap<>();
			        model.put("codigo", codigo);
			        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			        helperMessage.setFrom("noreply.subasta@gmail.com");
			        helperMessage.setTo(user.getCorreo());

			        helperMessage.setSubject("Codigo de verificacion");
			        helperMessage.setText(html,true);
			        user.setCodigo(codigo);
			        usuarioService.save(user);
			        emailSender.send(message);
			        return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Codigo de verificacion enviado al correo proporcionado"));
				}else {
					return ResponseEntity.ok().body(new MessageResponse(false, 6, new ArrayList<>(), "Usuario no encontrado con el correo enviado"));
				}
				
			}else {
				return ResponseEntity.ok().body(new MessageResponse(false, 2, new ArrayList<>(), "No se enviaron todos los datos necesarios"));
			}
		} catch (Exception e) {
			System.out.println("exepcion: "+e);
			return ResponseEntity.ok().body(new MessageResponse(false, 4, new ArrayList<>(), "El token o las credenciales son incorrectas"));
		}
		//return ResponseEntity.badRequest().body(new MessageResponse(false, 0, new ArrayList<>(), "Error interno del servido, verificar datos proporcionados"));
	}


	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Map<String, Object> signUpRequest){
		try {
			if (usuarioService.existsByCorreo(""+signUpRequest.get("correo"))) {
				return ResponseEntity.ok().body(new MessageResponse(false, 3, new ArrayList<>(), "Correo electronico en uso"));
			}else {
				if ((""+signUpRequest.get("tipo_persona")).equals("natural")) {
					Usuario nuevoRegistro = new Usuario();
					nuevoRegistro.setNombre(""+signUpRequest.get("nombre"));
					nuevoRegistro.setDireccion(""+signUpRequest.get("direccion"));
					nuevoRegistro.setMunicipio(""+signUpRequest.get("municipio"));
					nuevoRegistro.setDepartamento(""+signUpRequest.get("departamento"));
					nuevoRegistro.setDui(""+signUpRequest.get("dui"));
					nuevoRegistro.setNit(""+signUpRequest.get("nit"));
					nuevoRegistro.setTelefono(""+signUpRequest.get("telefono"));
					nuevoRegistro.setCorreo(""+signUpRequest.get("correo"));
					Rol rolUser = rolService.findByNombre("USUARIO").get();
					nuevoRegistro.setRol(rolUser);
					EstadoUsuario est =estadoUsuarioService.findById(1).get();
					/*--*/
					MimeMessage message = emailSender.createMimeMessage();
			        MimeMessageHelper helperMessage =  new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			        String confirmationToken =  UUID.randomUUID().toString();
			        Template t = freemakerConfig.getTemplate("pass.flth");
			        Map<String, Object> model = new HashMap<>();
			        model.put("codigo", "http://3.20.221.116/page/pass.html?t="+confirmationToken);
			        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			        
			        helperMessage.setFrom("noreply.subasta@gmail.com");
			        helperMessage.setTo(nuevoRegistro.getCorreo());
			        nuevoRegistro.setToken(confirmationToken);
			        helperMessage.setSubject("Establecer contraseña");
			        helperMessage.setText(html,true);
			        /*---*/
					nuevoRegistro.setEstadoUsuario(est);
					nuevoRegistro.setPassword("");
					nuevoRegistro.setUsername("nuevo");
					nuevoRegistro.setCodigo("no-cod");
					usuarioService.save(nuevoRegistro);
					emailSender.send(message);
					return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Informacion registrada para su verificacion"));
				}else {
					return ResponseEntity.ok().body(new MessageResponse(false, 7, new ArrayList<>(), "Registro para persona juridica aun no disponible"));
					//return ResponseEntity.badRequest().body(new MessageResponse("Registro para persona juridica aun no disponible"));
				}
			}

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "El token o las credenciales son incorrectas"));
		}
	}
	
	@PostMapping("/newpass")
	public ResponseEntity<?> parametroPorNombre(@RequestBody Map<String, Object> payload) {
		try {
			if ((payload.containsKey("pass")) && (payload.get("pass")!=null) && (payload.containsKey("token")) && (payload.get("token")!=null)) {
				Usuario user = usuarioService.findByToken(""+payload.get("token")).get();
				String passHash = encoder.encode(""+payload.get("pass"));
				user.setPassword(passHash);
				user.setToken("non-token");
				EstadoUsuario verificado = estadoUsuarioService.getById(2);
				user.setEstadoUsuario(verificado);
				usuarioService.save(user);
				return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Contraseña asignada correctamente"));
			}else {
				return ResponseEntity.ok().body(new MessageResponse(false, 2, new ArrayList<>(), "No se enviaron todos los datos necesarios"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok().body(new MessageResponse(false, 7, new ArrayList<>(), "No se encontro el usuario con el token enviado"));
		}
	}

}
