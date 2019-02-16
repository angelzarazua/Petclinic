
package org.springframework.samples.petclinic.users;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsersList{
    private List<Users> users;
    
    @XmlElement
    public List<Users>getUsersList() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }
}