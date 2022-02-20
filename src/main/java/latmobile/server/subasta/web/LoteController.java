package latmobile.server.subasta.web;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import latmobile.server.subasta.controller.EstadoLoteService;
import latmobile.server.subasta.controller.EstadoSubService;
import latmobile.server.subasta.controller.ImageService;
import latmobile.server.subasta.controller.LoteParticipantesService;
import latmobile.server.subasta.controller.LoteService;
import latmobile.server.subasta.controller.SubastaService;
import latmobile.server.subasta.controller.TipoLoteService;
import latmobile.server.subasta.controller.UsuarioService;
import latmobile.server.subasta.controller.UsuarioSuscripcionService;
import latmobile.server.subasta.entity.EstadoLote;
import latmobile.server.subasta.entity.EstadoSub;
import latmobile.server.subasta.entity.Image;
import latmobile.server.subasta.entity.Lote;
import latmobile.server.subasta.entity.LoteParticipantes;
import latmobile.server.subasta.entity.Subasta;
import latmobile.server.subasta.entity.Usuario;
import latmobile.server.subasta.entity.UsuarioSuscripcion;
import latmobile.server.subasta.image.Base64Converter;
import latmobile.server.subasta.image.FilesStorageService;
import latmobile.server.subasta.security.JwtUtils;
import latmobile.server.subasta.utils.GeneradorNombre;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lote")
public class LoteController {

	@Autowired
	private LoteService loteService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EstadoSubService estadoSubService;

	@Autowired
	private UsuarioSuscripcionService usuarioSuscripcionService;

	@Autowired
	private LoteParticipantesService loteParticipantesService;

	@Autowired
	private TipoLoteService tipoLoteService;

	@Autowired
	private SubastaService subastaService;

	@Autowired
	private EstadoLoteService estadoLoteService;

	@Autowired
	private ImageService imageService;

	@Autowired
	FilesStorageService storageService;

	@Autowired
	JwtUtils jwtUtils;

	@Value("${ruta-imagenes}")
	private String path;

	GeneradorNombre gn = new GeneradorNombre();

	@PostMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> postLote(@RequestBody Lote payload, HttpServletRequest request) {
		try {
			/*
			 * Date fechaHoy = new Date(); Calendar calendar = Calendar.getInstance();
			 * calendar.setTime(fechaHoy);
			 */
			// calendar.add(Calendar.HOUR, 48);
			/*
			 * Subasta subastaLote = new Subasta();
			 * subastaLote.setFechaInicio(calendar.getTime());
			 * subastaFacade.save(subastaLote); payload.setIdSubasta(subastaLote);
			 */

			Subasta sub = subastaService.findById(payload.getSubasta().getIdSubasta()).get();
			payload.setHoraInicio(sub.getHoraDefecto());
			payload.setHoraFin(sub.getHoraFin());
			payload.setFechaInicio(sub.getFechaInicio());
			payload.setFechaFin(sub.getFechaFin());
			if (payload.getPorcentajeReserva().compareTo(BigDecimal.ZERO) < 0) {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Porcentaje de reserva no puede ser menor a cero"));
			}
			EstadoLote estado = estadoLoteService.findByNombre("Proximo").get();
			payload.setEstadoLote(estado);

			// for (String img : imagenes) {
			// byte[] imageByte=Base64.decodeBase64(img);

			// FileUtils.writeByteArrayToFile(new File("uploads"), imageByte);
			// String directory=context.getRealPath("/")+"uploads/sample.jpg";
			// new FileOutputStream(directory).write(imageByte);
			// String directory=servletContext.getRealPath("/")+"images/sample.jpg";
			// storageService.save(Base64Converter.converter(img));
			// }
			// System.out.println(payload.getImages().size());

			loteService.save(payload);
			List<Image> guardarImg = new ArrayList<>();
			for (int i = 0; i < payload.getImages().size(); i++) {
				MultipartFile file = Base64Converter.converter(payload.getImages().get(i));
				// String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				// Files.copy(file.getInputStream(),
				// this.root.resolve(UUID.randomUUID().toString().replace("-",
				// "")+"."+extension))
				String name = UUID.randomUUID().toString().replace("-", "");
				storageService.save(file, name);
				Image imagen = new Image();
				imagen.setLote(payload);
				imagen.setOrden(i + 1);
				imagen.setSrc(name + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
				guardarImg.add(imagen);
				imageService.save(imagen);
			}
			payload.setImagenes(guardarImg);
			// loteService.save(payload);
			return ResponseEntity.ok(new MessageResponse(true, 1, payload, "Lote creado con exito"));
		} catch (Exception e) {
			System.out.println("error images: " + e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "error en los datos"));
		}
	}

