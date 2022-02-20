package latmobile.server.subasta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import latmobile.server.subasta.controller.UsuarioService;
import latmobile.server.subasta.entity.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Usuario user = usuarioService.findByCorreo(username)
			        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + username));
		 return UserDetailsImpl.build(user);
	}

}
