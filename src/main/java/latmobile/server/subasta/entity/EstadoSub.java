package latmobile.server.subasta.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ESTADO_SUB", catalog = "", schema = "public")
@XmlRootElement
public class EstadoSub implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_SUB", nullable = false, precision = 0, scale = -127)
    private Integer idEstadoSub;
    @Basic(optional = false)
    @Column(name = "NOMBRE", nullable = false, length = 55)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "ACTIVO", nullable = false)
    private short activo;
    @JsonIgnore
    @OneToMany(mappedBy = "estadoSub")
    private List<UsuarioSuscripcion> usuarioSuscripcionList;
    
    public EstadoSub() {
    }

    public EstadoSub(Integer idEstadoSub) {
        this.idEstadoSub = idEstadoSub;
    }

    public EstadoSub(Integer idEstadoSub, String nombre, String descripcion, Boolean activo) {
        this.idEstadoSub = idEstadoSub;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = (short)(activo?1:0);
    }

	public Integer getIdEstadoSub() {
		return idEstadoSub;
	}

	public void setIdEstadoSub(Integer idEstadoSub) {
		this.idEstadoSub = idEstadoSub;
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

	public Boolean getActivo() {
		return this.activo!=0;
	}

	public void setActivo(Boolean activo) {
		this.activo = (short)(activo?1:0);
	}

	public List<UsuarioSuscripcion> getUsuarioSuscripcionList() {
		return usuarioSuscripcionList;
	}

	public void setUsuarioSuscripcionList(List<UsuarioSuscripcion> usuarioSuscripcionList) {
		this.usuarioSuscripcionList = usuarioSuscripcionList;
	}
    
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.EstadoSub[ idEstadoSub=" + idEstadoSub + " ]";
    }
    
}
