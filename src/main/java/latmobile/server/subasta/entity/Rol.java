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
@Table(name = "ROL", catalog = "", schema = "public")
@XmlRootElement
public class Rol implements Serializable{
	
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROL", nullable = false, precision = 0, scale = -127)
    private Integer idRol;
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
    @OneToMany(mappedBy = "rol")
    private List<RolPermiso> rolPermisoList;
    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarioList;
    
    public Rol() {
    }

    public Rol(Integer idRol) {
        this.idRol = idRol;
    }

    public Rol(Integer idRol, String nombre, String descripcion, Boolean activo) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = (short)(activo?1:0);
    }

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
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

	public short getActivo() {
		return activo;
	}

	public void setActivo(short activo) {
		this.activo = activo;
	}

	public List<RolPermiso> getRolPermisoList() {
		return rolPermisoList;
	}

	public void setRolPermisoList(List<RolPermiso> rolPermisoList) {
		this.rolPermisoList = rolPermisoList;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
    
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.Rol[ idRol=" + idRol + " ]";
    }


}
