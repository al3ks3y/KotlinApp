package com.apetrov.petclinic.config

import com.apetrov.petclinic.service.ClientDetailsService
import com.apetrov.petclinic.util.ACCOUNT_API_PREFIX
import com.apetrov.petclinic.util.DOCTOR_API_PREFIX
import com.apetrov.petclinic.util.RECEPTION_API_PREFIX
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint


@Configuration
@EnableGlobalAuthentication
@EnableWebSecurity
class SecurityConfig(val clientDetailsService: ClientDetailsService,
                     val authenticationEntryPoint: AuthenticationEntryPoint) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("**/register").permitAll()
                .antMatchers(ACCOUNT_API_PREFIX).fullyAuthenticated()
                .antMatchers(DOCTOR_API_PREFIX, RECEPTION_API_PREFIX).permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
    }

    @Throws(java.lang.Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**")
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService? {
        return clientDetailsService
    }

    override fun configure(builder: AuthenticationManagerBuilder) {
        builder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }
}
