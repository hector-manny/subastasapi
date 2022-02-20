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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LOTE_PARTICIPANTES", catalog = "", schema = "public")
@XmlRootElement
public class LoteParticipantes implements Serializable {
	
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOTE_PARTICIPANTES", nullable = false, precision = 0, scale = -127)
    private Integer idLoteParticipantes;
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "PASO")
    private Integer paso;
    @JsonIgnore
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne
    private Lote lote;
    
    public LoteParticipantes() {
    }

	public Integer getIdLoteParticipantes() {
		return idLoteParticipantes;
	}

	public void setIdLoteParticipantes(Integer idLoteParticipantes) {
		this.idLoteParticipantes = idLoteParticipantes;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getPaso() {
		return paso;
	}

	public void setPaso(Integer paso) {
		this.paso = paso;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

}
