/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.owner;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
/**
 *
 * @author ciaba
 */
public interface AlbumRepository extends Repository<Album, Integer>{
    void save(Album album) throws DataAccessException;

    List<Album> findByPetId(Integer petId);
}
