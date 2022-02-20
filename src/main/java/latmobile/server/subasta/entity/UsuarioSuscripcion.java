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
@Table(name = "USUARIO_SUSCRIPCION", catalog = "", schema = "public")
@XmlRootElement
public class UsuarioSuscripcion implements Serializable{
	
	private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SUSCRIPCION", nullable = false, precision = 0, scale = -127)
    private Integer idUsuarioSuscripcion;
    @Column(name = "ID_LOTE")
    private Integer idLote;
    @Column(name = "MONTO_MAXIMO_PUJA", precision = 8, scale = 2)
    private BigDecimal montoMaximoPuja;
    @Column(name = "RESERVA", precision = 8, scale = 2)
    private BigDecimal reserva;
    @Column(name = "PORCENTAJE_RESERVA", precision = 8, scale = 2)
    private BigDecimal porcentajeReserva;
    @Column(name = "FECHA_ADJUDICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdjudicacion;
    @Column(name = "IDENTIFICACION_TANSACCION", length = 300)
    private String identificacionTansaccion;
    @Column(name = "COMPROBANTE", length = 300)
    private String comprobante;
    @Column(name = "TRANSACCION", length = 300)
    private String transaccion;
    @Column(name = "BANCO", length = 200)
    private String banco;
    @JoinColumn(name = "ID_ESTADO_SUB", referencedColumnName = "ID_ESTADO_SUB")
    @ManyToOne
    private EstadoSub estadoSub;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuario;
    @Basic(optional = false)
    @Column(name = "VALID", nullable = false)
    private short valid;
    
    public UsuarioSuscripcion() {
    }

	public Integer getIdUsuarioSuscripcion() {
		return idUsuarioSuscripcion;
	}

	public void setIdUsuarioSuscripcion(Integer idUsuarioSuscripcion) {
		this.idUsuarioSuscripcion = idUsuarioSuscripcion;
	}

	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public BigDecimal getMontoMaximoPuja() {
		return montoMaximoPuja;
	}

	public void setMontoMaximoPuja(BigDecimal montoMaximoPuja) {
		this.montoMaximoPuja = montoMaximoPuja;
	}

	public BigDecimal getReserva() {
		return reserva;
	}

	public void setReserva(BigDecimal reserva) {
		this.reserva = reserva;
	}

	public BigDecimal getPorcentajeReserva() {
		return porcentajeReserva;
	}

	public void setPorcentajeReserva(BigDecimal porcentajeReserva) {
		this.porcentajeReserva = porcentajeReserva;
	}

	public Date getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	public void setFechaAdjudicacion(Date fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	public String getIdentificacionTansaccion() {
		return identificacionTansaccion;
	}

	public void setIdentificacionTansaccion(String identificacionTansaccion) {
		this.identificacionTansaccion = identificacionTansaccion;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public EstadoSub getEstadoSub() {
		return estadoSub;
	}

	public void setEstadoSub(EstadoSub estadoSub) {
		this.estadoSub = estadoSub;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	
	public Boolean getValid() {
		return this.valid!=0;
	}

	public void setValid(Boolean valid) {
		this.valid = (short)(valid?1:0);
	}

	@Override
    public String toString() {
        return "latmobile.server.subasta.entity.UsuarioSuscripcion[ idUsuarioSuscripcion=" + idUsuarioSuscripcion + " ]";
    }

}
