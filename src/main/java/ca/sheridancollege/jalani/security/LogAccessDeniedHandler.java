package ca.sheridancollege.jalani.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException)
			throws IOException, ServletException 
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();	
		if(auth != null)
		{
			System.out.println(auth.getName()+"trying to access protected resorces\n"+request.getRequestURI());
		}
		response.sendRedirect(request.getContextPath() + "/permissionDenied");			
	}

}
