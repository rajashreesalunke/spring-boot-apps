package com.telusko.spring_sec_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public UserDetailsService userDetailsService;
    @Bean

    public AuthenticationProvider authProvider(){


        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12 ));

        return provider;
    }









    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      //1.we are disabling csrf
       http.csrf(customizer-> customizer.disable() );
       //2.we are enabling security for requests...i.e authenticate
       http.authorizeHttpRequests(request->request.anyRequest().authenticated());
        //3.Form-based login — like a normal login page with username/password form.
        //http.formLogin(Customizer.withDefaults());
        //4.Basic Authentication — where a popup asks you for username and password.





//        http.formLogin(form -> form
//                .loginPage("/login")  // custom login page URL
//                .loginProcessingUrl("/login")  // URL to submit the login form
//                .defaultSuccessUrl("/dashboard", true)  // Redirect on success
//                .failureUrl("/login?error=true")  // Redirect on failure
//                .permitAll()
//        );.............template sathi
        http.httpBasic(Customizer.withDefaults());
        // making is stateless-- we dont need form login when stateless
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return  http.build();
}
//@Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user= User
//                .withDefaultPasswordEncoder()
//                .username("rajashree")
//                .password("raj123")
//                .roles("USER")
//                .build();
//    UserDetails admin= User
//            .withDefaultPasswordEncoder()
//            .username("admin")
//            .password("admin789")
//            .roles("ADMIN")
//            .build();
//
//return new InMemoryUserDetailsManager(user,admin);
//}

}


