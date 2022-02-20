package latmobile.server.subasta.web;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank
	private String correo;

	@NotBlank
	private String password;

	@NotBlank
	private String codigo;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
