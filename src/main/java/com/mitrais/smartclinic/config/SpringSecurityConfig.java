package com.mitrais.smartclinic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AccessDeniedHandler accessDeniedHandler;

    private final DataSource dataSource;

    public SpringSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, AccessDeniedHandler accessDeniedHandler, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("SELECT email, password, active FROM clinic_user WHERE email = ?")
                /**.authoritiesByUsernameQuery("SELECT u.email, r.role from clinic_user u " +
                        "INNER JOIN clinic_user_roles ur " +
                        "ON(u.id=ur.user_id) " +
                        "INNER JOIN role r " +
                        "ON(ur.role_id=r.id) " +
                        "WHERE u.email=?")
                **/
                .authoritiesByUsernameQuery("SELECT u.email, u.role from clinic_user u " +
                        "WHERE u.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

        auth.
                inMemoryAuthentication()
                .withUser("user").password(bCryptPasswordEncoder.encode("user")).roles("USER")
                .and()
                .withUser("admin").password(bCryptPasswordEncoder.encode("admin")).roles("USER", "ADMIN");
    }

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/assets/**","/images/**","/js/**","/vendors/**","/index").permitAll()
                .antMatchers("/users/**", "/patients/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
