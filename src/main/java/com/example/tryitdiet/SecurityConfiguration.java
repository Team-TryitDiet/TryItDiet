package com.example.tryitdiet;

import com.example.tryitdiet.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // really fancy way of saying now we have a supercharged UserRepository, with way more functionality than a plain UserRepository
    public UserDetailsLoader usersLoader;

    // Set constructor for this Configuration
    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    // This is like a small class, but so small that is doesn't warrant its own file, so we can just annotate it as a @Bean
    // here, for access elsewhere in our code
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This is the override that just sets up the Authentication process for our app
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()); // How to encode and decode/verify passwords
    }

    // Override what happens when specific HTTP pages are loaded / requests are made / etc
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /** Login Configuration **/
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile") // when they successfully log in, redirect to /posts
                .permitAll()
                /** Logout Configuration **/
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // we set a parameter 'logout', so we can display a message to the user
                /** Pages that can be viewed by anyone **/
                .and()
                .authorizeRequests()

                .antMatchers("/", "/posts") // Another Neat Tool matcher - if someone hits these Urls in their browser they are allowed to view

                .permitAll() // like a catch-all
                /** Pages that DO require authentication **/
                .and()
                .authorizeRequests()
                .antMatchers("/posts/create", "/posts/recipe") // pages that we DO want users to be logged in to view/access
                .authenticated() // for the previously mentioned Another Neat Tool Matched URL patterns, users should be authenticated to access them
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }
}
