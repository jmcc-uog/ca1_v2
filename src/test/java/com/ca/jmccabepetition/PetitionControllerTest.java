package com.ca.jmccabepetition;

import com.ca.jmccabepetition.controller.PetitionController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(PetitionController.class)
public class PetitionControllerTest {

    //@Autowired
    //private MockMvc mockMvc;

    //@Mock
    //private PetitionService petitionService;
/*
   @Test
    public void testShowCreatePetitionForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/create-petition"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-petition"))
                .andExpect(model().attributeExists("petition"));
        // You can add more specific assertions based on your requirements
    }

    @Test
    public void testShowPetitionById() throws Exception {
        Long petitionId = 1L;
        Petition petition = new Petition(); // Create a sample petition

        when(petitionService.findPetitionById(petitionId)).thenReturn(Optional.of(petition));

        mockMvc.perform(MockMvcRequestBuilders.get("/petition/{petitionId}", petitionId))
                .andExpect(status().isOk())
                .andExpect(view().name("view-petition"))
                .andExpect(model().attribute("petition", petition));
        // You can add more specific assertions based on your requirements
    }

    @Test
    public void testShowSignersForPetition() throws Exception {
        Long petitionId = 1L;
        Petition petition = new Petition(); // Create a sample petition with signers

        when(petitionService.findPetitionWithSigners(petitionId)).thenReturn(petition);

        mockMvc.perform(MockMvcRequestBuilders.get("/view-signers/{petitionId}", petitionId))
                .andExpect(status().isOk())
                .andExpect(view().name("view-signers"))
                .andExpect(model().attribute("petition", petition));
        // You can add more specific assertions based on your requirements
    }

    @Test
    public void testCreatePetition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/create-petition"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-petition"))
                .andExpect(model().attributeExists("petition"));
        // You can add more specific assertions based on your requirements
    }
*/
    // Add more controller tests as needed
}

