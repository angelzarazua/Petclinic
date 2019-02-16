
package org.springframework.samples.petclinic.users;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends Repository<Users, Integer> {
    
    @Transactional(readOnly = true)   
    Collection<Users> findAll() throws DataAccessException;
    
    
    @Query("SELECT users FROM Users users WHERE users.id =:id")
    @Transactional(readOnly = true)
    Users findById(@Param("id") Integer id);
            
    void save(Users user);
    
    void delete(Users user);
}
