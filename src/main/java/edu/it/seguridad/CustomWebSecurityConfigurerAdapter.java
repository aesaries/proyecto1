package edu.it.seguridad;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Slf4j
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	//@Autowired 
	//ApplicationContext appContext;

	@Autowired
	Filtro filtro;

	public CustomWebSecurityConfigurerAdapter() {
        log.info("CustomWebSecurityConfigurerAdapter... LEVANTADO");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("Ingresa a configurar el adapter....");
		
        http
          .authorizeRequests()
          .anyRequest()
          .permitAll()
          .and()
          .addFilterBefore(filtro, BasicAuthenticationFilter.class)
          .csrf().disable();
    }
}
