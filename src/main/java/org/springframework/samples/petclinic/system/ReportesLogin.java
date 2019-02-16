/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.system;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Sarmiento
 */
@XmlRootElement
public class ReportesLogin {
     private List<ReporteLogin> reportesLogin;
     
    @XmlElement
    public List<ReporteLogin> getReportesLoginList() {
        if (reportesLogin == null) {
            reportesLogin = new ArrayList<>();
        }
        return reportesLogin;
    }

}
