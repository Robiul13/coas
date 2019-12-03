package com.asl.asl_rms.configuration;

import com.asl.asl_rms.service.UserService;
import org.casbin.adapter.JDBCAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.asl.asl_rms")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    private final UserService userDetailsService;

    private final Environment environment;

    @Autowired
    public SecurityConfiguration(UserService userService, Environment environment){
        this.userDetailsService = userService;
        this.environment = environment;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

		/*
		 * http.authorizeRequests() .antMatchers("/", "/home").permitAll()
		 * .and().formLogin().loginPage("/login").defaultSuccessUrl(
		 * "/adminpanel/dashboard") .and().csrf()
		 * .and().exceptionHandling().accessDeniedPage("/Access_Denied");
		 */
        
        http.authorizeRequests()
        .antMatchers("/adminpanel/**").authenticated()
        .antMatchers("/dncc21/**", "/api/v1/**").permitAll()
        //.antMatchers("/", "/dncc21/**", "/home").permitAll().anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/adminpanel/dashboard")
       // .and().csrf().ignoringAntMatchers("/api/v1/*")
        .and().csrf().ignoringAntMatchers("/nocsrf","/api/v1/**")
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
        
    }
    


    @Bean
    public Enforcer enforcer() {
        JDBCAdapter adapter = new JDBCAdapter(environment.getRequiredProperty("spring.datasource.driver-class-name"),
                environment.getRequiredProperty("spring.datasource.url"), environment.getRequiredProperty("spring.datasource.username"),
                environment.getRequiredProperty("spring.datasource.password"), true);

        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource("model.conf").getFile());

        Enforcer enforcer = new Enforcer(file1.getAbsolutePath(), adapter);

        enforcer.loadPolicy();
        // enforcer.addPolicy("asl123", "/superadmin", "GET");
        // enforcer.addPolicy("SUPERADMIN","/superadmin","GET");
        // enforcer.addPolicy("SUPERADMIN","/superadmin","POST");
        // enforcer.addGroupingPolicy("asl123","SUPERADMIN");

        //enforcer.savePolicy();
        return enforcer;
    }
}
