package com.project.bilbioteka.App.security.config;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/registration/**", "/", "/users","/img/**","/login","/home_after_login").permitAll() //"/registration/**", "/", "/users","/img/**","/login"
                .anyRequest().authenticated().and().formLogin().loginPage("/login").usernameParameter("email")
                .permitAll()
                .defaultSuccessUrl("/home_after_login", true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/auth/**").hasAnyAuthority("USER")
//                .antMatchers("/login**").permitAll()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/register**").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/auth/home", true)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .addLogoutHandler(logoutHandler)
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
//                .permitAll()
//                .and()
//                .csrf().disable();;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/registration/**", "/", "/users","/img/**").authenticated()
//                .anyRequest().permitAll().and()
//                .formLogin()
//                .and()
//                .logout()
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request,
//                                                HttpServletResponse response, Authentication authentication)
//                            throws IOException, ServletException {
//                        AppUser appUser = (AppUser) authentication.getPrincipal();
//                        String username = appUser.getUsername();
//
//                        System.out.println("The user " + username + " has logged out.");
//                        UrlPathHelper helper = new UrlPathHelper();
//                        String context = helper.getContextPath(request);
//
//                        response.sendRedirect(request.getContextPath());
//                    }
//                })
//                .permitAll();
//    }



}