	@GetMapping("/porsubastar")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> lotesPorSubastar(@RequestHeader(value = "authorization", required=false) String token) {
		try {
			List<Lote> filtrados = loteService.findAll();
			/*
			 * List<Lote> nuevos = new ArrayList<>(); for (Lote lote : filtrados) { if
			 * (condition) {
			 * 
			 * } }
			 */
			// System.out.println("username:
			// "+jwtUtils.getUserNameFromJwtToken(token.replaceAll("Bearer ", "")));
			return ResponseEntity.ok(new MessageResponse(true, 1, filtrados, "Lotes que se van a subastar"));
		} catch (Exception e) {
			//System.out.println("----excepcion" + e);
			return ResponseEntity.ok().body(new MessageResponse(false, 7, new ArrayList<>(), "No se pudieron obtener los lotes que se van a subastar"));
		}
	}

	@GetMapping("")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> todos() {
		try {
			List<Lote> lotes = loteService.findAll().stream().filter((Lote sub) -> {
				return sub.getDelete() == false;
			}).collect(Collectors.toList());
			return ResponseEntity.ok(new MessageResponse(true, 1, lotes, "Lista de todos los lotes"));

		} catch (Exception e) {
			return ResponseEntity.ok().body(new MessageResponse(false, 7, new ArrayList<>(), "No se pudieron obtener los lotes"));
		}
	}

