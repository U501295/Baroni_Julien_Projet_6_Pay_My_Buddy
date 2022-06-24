package com.projet_6.pay_my_buddy.JB.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/PayMyBuddy/registration"
                ).permitAll()
                //.antMatchers("/admin").hasRole("ADMIN")
                //.antMatchers("/templates/PayMyBuddy").hasRole("USER");
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/PayMyBuddy/HOME");
        /*http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/visitor/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/home")
                .failureUrl("/loginerror")
                .and()
                .logout(logout -> logout
                        .logoutSuccessUrl("/loginlogout"));*/

        http
                .csrf().disable();
        /*http
                .headers()
                .frameOptions()
                .sameOrigin();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                //TODO : voir ici le problème de l'admin qui peut pas se connecter en tant qu'admin
                .usersByUsernameQuery(
                        "select email,pass_word, enabled from users where email=?")
                .authoritiesByUsernameQuery(
                        "select email,authority from authorities JOIN users ON users.authority_id = authorities.authority_id where email=?");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

}
