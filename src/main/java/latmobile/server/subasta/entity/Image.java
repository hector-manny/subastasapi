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
@Table(name = "IMAGE", catalog = "", schema = "public")
@XmlRootElement
public class Image implements Serializable{
	
	private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_IMAGE", nullable = false, precision = 0, scale = -127)
    private Integer idImage;
    @Basic(optional = false)
    @Column(name = "SRC", nullable = false, length = 200)
    private String src;
    @Column(name = "ORDEN")
    private Integer orden;
    @JsonIgnore
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne
    private Lote lote;

    public Image() {
    }

    public Image(Integer idImage) {
        this.idImage = idImage;
    }

    public Image(Integer idImage, String src) {
        this.idImage = idImage;
        this.src = src;
    }

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
    
    public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	@Override
    public String toString() {
        return "latmobile.server.subasta.entity.Image[ idImage=" + idImage + " ]";
    }


}