	@GetMapping("/{id}")
	// @PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> lote(@PathVariable int id) {
		try {
			Lote encontrado = loteService.findById(id).get();
			List<Lote> lista = new ArrayList<>();
			lista.add(encontrado);
			return ResponseEntity.ok(new MessageResponse(true, 1, lista, "Lote con el id: " + id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se ha encontrado el lote con el id: " + id));
		}
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> lotePorUsuario(@PathVariable Integer id) {
		try {
			List<Lote> suscritos = loteService.lotesPorUsuario(id);
			return ResponseEntity.ok(new MessageResponse(true, 1, suscritos, "Lotes a los que esta suscrito el usuario"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se encontraron lotes por usuario"));
		}
	}

	@GetMapping("{idlote}/user/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> permisoUser(@PathVariable("idlote") Integer idLote, @PathVariable("id") Integer id) {
		List<Object> lista = new ArrayList<>();
		try {
			List<LoteParticipantes> suscritos = loteService.permisoLotePorUsuario(idLote, id);
			Lote encontrado = loteService.findById(idLote).get();
			Date fechaAhorita = new Date();

			// System.out.println("idlote: "+idLote+" id
			// user:"+id+"f:"+fechaAhorita.toGMTString());
			if ((fechaAhorita.compareTo(encontrado.getSubasta().getFechaInicioSuscripcion()) >= 0
					&& fechaAhorita.compareTo(encontrado.getSubasta().getFechaFinSuscripcion()) <= 0) && suscritos.size() > 0) {
				// System.out.println("Se encuentra entre las fechas");
				lista.add(1);
				// return ResponseEntity.ok(1);
				return ResponseEntity.ok(new MessageResponse(true, 1, lista, "Permiso de participacion en la subasta del lote por id del usuario"));
			} else {
				lista.add(0);
				// return ResponseEntity.ok(0);
				return ResponseEntity.ok(new MessageResponse(false, 1, lista, "No tiene permiso en el lote"));
			}
			// System.out.println("tamanio: "+suscritos.size());
			// return ResponseEntity.ok(suscritos.size());

		} catch (Exception e) {
			// System.out.println("error: "+e);
			return ResponseEntity.ok(new MessageResponse(false, 7, lista, "No se pudo determinar si el lote enviado tiene permiso"));
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@GetMapping("/ver/{idlote}/user/{id}")
	// @PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> permisoVer(@PathVariable("idlote") Integer idLote, @PathVariable("id") Integer id) {
		try {
			List<LoteParticipantes> suscritos = loteService.permisoLotePorUsuario(idLote, id);
			Lote encontrado = loteService.findById(idLote).get();
			Date fechaAhorita = new Date();
			// System.out.println("idlote: "+idLote+" id user:"+id);
			int horaI = encontrado.getHoraInicio().getHours();
			int minutosI = encontrado.getHoraInicio().getMinutes();
			// System.out.println("hora inicio: "+((horaI*100)+minutosI));
			int horaF = encontrado.getHoraFin().getHours();
			int minutosF = encontrado.getHoraFin().getMinutes();
			int horaA = fechaAhorita.getHours();
			int minutosA = fechaAhorita.getMinutes();
			List<Object> lista = new ArrayList<>();
			if ((fechaAhorita.compareTo(encontrado.getFechaInicio()) >= 0 && fechaAhorita.compareTo(encontrado.getFechaFin()) <= 0)
					&& ((horaA * 100) + minutosA) > ((horaI * 100) + minutosI) && ((horaA * 100) + minutosA) < ((horaF * 100) + minutosF)) {
				System.out.println("Se encuentra entre las fechas");
				// return ResponseEntity.ok(1);
				lista.add(1);
				return ResponseEntity.ok(new MessageResponse(true, 1, lista, "Permiso de participacion en la subasta del lote por id del usuario"));
			} else {
				/*
				 * int horaI = encontrado.getHoraInicio().getHours(); int minutosI =
				 * encontrado.getHoraInicio().getMinutes();
				 * System.out.println("hora inicio: "+((horaI*100)+minutosI)); int horaF =
				 * encontrado.getHoraFin().getHours(); int minutosF =
				 * encontrado.getHoraFin().getMinutes(); int horaA = fechaAhorita.getHours();
				 * int minutosA = fechaAhorita.getMinutes();
				 */
				/*
				 * System.out.println("hora fin: "+((horaF*100)+minutosF));
				 * System.out.println("hora actual: "+((horaA*100)+minutosA));
				 */
				if (((horaA * 100) + minutosA) > ((horaI * 100) + minutosI) && ((horaA * 100) + minutosA) < ((horaF * 100) + minutosF)) {
					System.out.println("Dentro del rango de hora");
				} else {
					System.out.println("Fuera del rango de hora");
				}
				// System.out.println("hora fin: "+((horaF*100)+minutosF));
				lista.add(0);
				// return ResponseEntity.ok(0);
				return ResponseEntity.ok(new MessageResponse(true, 1, lista, "No tiene permiso en el lote"));
			}
			// System.out.println("tamanio: "+suscritos.size());
			// return ResponseEntity.ok(suscritos.size());

		} catch (Exception e) {
			System.out.println("error: " + e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, 0, "No tiene permiso en el lote"));
		}
	}

	@PostMapping(path = "/sub", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> suscripcion(@RequestBody Map<String, Object> payload) {
		/*
		 * Creado por RGuevara
		 */
		ArrayList<Map<String, String>> rsp = new ArrayList<Map<String, String>>();
		Map<String, String> rspMsg = new HashMap<>();

		ArrayList<UsuarioSuscripcion> respuesta = new ArrayList<UsuarioSuscripcion>();
		try {
			if ((payload.containsKey("idLote")) & (payload.get("idLote") != null) & (payload.containsKey("idUsuario")) & (payload.get("idUsuario") != null)
					& (payload.containsKey("banco")) & (payload.get("banco") != null) & (payload.containsKey("transaccion"))
					& (payload.get("comprobante")) != null & (payload.get("transaccion") != null)) {
				// Metodo para subir la imagen a directorio
				MultipartFile file = Base64Converter.converter(payload.get("comprobante").toString());
				String nombreImagen = gn.generaNombre(file);
				storageService.save(file, FilenameUtils.getBaseName(nombreImagen));
				UsuarioSuscripcion nuevaSub = new UsuarioSuscripcion();
				BigDecimal valor = new BigDecimal("" + payload.get("reserva"));
				Lote lote = loteService.findById(Integer.parseInt("" + payload.get("idLote"))).get();
				nuevaSub.setIdLote(lote.getIdLote());
				Usuario user = usuarioService.findById(Integer.parseInt("" + payload.get("idUsuario"))).get();
				nuevaSub.setUsuario(user);
				nuevaSub.setReserva(valor);
				nuevaSub.setTransaccion("" + payload.get("transaccion"));
				nuevaSub.setBanco("" + payload.get("banco"));
				// Envio el nombre agregando los datos random
				nuevaSub.setComprobante("" + nombreImagen);
				EstadoSub est = estadoSubService.findByNombre("Reserva").get();
				nuevaSub.setEstadoSub(est);
				// BigDecimal porcentajeReserva = new
				// BigDecimal(parametroService.findByNombre("porcentaje").get().getValor());
				nuevaSub.setPorcentajeReserva(lote.getPorcentajeReserva());
				usuarioSuscripcionService.save(nuevaSub);
				LoteParticipantes participacion = new LoteParticipantes();
				participacion.setIdUsuario(user.getIdUsuario());
				participacion.setLote(lote);
				participacion.setPaso(1);
				loteParticipantesService.save(participacion);
				respuesta.add(nuevaSub);

				rspMsg.put("precioLote", lote.getPrecio().toString());
				rspMsg.put("porcentajeReserva", lote.getPorcentajeReserva().toString());
				rspMsg.put("montoPagar", nuevaSub.getReserva().toString());
				rsp.add(rspMsg);
				return ResponseEntity.ok(new MessageResponse(true, 1, rsp, "se guardo la inscripcion"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Datos incorrectos"));
			}
		} catch (Exception e) {
			System.out.println("excepcion:  " + e);
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Error al guardar la inscripcion"));
		}
	}

	@GetMapping("/tiposdelote")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> tiposdelote() {
		try {
//			return ResponseEntity.ok(tipoLoteService.findAll());
			return ResponseEntity.ok(new MessageResponse(true, 1, tipoLoteService.findAll(), "Tipos de lote"));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new MessageResponse(false, 7, new ArrayList<>(), "No se pudieron obtener los tipos de lote"));
		}
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> editarLote(@RequestBody Lote editar) {
		try {
			if (editar.getPorcentajeReserva().compareTo(BigDecimal.ZERO) < 0) {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Porcentaje de reserva no puede ser menor a cero"));
			}
			loteService.save(editar);
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Lote editado con exito"));
		} catch (Exception e) {
			System.out.println("excepcion editar lote: " + e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 7, new ArrayList<>(), "No se pudo editar el lote"));
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> deleteLote(@PathVariable Integer id) {
		// System.out.println("se elimina subasta: "+payload.getIdSubasta());
		try {
			// payload.setDelete(false);
			// subastaService.save(payload);
			Lote borrar = loteService.findById(id).get();
			// System.out.println("se elimina subasta: "+payload);
			borrar.setDelete(true);
			loteService.save(borrar);
			return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Lote eliminado con exito"));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new MessageResponse(false, 7, new ArrayList<>(), "Ocurrio un error al eliminar el lote"));
		}
	}

	@PostMapping(path = "/postimage", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> postImagenes(@RequestBody Map<String, Object> payload) {
		try {
			if (payload.get("src") != null) {
				Integer idLote = Integer.parseInt("" + payload.get("idLote"));
				Lote lote = loteService.findById(idLote).get();
				Image imagen = new Image();
				imagen.setLote(lote);
				imagen.setSrc("" + payload.get("src"));
				imagen.setOrden(Integer.parseInt("" + payload.get("orden")));
				imageService.save(imagen);
				return ResponseEntity.ok(new MessageResponse(true, 1, new ArrayList<>(), "Imagen editado con exito"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "No se pudo agregar la imagen"));
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "Ocurrio un error al crear la imagen"));
		}
	}

	@PutMapping(path = "/inscripcion", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
	public ResponseEntity<?> postLote(@RequestBody UsuarioSuscripcion payload) {
		// System.out.println("aver");
		try {
			payload.setValid(true);
			usuarioSuscripcionService.save(payload);
			return ResponseEntity.ok(new MessageResponse(true, 1, payload, "Inscipcion validada con exito"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(false, 4, new ArrayList<>(), "error al validar"));
		}
	}

	@RequestMapping(value = "/imagenes", produces = MediaType.IMAGE_JPEG_VALUE)
	@PreAuthorize("hasRole('ADMIN') or hasRole('USUARIO')")
    @ResponseBody
	public byte[] loteImagen(@RequestBody Map<String, String> fileName) throws Exception {
        try (FileInputStream inputStream = new FileInputStream(path+fileName.get("imagen"))) {
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes, 0, inputStream.available());
			return bytes;
		}
	}

}
