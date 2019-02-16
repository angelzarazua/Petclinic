/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.users;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author marqu
 */
public class GetWs {
    WebService ws = new WebService();
    String resultado = null;
    public GetWs(){
    }
    public void setWs(String cp){
        resultado = ws.ws(cp);
    }
    public String getWs(){
        return resultado;
    }
    public CodigoPostal cp(){
        Gson gson = new Gson();
        CodigoPostal cp = gson.fromJson(resultado,CodigoPostal.class);
        return null;
    }
    
    public String getMunicipio(){
        Gson gson = new Gson();
        CodigoPostal cp = gson.fromJson(resultado,CodigoPostal.class);
        return cp.getMunicipio();
    }
}
