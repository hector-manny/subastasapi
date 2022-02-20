package latmobile.server.subasta.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PUJA", catalog = "", schema = "public")
@XmlRootElement
public class Puja implements Serializable{
	
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PUJA", nullable = false, precision = 0, scale = -127)
    private Integer idPuja;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "MONTO", precision = 8, scale = 2)
    private BigDecimal monto;
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne
    private Lote lote;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuario;
	
    public Puja() {
    }

    public Puja(Integer idPuja) {
        this.idPuja = idPuja;
    }

	public Integer getIdPuja() {
		return idPuja;
	}

	public void setIdPuja(Integer idPuja) {
		this.idPuja = idPuja;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.Puja[ idPuja=" + idPuja + " ]";
    }

}
