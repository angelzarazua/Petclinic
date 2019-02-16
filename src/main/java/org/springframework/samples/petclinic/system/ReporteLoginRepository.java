/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.system;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel Sarmiento
 */
public interface ReporteLoginRepository extends Repository<ReporteLogin, Integer>{
    
    @Transactional(readOnly = true)   
    Collection<ReporteLogin> findAll() throws DataAccessException;
    
    void save(ReporteLogin reporteLogin);
    
}
