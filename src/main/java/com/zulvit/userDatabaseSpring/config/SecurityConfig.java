package com.zulvit.userDatabaseSpring.config;

import com.zulvit.userDatabaseSpring.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/auth/login").permitAll()
                .antMatchers("/api/v1/auth/success").authenticated()
                .antMatchers("/admin/**").hasAnyAuthority(Permission.ADMIN_WRITE_LIST.getPermission(), Permission.ADMIN_WATCH_LIST.getPermission())
                .antMatchers("/seller/**").hasAnyAuthority(Permission.SELLER_ORDER_WRITE.getPermission(), Permission.SELLER_ORDER_READ.getPermission()
//                        , Permission.ADMIN_WATCH_LIST.getPermission()
                , Permission.ADMIN_WATCH_LIST.getPermission()
                        )
                .antMatchers("/storekeeper/**").hasAnyAuthority(Permission.SK_WRITE.getPermission(), Permission.SK_READ.getPermission(), Permission.ADMIN_WRITE_LIST.getPermission())
                .antMatchers(("/list/**")).hasAuthority(Permission.ADMIN_WRITE_LIST.getPermission())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic()
//                .and()
                .formLogin()
                .loginPage("/api/v1/auth/login")
                .permitAll()
                .defaultSuccessUrl("/api/v1/auth/success")
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/api/v1/auth/login")
                ;

                //.antMatchers(HttpMethod.GET,"/api/v1/**").hasAuthority(Permission.ADMIN_MAKE_LIST.getPermission())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean()throws Exception{
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities(Role.ADMIN.getAuthorities())
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .authorities(Role.SELLER.getAuthorities())
//                        .build()
//        );
//    }
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
