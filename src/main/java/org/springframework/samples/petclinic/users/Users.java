package org.springframework.samples.petclinic.users;

/**
 *
 * @author marqu
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.BaseEntity;




@Entity
@Table(name = "users")
public class Users extends BaseEntity{
    @Column(name = "username")
    @NotEmpty
    private String username;
    
    @Column(name = "password")
    @NotEmpty
    private String password;
    
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    
    @Column(name = "activo")
    @NotEmpty
    private String activo;
    
    @Column(name = "cp")
    @NotEmpty
    private String cp;
    
    @Column(name = "municipio")
    @NotEmpty
    private String municipio;
    
    @Column(name = "rol")
    @NotEmpty
    private String rol;

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    

    
    
    @Override
    public String toString() {
        return new ToStringCreator(this)
                
                .append("id",this.getId()).append("new", this.isNew())
                .append("user_name", this.username)
                .append("password",this.password)
                .append("nombre",this.nombre)
                .append("activo",this.activo)
                .append("cp", this.cp).toString();
    }
}

