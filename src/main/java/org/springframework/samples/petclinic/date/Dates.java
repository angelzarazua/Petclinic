/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.springframework.samples.petclinic.date;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Zarazua
 */
@XmlRootElement
public class Dates {
    
    private List<Date> dates;

    @XmlElement
    public List<Date> getDateList() {
        if (dates == null) {
            dates = new ArrayList<>();
        }
        return dates;
    }

}
