package org.dashboard.main;

import org.dashboard.main.security.GoogleAuthenticationFilter;
import org.dashboard.main.security.GoogleAuthenticationProvider;
import org.dashboard.main.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(googleAuthProvider());
        auth.authenticationProvider(daoAuthProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //filters
        http.addFilterBefore(new GoogleAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        //disable csrf
        http.csrf().disable();

        //security
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/*").permitAll()
                .antMatchers("/list","/add", "/remove").fullyAuthenticated()
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GoogleAuthenticationProvider googleAuthProvider(){
        return new GoogleAuthenticationProvider();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthProvider() {
        DaoAuthenticationProvider authProvider = new MyAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
