package org.springframework.samples.petclinic.users;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class UsersController {
    private static final String VIEWS_USERS_CREATE_OR_UPDATE_FORM = "users/newUsers";
    private final UsersRepository users;
    
    public UsersController(UsersRepository clinicService){
        this.users = clinicService;
    }
    
    @GetMapping("/users/new")
    public String users(Map<String, Object> model) {
        Users user = new Users();
        model.put("users",user);
        return VIEWS_USERS_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/users/new")
    public String proccessCreationForm(@Valid Users user, BindingResult result){
        GetWs  ws = new GetWs();
        int primerChar;
        ws.setWs(user.getCp());
        ws.cp();
        String entero=ws.getMunicipio();
        System.out.println("Entero: " +  entero);
        String resultado ="";
        char compare = 0;
for (int i = 0; i < entero.length(); i++) {
			switch(entero.charAt(i)){
				case '¡':
					resultado = resultado + "á";
				break;   
				case '©':
					resultado = resultado + "é";
				break;
				case '­':
					resultado = resultado +  "í";
				break;
				case '³':
					resultado += "ó";
				break;	
				case 'Ã':
				break;
				default:
					resultado = resultado + entero.charAt(i);
						
			      }
			
		}
        System.out.println("El char a comparar: " + resultado);
        if(!resultado.equals(user.getMunicipio())){
            result.rejectValue("cp", "novalid");
            return VIEWS_USERS_CREATE_OR_UPDATE_FORM;
        }
        String passwd = "";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        passwd = encoder.encode(user.getPassword());
        user.setPassword(passwd);
        
        if(resultado.equals(user.getMunicipio())){
        passwd = encoder.encode(user.getPassword());
        user.setPassword(passwd);
        user.setRol("ADMIN");
        this.users.save(user);
        return "redirect:/userListView";
        }else{
        result.rejectValue("cp", "novalid");
            return VIEWS_USERS_CREATE_OR_UPDATE_FORM;
        }
    }
    
    @GetMapping("/userListView")
    public String showUserList(Map<String, Object> model){
        UsersList userLis = new UsersList();
        userLis.getUsersList().addAll(this.users.findAll());
        model.put("users", userLis);
        return "users/usersListView";
    } 
    
    @GetMapping("/reportListView")
    public String showReportList(Map<String, Object> model){
        UsersList userLis = new UsersList();
        userLis.getUsersList().addAll(this.users.findAll());
        model.put("users", userLis);
        return "users/reportListView";
    } 

    @GetMapping("/users/edit/{usersId}")
    public String initUpdateUsersForm(@PathVariable("usersId") int usersId, Model model) {
        Users user = this.users.findById(usersId);
        model.addAttribute(user);
        return VIEWS_USERS_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/users/edit/{usersId}")
    public String processUpdateUsersForm(@Valid Users user, BindingResult result, @PathVariable("usersId") int usersId) {
        GetWs  ws = new GetWs();
        int primerChar;
        ws.setWs(user.getCp());
        ws.cp();
        String entero=ws.getMunicipio();
        System.out.println("Entero: " +  entero);
        String resultado ="";
        char compare = 0;
for (int i = 0; i < entero.length(); i++) {
			switch(entero.charAt(i)){
				case '¡':
					resultado = resultado + "á";
				break;   
				case '©':
					resultado = resultado + "é";
				break;
				case '­':
					resultado = resultado +  "í";
				break;
				case '³':
					resultado += "ó";
				break;	
				case 'Ã':
				break;
				default:
					resultado = resultado + entero.charAt(i);
						
			      }
			
		}
        System.out.println("El char a comparar: " + resultado);
        if(!resultado.equals(user.getMunicipio())){
            result.rejectValue("cp", "novalid");
            return VIEWS_USERS_CREATE_OR_UPDATE_FORM;
        }
        String passwd = "";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //passwd = encoder.encode(user.getPassword());
        //user.setPassword(passwd);
        
        if(resultado.equals(user.getMunicipio())){
        passwd = encoder.encode(user.getPassword());
        user.setPassword(passwd); 
        user.setId(usersId);
        user.setRol("ADMIN");
        this.users.save(user);
        return "redirect:/userListView";
        }else{
        result.rejectValue("cp", "novalid");
            return VIEWS_USERS_CREATE_OR_UPDATE_FORM;
        }
    }
    
    @GetMapping("/users/delete/{usersId}")
    public String deleteUser(Users user, BindingResult result, Map<String, Object> model, @PathVariable("usersId") int usersId) {
        this.users.delete(this.users.findById(usersId));
        Collection<Users> results = this.users.findAll();
        model.put("users", results);
        return "redirect:/userListView";
    }
}
