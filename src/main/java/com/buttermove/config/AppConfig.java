package com.buttermove.config;

import com.buttermove.Calculation.CalculationAmount;
import com.buttermove.factory.StatesFactory;
import com.buttermove.filter.AppFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration//Clase que realiza la configuracion para el sitio
public class AppConfig{
    @Bean
    public StatesFactory statesFactory(){
        StatesFactory states=new StatesFactory();
        states.createSate("NY","New York",.25,.35);
        states.createSate("CA","California",.23,.33);
        states.createSate("AZ","Arizona",.20,.30);
        states.createSate("TX","Texas",.18,.28);
        states.createSate("OH","Ohio",.15,.25);
        return states;
    }
    @Bean // bead usada para que permita saltar login estandar de spring boot
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean//Se prepara bean para que exista en el contexto
    public CalculationAmount calculationAmount(){
        return new CalculationAmount();
    }
    @Bean //Se prepára configuracion para el sitio
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().permitAll();//Permite todos los accesos
        //Aqui se manda llamar un filter que es el que se usa para validar el dato ip-cliente del header
        http.addFilterBefore(appFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))),UsernamePasswordAuthenticationFilter.class);
        return http.getOrBuild();
    }
    protected AppFilter appFilter(AuthenticationManager authenticationManager) throws Exception{
        final AppFilter appFilter=new AppFilter(authenticationManager);
        return appFilter;
    }
}
