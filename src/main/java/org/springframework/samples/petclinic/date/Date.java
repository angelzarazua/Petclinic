/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.springframework.samples.petclinic.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.vet.Specialty;

/**
 * 
 * @author Zarazua
 */

@Entity
@Table(name = "dates")
public class Date extends BaseEntity{
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "hora")
    String hora;
    
    @Column(name = "mascota")
    private String mascota;
    
    @Column(name = "confirmacion")
    private String confirmacion;
    
    
    public void setFecha(String date){
        this.date=date;
    }
    public String getFecha(){
        return this.date;
    }
    
    public void setHora(String hora){
        this.hora=hora;
    }
    public String getHora(){
        return this.hora;
    }
    
    public void setMascota(String mascota){
        this.mascota=mascota;
    }
    public String getMascota(){
        return this.mascota;
    }
    
    public void setCofirmacion(String confirmacion){
        this.confirmacion=confirmacion;
    }
    public String getConfirmacion(){
        return this.confirmacion;
    }
    
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "date_specialties", joinColumns = @JoinColumn(name = "date_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    protected void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecs,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }
    
    public void setSpecialties(List<Specialty> specialties){
        addSpecialty(specialties.get(0));
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

}
