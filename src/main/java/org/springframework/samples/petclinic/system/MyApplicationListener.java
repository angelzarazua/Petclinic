/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.system;

import java.time.LocalDate;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel Sarmiento
 */
//@Component
public class MyApplicationListener implements ApplicationListener{
    
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        
        ReporteLoginRepository repositorioReporteLogin;
        ReporteLogin reporteLogin = new ReporteLogin();
        reporteLogin.setDate(LocalDate.now());
        
        if (event instanceof AuthenticationFailureBadCredentialsEvent) { 
            
            AuthenticationFailureBadCredentialsEvent ev = (AuthenticationFailureBadCredentialsEvent) event;
            
            System.out.println("Inicio de sesión fallido!!");
            Object userName = ev.getAuthentication().getPrincipal();
            System.out.println(userName);
            reporteLogin.setUsername((String) userName);
            reporteLogin.setDescripcion("Inicio de sesion fallido");

        
        } else if(event instanceof AuthenticationSuccessEvent) {
            
            AuthenticationSuccessEvent ev = (AuthenticationSuccessEvent) event;

            System.out.println("Inicio de sesión exitoso!!");
            Object userName = ev.getAuthentication().getPrincipal();
            System.out.println("Inicio de sesión exitoso!!");
            reporteLogin.setUsername((String) userName);
            reporteLogin.setDescripcion("Inicio de sesion exitos");    
        }
    }
    
}
