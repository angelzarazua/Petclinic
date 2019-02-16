/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.owner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author ciaba
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class AlbumController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetRepository pets;
    private final OwnerRepository owners;
    private final AlbumRepository albums;
    
    private static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/resources/imagesPets";
    
    public AlbumController(PetRepository pets, OwnerRepository owners, AlbumRepository albums) {
        this.pets = pets;
        this.owners = owners;
        this.albums = albums;
    }
    
    @ModelAttribute("album")
    public Album loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model) {
        Pet pet = this.pets.findById(petId);
        model.put("pet", pet);
        Album album = new Album();
        pet.addPhoto(album);
        return album;
    }
    
    @GetMapping("/pets/{petId}/album")
    public String verAlbum(@PathVariable("petId") int petId, Map<String, Object> model) {
        Pet pet = this.pets.findById(petId);
        model.put("pet", pet);
        return "album/albumMascosta";
    }
    
    @PostMapping("/pets/{petId}/album")
    public String processNewPhoto(@RequestParam("file") MultipartFile[]f,@Valid Album album, BindingResult result) {
        StringBuilder fileNames = new StringBuilder();
        if (result.hasErrors()) {
            return "redirect:/owners/{ownerId}";
        } else {
            for(MultipartFile file:f){
                System.out.println("ARCHIVO: "+file.getOriginalFilename());
                Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(PetController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.albums.save(album);          
            return "redirect:/owners/{ownerId}/pets/{petId}/album";           
        }
    }
}
