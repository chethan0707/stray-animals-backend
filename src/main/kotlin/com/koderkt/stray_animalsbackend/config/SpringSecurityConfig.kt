package com.koderkt.stray_animalsbackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.security.auth.message.config.AuthConfig

@Configuration
class SpringSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.cors()?.disable()
        http?.csrf()?.disable()
        http?.authorizeRequests()?.anyRequest()?.permitAll()
    }
}