package com.ca.jmccabepetition;

import com.ca.jmccabepetition.model.Petition;
import com.ca.jmccabepetition.model.Signer;
import com.ca.jmccabepetition.repository.PetitionRepository;
import com.ca.jmccabepetition.service.PetitionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class PetitionServiceTest {

    @InjectMocks
    private PetitionService petitionService;

    @Mock
    private PetitionRepository petitionRepository;

    @Test
    public void testFindAllPetitions() {
        List<Petition> petitions = new ArrayList<>();
        // Add sample petitions to the list

        Mockito.when(petitionRepository.findAll()).thenReturn(petitions);

        List<Petition> result = petitionService.getAllPetitions();
        assertNotNull(result);
        assertEquals(petitions, result);
    }

    @Test
    public void testFindPetitionById() {
        Long petitionId = 1L;
        Optional<Petition> petition = Optional.of(new Petition()); // Create a sample petition

        Mockito.when(petitionRepository.findById(petitionId)).thenReturn(petition);

        Optional<Petition> result = petitionService.findPetitionById(petitionId);
        assertNotNull(result);
        assertEquals(petition, result);
        // You can add more specific assertions based on your requirements
    }
    @Test
    public void testFindPetitionWithSigners() {
        Long petitionId = 1L;
        Petition petition = new Petition(); // Create a sample petition with signers

        Mockito.when(petitionRepository.findByIdWithSigners(petitionId)).thenReturn(petition);

        Petition result = petitionService.findPetitionWithSigners(petitionId);
        assertNotNull(result);
        assertEquals(petition, result);
        // You can add more specific assertions based on your requirements
    }
    @Test
    public void testCreatePetition() {
        Petition petition = new Petition(); // Create a sample petition

        petitionService.createPetition(petition);

        // Verify that the save method was called
        verify(petitionRepository).save(petition);
    }
    @Test
    public void testAddSignerToPetition() {
        Long petitionId = 1L;
        Signer signer = new Signer(); // Create a sample signer

        Petition petition = new Petition(); // Create a sample petition
        petition.setId(petitionId);

        Mockito.when(petitionRepository.findById(petitionId)).thenReturn(Optional.of(petition));

        petitionService.addSignerToPetition(petitionId, signer);

        // Verify that the petition was updated with the signer
        assertEquals(1, petition.getSigners().size());
        assertEquals(signer, petition.getSigners().get(0));
        // Verify that the save method was called
        verify(petitionRepository).save(petition);
    }
    // Add more service tests as needed
}

