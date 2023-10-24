package com.ca.jmccabepetition.service;

import com.ca.jmccabepetition.model.Petition;
import com.ca.jmccabepetition.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    public List<Petition> searchPetitions(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Implement your search logic here
        // For example, you can search by title or description containing the query
        return petitionRepository.findByTitleContainingOrDescriptionContaining(query, query);
    }
    public void createPetition(Petition petition) {
        // Implement the logic to save the petition using the repository
        petitionRepository.save(petition);
    }

    public Petition findPetitionWithSigners(Long petitionId) {
        return petitionRepository.findByIdWithSigners(petitionId);
    }

}
