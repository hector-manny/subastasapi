package latmobile.server.subasta.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ROL_PERMISO", catalog = "", schema = "public")
@XmlRootElement
public class RolPermiso implements Serializable{
	
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROL_PERMISO", nullable = false, precision = 0, scale = -127)
    private Integer idRolPermiso;
    @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID_PERMISO")
    @ManyToOne
    private Permiso permiso;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private Rol rol;

    public RolPermiso() {
    }

    public RolPermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

	public Integer getIdRolPermiso() {
		return idRolPermiso;
	}

	public void setIdRolPermiso(Integer idRolPermiso) {
		this.idRolPermiso = idRolPermiso;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.RolPermiso[ idRolPermiso=" + idRolPermiso + " ]";
    }
    
}
