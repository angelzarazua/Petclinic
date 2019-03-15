/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.springframework.samples.petclinic.date;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Zarazua
 */
public interface DateRepository extends Repository<Date, Integer>{
    @Transactional(readOnly = true)   
    Collection<Date> findAll() throws DataAccessException;
    
    @Query("SELECT date FROM Date date WHERE date.id =:id")
    @Transactional(readOnly = true)
    Date findById(@Param("id") Integer id);
    
    @Query("SELECT DISTINCT date FROM Date date WHERE date.date LIKE :date%")
    @Transactional(readOnly = true)
    Collection<Date> findByFecha(@Param("date") LocalDate fecha);
    
    @Query("SELECT specialties FROM Specialty specialties ORDER BY specialties.name")
    @Transactional(readOnly = true)
    List<Specialty> findSpecialityTypes();
    
    @Query("Select date FROM Date date")
    @Transactional (readOnly= true)
    Collection <Date> findAll2();
    
    @Query("SELECT pets FROM Pet pets")
    @Transactional(readOnly=true)
    List<Pet> findSpecialityTypesPets();
    
    void save(Date date);  
    void delete(Date date);

}
