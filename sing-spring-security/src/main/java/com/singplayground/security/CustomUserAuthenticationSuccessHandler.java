package com.singplayground.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomUserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		//User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username

		System.out.println("CustomUserAuthenticationSuccessHandler ---- " + name);
		//System.out.println("this password :" + auth.getCredentials().toString());
		//LdapUserDetailsImpl authUser = (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//session.setAttribute("username", authUser.getUsername());
		System.out.println("======= this is authentication : ");
		response.setStatus(HttpServletResponse.SC_OK);

		response.sendRedirect("basic/test");

	}

}
