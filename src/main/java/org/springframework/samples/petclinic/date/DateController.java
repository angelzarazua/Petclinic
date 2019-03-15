/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.springframework.samples.petclinic.date;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 
 * @author Zarazua
 */
@Controller
public class DateController {
    
    private static final String VIEWS_DATE_CREATE_OR_UPDATE_FORM = "dates/createorupdateDateForm";
    private final DateRepository dates;

    public DateController(DateRepository clinicService) {
        this.dates = clinicService;
    }
    
    @GetMapping("/dates.html")
    public String showDateList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        Dates dates = new Dates();                
        dates.getDateList().addAll(this.dates.findAll());
       model.put("dates", dates);
        return "dates/dateList";
    }
    
    
    @GetMapping("/date")
    public String showDate(Date date, BindingResult result, Map<String, Object> model) {

        //Collection<Owner> results = this.owners.findByLastName("");
            //model.put("selections", results);
            return "dates/dateReporte";
        
    }
    
    @GetMapping("/rrr")
    public String showVis(){
        return "/dates/createorupdateDateForm";
    }
    
    @GetMapping("/dateList")
    public String showListDates(Map<String, Object> model){
        Dates dates = new Dates();                
        dates.getDateList().addAll(this.dates.findAll());
        model.put("dates", dates);
        return "/dates/dateList";
    }
    
    @GetMapping("/dates/new")
    public String initCreationForm(Map<String, Object> model) {
        Date date = new Date();        
        model.put("date", date);        
        return VIEWS_DATE_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/dates/new")
    public String processCreationForm(@Valid Date date, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_DATE_CREATE_OR_UPDATE_FORM;
        } else {
            date.setCofirmacion("Sin confirmar");
            this.dates.save(date);
            System.out.println("Datos: " + date.getFecha()+" "+date.getMascota()+" "+date.getConfirmacion());
            return "redirect:/dates.html";
        }
    }
    
    @GetMapping("/dates/edit/{dateId}")
    public String initUpdateVetForm(@PathVariable("dateId") int dateId, Model model) {
        Date date = this.dates.findById(dateId);
        model.addAttribute(date);
        return VIEWS_DATE_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/dates/edit/{dateId}")
    public String processUpdateOwnerForm(@Valid Date date, BindingResult result, @PathVariable("dateId") int dateId) {
        if (result.hasErrors()) {
            return VIEWS_DATE_CREATE_OR_UPDATE_FORM;
        } else {
            date.setId(dateId);
            this.dates.save(date);
            return "redirect:/dateList";
        }
    }
    
    @GetMapping("/editDate")
    public String showEditListDates(Map<String, Object> model){
        Dates dates = new Dates();                
        dates.getDateList().addAll(this.dates.findAll());
        model.put("dates", dates);
        return "/dates/editDate";
    }
    
    @ModelAttribute("specialties")
    public Collection<Specialty> populatSpecialty() {
        return this.dates.findSpecialityTypes();
    }
    
    @ModelAttribute("status")
    public List<String> populatStatus() {
        List<String> estatus = new ArrayList<String>();
        estatus.add("Sin Confirmar");
        estatus.add("Confirmado");
        return estatus;
    }
    
    @ModelAttribute("pets")
    public Collection<Pet> populatPets() {
        return this.dates.findSpecialityTypesPets();
    }
    

}
