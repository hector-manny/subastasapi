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
@Table(name = "TIPO_LOTE", catalog = "", schema = "public")
@XmlRootElement
public class TipoLote implements Serializable{
	
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_LOTE", nullable = false, precision = 0, scale = -127)
    private Integer idTipoLote;
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
    @OneToMany(mappedBy = "tipoLote")
    private List<Lote> loteList;
    
    public TipoLote() {
    }

    public TipoLote(Integer idTipoLote) {
        this.idTipoLote = idTipoLote;
    }

    public TipoLote(Integer idTipoLote, String nombre, String descripcion, Boolean activo) {
        this.idTipoLote = idTipoLote;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = (short)(activo?1:0);
    }

	public Integer getIdTipoLote() {
		return idTipoLote;
	}

	public void setIdTipoLote(Integer idTipoLote) {
		this.idTipoLote = idTipoLote;
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

	public List<Lote> getLoteList() {
		return loteList;
	}

	public void setLoteList(List<Lote> loteList) {
		this.loteList = loteList;
	}

    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.TipoLote[ idTipoLote=" + idTipoLote + " ]";
    }
    
    
}
