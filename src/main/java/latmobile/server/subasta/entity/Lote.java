/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latmobile.server.subasta.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LOTE", catalog = "", schema = "public")
@XmlRootElement
public class Lote implements Serializable {

	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_LOTE", nullable = false, precision = 0, scale = -127)
	private Integer idLote;
	@Column(name = "PRECIO", precision = 8, scale = 2)
	private BigDecimal precio;
	@Column(name = "DESCRIPCION", length = 500)
	private String descripcion;
	@Column(name = "NOMBRE", length = 150)
	private String nombre;
	@Column(name = "IMPUESTOS", length = 50)
	private String impuestos;
	@Column(name = "PESO", length = 50)
	private String peso;
	@Column(name = "MEDIDAS", length = 100)
	private String medidas;
	// Modificado por RGuevara
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "HORA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;
	// Modificado por RGuevara
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FECHA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	// Modificado por RGuevara
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "HORA_FIN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFin;
	// Modificado por RGuevara
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FECHA_FIN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	@Basic(optional = false)
	@Column(name = "DELETE", precision = 8, scale = 2)
	private short delete;
	@Column(name = "PORCENTAJE_RESERVA", precision = 8, scale = 2)
	private BigDecimal porcentajeReserva;
	@JoinColumn(name = "ID_SUBASTA", referencedColumnName = "ID_SUBASTA")
	@ManyToOne
	private Subasta subasta;
	@JoinColumn(name = "ID_ESTADO_LOTE", referencedColumnName = "ID_ESTADO_LOTE")
	@ManyToOne
	private EstadoLote estadoLote;
	@JoinColumn(name = "ID_TIPO_LOTE", referencedColumnName = "ID_TIPO_LOTE")
	@ManyToOne
	private TipoLote tipoLote;
	@OneToMany(mappedBy = "lote")
	private List<Image> imagenes;
	@JsonIgnore
	@OneToMany(mappedBy = "lote")
	private List<LoteParticipantes> participantes;
	@Transient
	private List<String> images;

	public Lote() {
	}

	public Lote(Integer idLote) {
		this.idLote = idLote;
	}

	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	public Subasta getSubasta() {
		return subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}

	public EstadoLote getEstadoLote() {
		return estadoLote;
	}

	public void setEstadoLote(EstadoLote estadoLote) {
		this.estadoLote = estadoLote;
	}

	public TipoLote getTipoLote() {
		return tipoLote;
	}

	public void setTipoLote(TipoLote tipoLote) {
		this.tipoLote = tipoLote;
	}

	public List<Image> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Image> imagenes) {
		this.imagenes = imagenes;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getMedidas() {
		return medidas;
	}

	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Boolean getDelete() {
		return this.delete != 0;
	}

	public void setDelete(Boolean delete) {
		this.delete = (short) (delete ? 1 : 0);
	}

	public BigDecimal getPorcentajeReserva() {
		return porcentajeReserva;
	}

	public void setPorcentajeReserva(BigDecimal porcentajeReserva) {
		this.porcentajeReserva = porcentajeReserva;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	/*
	 * public List<LoteParticipantes> getParticipantes() { return participantes; }
	 * 
	 * public void setParticipantes(List<LoteParticipantes> participantes) {
	 * this.participantes = participantes; }
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idLote != null ? idLote.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Lote)) {
			return false;
		}
		Lote other = (Lote) object;
		if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "latmobile.server.subasta.entity.Lote[ idLote=" + idLote + " ]";
	}

}
