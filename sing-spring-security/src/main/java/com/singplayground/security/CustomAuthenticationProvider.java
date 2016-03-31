package com.singplayground.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import com.singplayground.security.model.CustomUser;
import com.singplayground.security.model.Role;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		CustomUser user = new CustomUser();
		user.setUsername(name);
		user.setPassword(password);

		user.setEmail("testing@abc.com");
		System.out.println("username : " + name);
		System.out.println("password : " + password);
		//user.setAuthorities(New GrantedAuthorityImpl("ROLE_FOO"));
		//getAuthorities().add(New GrantedAuthorityImpl("ROLE_FOO")
		List<Role> temp = new ArrayList();

		Role role = new Role();
		role.setName("temp");
		temp.add(role);
		user.setAuthorities(temp);

		// if the username and password is auth, then the result will auth ****
		boolean auth = true;
		if (auth) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			// add the role to the 
			grantedAuths.add(new GrantedAuthorityImpl("ROLE_FOO"));
			//grantedAuths.add(user.getAuthorities());
			//return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
			return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
		} else {
			return null;
		}

		// use the credentials to try to authenticate against the third party system
		/*
		boolean auth = true;
		if (auth) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		} else {
			System.out.println("=========");
			throw new AuthenticationException("Unable to auth against third party systems");

		}
		*/
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
