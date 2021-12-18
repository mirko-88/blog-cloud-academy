package com.cloudacademy.blog;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
	
//	@Bean 
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//	    return new BCryptPasswordEncoder(); 
//	}
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return new UserDetails() {
					
					@Override
					public boolean isEnabled() {
						// TODO Auto-generated method stub
						return true;
					}
					
					@Override
					public boolean isCredentialsNonExpired() {
						// TODO Auto-generated method stub
						return true;
					}
					
					@Override
					public boolean isAccountNonLocked() {
						// TODO Auto-generated method stub
						return true;
					}
					
					@Override
					public boolean isAccountNonExpired() {
						// TODO Auto-generated method stub
						return true;
					}
					
					@Override
					public String getUsername() {
						// TODO Auto-generated method stub
						return "user";
					}
					
					@Override
					public String getPassword() {
						// TODO Auto-generated method stub
						return "pw";
					}
					
					@Override
					public Collection<? extends GrantedAuthority> getAuthorities() {
						// TODO Auto-generated method stub
						return null;
					}
				};
			}
		};
	}
	
}
