package com.ca.jmccabepetition.repository;


import com.ca.jmccabepetition.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Long> {
    // You can define custom query methods here if needed.
    List<Petition> findByTitleContainingIgnoreCase(String keyword);
}
