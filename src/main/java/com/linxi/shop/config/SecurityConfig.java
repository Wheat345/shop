package com.linxi.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.linxi.shop.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
//@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
	
	 @Autowired 
	 private UserDetailsService userDetailsService;
	 
	 @Autowired
	 private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	 
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	 } 
	 
//	 @Bean
//	 public BasicAuthenticationEntryPoint entryPoint() {
//	     BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
//	     basicAuthEntryPoint.setRealmName("My Realm");
//	     return basicAuthEntryPoint;
//	 }
	 
//	 @Autowired 
//	 private AuthenticationEntryPoint authEntryPoint;
	 
	 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
			.addFilterBefore(new CORSFilter(), ChannelProcessingFilter .class)
    		//.addFilterBefore(new CORSFilter(), UsernamePasswordAuthenticationFilter.class)
        	.csrf().disable()
//        	.exceptionHandling()
//            	.authenticationEntryPoint(entryPoint())
//            .and()
            .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
            	.antMatchers("/").permitAll()
            	.antMatchers("/.").authenticated()
            	//.antMatchers("/rest/**").permitAll()
				//.antMatchers("/rest/**").access("hasRole('ROLE_ADMIN')")
				//.antMatchers("/rest/**").authenticated()
				.antMatchers("/rest/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                //.usernameParameter("username").passwordParameter("password")
                //.and()
                .permitAll()
                //.defaultSuccessUrl("/rest/listCustomers")
                .successHandler(myAuthenticationSuccessHandler)
                .and()
            .logout()
            	.logoutUrl("/rest/logout");
                //.permitAll();
//				.and().httpBasic()
//				.authenticationEntryPoint(authEntryPoint);;.httpBasic()
//
//        		.and().httpBasic()
//		.realmName("My Realm").authenticationEntryPoint(entryPoint())
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Bean(name="passwordEncoder")
       public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
       }
}