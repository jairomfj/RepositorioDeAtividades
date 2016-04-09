package br.com.repositoriodeatividades.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private CsrfTokenRepository csrfTokenRepository()
//    {
//        HttpSessionCsrfTokenRepository repositories = new HttpSessionCsrfTokenRepository();
//        repositories.setSessionAttributeName("_csrf");
//        return repositories;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
            .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().permitAll();

        http
            .formLogin()
            .loginPage("/login")
            .permitAll();

        http
            .logout()
            .permitAll()
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("pass").roles("USER");
    }
}