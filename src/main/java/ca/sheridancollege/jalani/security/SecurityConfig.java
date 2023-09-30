package ca.sheridancollege.jalani.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
	@Autowired
	private LogAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
	{
		UserDetails user=User.withUsername("parul@gmail.com")
				.password(passwordEncoder.encode("1234"))
				.roles("USER")
				.build();
		
		UserDetails guest=User.withUsername("guest@guest.com")
				.password(passwordEncoder.encode("1111"))
				.roles("GUEST")
				.build();
		
		return new InMemoryUserDetailsManager(user,guest);
	}
	*/
	/*
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		PasswordEncoder encoder=NoOpPasswordEncoder.getInstance();
		return encoder;
	}
	*/
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		
		//these two lines are testing purpose only
		http.csrf().disable(); 
		http.headers().frameOptions().disable();

		http.authorizeHttpRequests()
		.requestMatchers("/admin/**").hasRole("USER")
		.requestMatchers(HttpMethod.GET,"/register").permitAll()
		.requestMatchers(HttpMethod.POST,"/register").permitAll()  
		.requestMatchers(new AntPathRequestMatcher("/ViewReviewsById/**")).permitAll()
		.requestMatchers("/","/js/**","/css/**","permissionDenied","view-book.html").permitAll()
		.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/admin",true).permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll()
		.and()
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler);		
		
		return http.build();
	}
	
}






























