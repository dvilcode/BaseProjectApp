package $packageName.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import $packageName.utils.StringUtil;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String contrasena = (String) authentication.getCredentials();
		String nombreUsuario = (String) authentication.getPrincipal();

		if (StringUtil.isEmpty(nombreUsuario)) {
			throw new BadCredentialsException("Introduce tu nombre de usuario.");
		}

		Collection<? extends GrantedAuthority> grantedAuthorities;
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;/*
																						 * new
																						 * UsernamePasswordAuthenticationToken
																						 * (
																						 * emplid
																						 * ,
																						 * contrasena
																						 * ,
																						 * grantedAuthorities
																						 * )
																						 * ;
																						 * usernamePasswordAuthenticationToken
																						 * .
																						 * setDetails
																						 * (
																						 * usuario
																						 * )
																						 * ;
																						 */
		return usernamePasswordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<? extends Object> objectClass) {
		return objectClass.equals(UsernamePasswordAuthenticationToken.class);
	}

}
