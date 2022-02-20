package latmobile.server.subasta.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SUBASTA", catalog = "", schema = "public")
@XmlRootElement
public class Subasta implements Serializable{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SUBASTA", nullable = false, precision = 0, scale = -127)
    private Integer idSubasta;
    @Column(name = "NOMBRE", length = 150)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "HORA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    @Column(name = "HORA_DEFECTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaDefecto;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "FECHA_INICIO_SUSCRIPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioSuscripcion;
    @Column(name = "FECHA_FIN_SUSCRIPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinSuscripcion;
    @Column(name = "HORA_INICIO_SUSCRIPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicioSuscripcion;
    @Column(name = "HORA_FIN_SUSCRIPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinSuscripcion;
    @Basic(optional = false)
    @Column(name = "ACTIVO", nullable = false)
    private short activo;
    @Basic(optional = false)
    @Column(name = "DELETE", nullable = false)
    private short delete;
    @JsonIgnore
    @OneToMany(mappedBy = "subasta")
    private List<Lote> loteList;


    public Subasta() {
    }

    public Subasta(Integer idSubasta) {
        this.idSubasta = idSubasta;
    }

	public Integer getIdSubasta() {
		return idSubasta;
	}

	public void setIdSubasta(Integer idSubasta) {
		this.idSubasta = idSubasta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Date getFechaInicioSuscripcion() {
		return fechaInicioSuscripcion;
	}

	public void setFechaInicioSuscripcion(Date fechaInicioSuscripcion) {
		this.fechaInicioSuscripcion = fechaInicioSuscripcion;
	}

	public Date getFechaFinSuscripcion() {
		return fechaFinSuscripcion;
	}

	public void setFechaFinSuscripcion(Date fechaFinSuscripcion) {
		this.fechaFinSuscripcion = fechaFinSuscripcion;
	}

	public Date getHoraDefecto() {
		return horaDefecto;
	}

	public void setHoraDefecto(Date horaDefecto) {
		this.horaDefecto = horaDefecto;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getHoraInicioSuscripcion() {
		return horaInicioSuscripcion;
	}

	public void setHoraInicioSuscripcion(Date horaInicioSuscripcion) {
		this.horaInicioSuscripcion = horaInicioSuscripcion;
	}

	public Date getHoraFinSuscripcion() {
		return horaFinSuscripcion;
	}

	public void setHoraFinSuscripcion(Date horaFinSuscripcion) {
		this.horaFinSuscripcion = horaFinSuscripcion;
	}

	public List<Lote> getLoteList() {
		return loteList;
	}

	public void setLoteList(List<Lote> loteList) {
		this.loteList = loteList;
	}
	
	public Boolean getActivo() {
		return this.activo!=0;
	}

	public void setActivo(Boolean activo) {
		this.activo = (short)(activo?1:0);
	}

    public Boolean getDelete() {
    	return this.delete!=0;
	}

	public void setDelete(Boolean delete) {
		this.delete = (short)(delete?1:0);
	}

	@Override
    public String toString() {
        return "latmobile.server.subasta.entity.Subasta[ idSubasta=" + idSubasta + " ]";
    }

}
