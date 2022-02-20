package latmobile.server.subasta.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USUARIO", catalog = "", schema = "public")
@XmlRootElement
public class Usuario implements Serializable{
	
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO", nullable = false, precision = 0, scale = -127)
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "NOMBRE", nullable = false, length = 55)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DIRECCION", nullable = false, length = 300)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "MUNICIPIO", nullable = false, length = 100)
    private String municipio;
    @Basic(optional = false)
    @Column(name = "DEPARTAMENTO", nullable = false, length = 100)
    private String departamento;
    @Basic(optional = false)
    @Column(name = "DUI", nullable = false, length = 12)
    private String dui;
    @Basic(optional = false)
    @Column(name = "NIT", nullable = false, length = 20)
    private String nit;
    @Basic(optional = false)
    @Column(name = "TELEFONO", nullable = false, length = 10)
    private String telefono;
    @Basic(optional = false)
    @Column(name = "CORREO", nullable = false, length = 55)
    private String correo;
    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 55)
    private String username;
    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "TOKEN", nullable = false, length = 255)
    private String token;
    @JsonIgnore
    @Column(name = "CODIGO", length = 6)
    private String codigo;
    @JoinColumn(name = "ID_ESTADO_USUARIO", referencedColumnName = "ID_ESTADO_USUARIO")
    @ManyToOne
    private EstadoUsuario estadoUsuario;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private Rol rol;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioSuscripcion> usuarioSuscripcionList;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Puja> pujaList;
    
    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombre, String direccion, String municipio, String departamento, String dui, String nit, String telefono, String correo, String username, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
        this.departamento = departamento;
        this.dui = dui;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.username = username;
        this.password = password;
    }

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public EstadoUsuario getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<UsuarioSuscripcion> getUsuarioSuscripcionList() {
		return usuarioSuscripcionList;
	}

	public void setUsuarioSuscripcionList(List<UsuarioSuscripcion> usuarioSuscripcionList) {
		this.usuarioSuscripcionList = usuarioSuscripcionList;
	}

	public List<Puja> getPujaList() {
		return pujaList;
	}

	public void setPujaList(List<Puja> pujaList) {
		this.pujaList = pujaList;
	}
    
    @Override
    public String toString() {
        return "latmobile.server.subasta.entity.Usuario[ idUsuario=" + idUsuario + " ]";
    }

}
