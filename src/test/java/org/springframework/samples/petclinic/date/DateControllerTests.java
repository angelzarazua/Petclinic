/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.springframework.samples.petclinic.date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
/**
 * 
 * @author Zarazua
 */

@RunWith(SpringRunner.class)
@WebMvcTest(DateController.class)
public class DateControllerTests {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testShowDate() throws Exception{
        mockMvc.perform(get("/rrr"))                
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("date"))
                .andExpect(view().name("/dates/createorupdateDateForm"));
    }

}
