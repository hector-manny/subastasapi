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

@Entity
@Table(name = "PERMISO", catalog = "", schema = "public")
@XmlRootElement
public class Permiso implements Serializable {

	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_PERMISO", nullable = false, precision = 0, scale = -127)
	private Integer idPermiso;
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 55)
	private String nombre;
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 500)
	private String descripcion;
	@Basic(optional = false)
	@Column(name = "ACTIVO", nullable = false)
	private short activo;
	@OneToMany(mappedBy = "permiso")
	private List<RolPermiso> rolPermisoList;

	public Permiso() {
	}

	public Permiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Permiso(Integer idPermiso, String nombre, String descripcion, short activo) {
		this.idPermiso = idPermiso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public Integer getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
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
	
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.Permiso[ idPermiso=" + idPermiso + " ]";
    }

}
