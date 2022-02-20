package latmobile.server.subasta.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PARAMETRO", catalog = "", schema = "public")
@XmlRootElement
public class Parametro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_PARAMETRO", nullable = false, precision = 0, scale = -127)
    private Integer idEstadoParametro;
    @Basic(optional = false)
    @Column(name = "NOMBRE", nullable = false, length = 55)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "VALOR", nullable = false, length = 500)
    private String valor;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "ACTIVO", nullable = false)
    private short activo;
    
    public Parametro() {
    }

    public Parametro(Integer idEstadoParametro) {
        this.idEstadoParametro = idEstadoParametro;
    }

    public Parametro(Integer idEstadoParametro, String nombre, String valor, String descripcion, Boolean activo) {
        this.idEstadoParametro = idEstadoParametro;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
        this.activo = (short)(activo?1:0);
    }

	public Integer getIdEstadoParametro() {
		return idEstadoParametro;
	}

	public void setIdEstadoParametro(Integer idEstadoParametro) {
		this.idEstadoParametro = idEstadoParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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
    
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.Parametro[ idEstadoParametro=" + idEstadoParametro + " ]";
    }
    

}
