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
@Table(name = "ESTADO_USUARIO", catalog = "", schema = "public")
@XmlRootElement
public class EstadoUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_USUARIO", nullable = false, precision = 0, scale = -127)
    private Integer idEstadoUsuario;
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
    @OneToMany(mappedBy = "estadoUsuario")
    private List<Usuario> usuarioList;

    public EstadoUsuario() {
    }

    public EstadoUsuario(Integer idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public EstadoUsuario(Integer idEstadoUsuario, String nombre, String descripcion, Boolean activo) {
        this.idEstadoUsuario = idEstadoUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = (short)(activo?1:0);
    }

	public Integer getIdEstadoUsuario() {
		return idEstadoUsuario;
	}

	public void setIdEstadoUsuario(Integer idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
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

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
    
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.EstadoUsuario[ idEstadoUsuario=" + idEstadoUsuario + " ]";
    }
    
}
