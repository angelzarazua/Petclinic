/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author Daniel Sarmiento
 */
@Controller
public class LoginController {
    
    private final ReporteLoginRepository repositoryReporteLogin;
    
    public LoginController(ReporteLoginRepository repositoryReporteLogin){
        this.repositoryReporteLogin = repositoryReporteLogin;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
    
    @GetMapping("/reportelogins")
    public String showVetList(Map<String, Object> model) {
        ReportesLogin reportesLogin = new ReportesLogin();               
        reportesLogin.getReportesLoginList().addAll(this.repositoryReporteLogin.findAll());
        model.put("reporteslogin", reportesLogin);
        return "reportesLogin";
    }
    
    @GetMapping({ "/reporteslogin.xml" })
    public @ResponseBody ReportesLogin showResourcesReporteLoginList() {
        ReportesLogin reportesLogin = new ReportesLogin();
        reportesLogin.getReportesLoginList().addAll(this.repositoryReporteLogin.findAll());
        return reportesLogin;
    }
    
   
    @Bean 
    public  ApplicationListener < AuthenticationSuccessEvent >  onSuccessListener () {
         return ( AuthenticationSuccessEvent event ) -> {
            ReporteLogin reporteLogin = new ReporteLogin();
            
            reporteLogin.setDate(LocalDate.now());
            reporteLogin.setHora(LocalDateTime.now());
            System.out.println(LocalDateTime.now());
            String userName = event.getAuthentication().getName();
            System.out.println(event.getAuthentication().getDetails());
            System.out.println(event.getAuthentication());
            reporteLogin.setUsername(userName);
            reporteLogin.setDescripcion("Inicio de sesión Exitoso");
            this.repositoryReporteLogin.save(reporteLogin);
            System.out.println ( " Yeah.. " + userName );
        };
    }

    @Bean 
    public  ApplicationListener < AuthenticationFailureBadCredentialsEvent >  onBadCredentialsListener () {
         return ( AuthenticationFailureBadCredentialsEvent event) -> {
            ReporteLogin reporteLogin = new ReporteLogin();
            
            reporteLogin.setDate(LocalDate.now());
            reporteLogin.setHora(LocalDateTime.now());
            System.out.println(LocalDateTime.now());
            Object userName = event.getAuthentication().getPrincipal();
            System.out.println(event.getAuthentication().getDetails());
            System.out.println(event.getAuthentication());
            reporteLogin.setUsername((String) userName);
            reporteLogin.setDescripcion("Inicio de sesión Fallido");
            this.repositoryReporteLogin.save(reporteLogin);
            System.out.println ( " Oh no ... " + userName );
        };
    }
    
    @Bean 
    public  ApplicationListener < AuthenticationFailureDisabledEvent >  onFailureDisabled() {
         return ( AuthenticationFailureDisabledEvent event ) -> {
            ReporteLogin reporteLogin = new ReporteLogin();
            
            reporteLogin.setDate(LocalDate.now());
            reporteLogin.setHora(LocalDateTime.now());
            System.out.println(LocalDateTime.now());
            String userName = event.getAuthentication().getName();
            System.out.println(event.getAuthentication().getDetails());
            System.out.println(event.getAuthentication());
            reporteLogin.setUsername(userName);
            reporteLogin.setDescripcion("Inicio de sesión Fallido");
            this.repositoryReporteLogin.save(reporteLogin);
            System.out.println ( " Disabled " + userName );
        };
    }
    
    
}
