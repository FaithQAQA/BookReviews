package ca.sheridancollege.jalani;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.jalani.security.*;

public class SecurityWebApplicationInitial extends AbstractSecurityWebApplicationInitializer
{
	public SecurityWebApplicationInitial()
	{
		super(SecurityConfig.class);
	}
}
