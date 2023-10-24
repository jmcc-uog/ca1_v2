package com.ca.jmccabepetition;

import com.ca.jmccabepetition.model.Petition;
import com.ca.jmccabepetition.service.PetitionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetitionService petitionService;

    @Test
    public void testShowAllPetitions() throws Exception {
        Petition petition1 = new Petition("Petition 1", "Description 1");
        Petition petition2 = new Petition("Petition 2", "Description 2");
        List<Petition> petitions = Arrays.asList(petition1, petition2);

        when(petitionService.getAllPetitions()).thenReturn(petitions);

        mockMvc.perform(MockMvcRequestBuilders.get("/list-petitions"))
                .andExpect(status().isOk());



    }



    // Add more test methods for other controller actions as needed

    @Test
    public void testShowCreatePetitionForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/create-petition"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-petition"))
                .andExpect(model().attributeExists("petition"));
    }

    // Add more test methods for other controller actions as needed
}
