package com.example.pfa.Repository;

import com.example.pfa.Entities.Categorie;
import com.example.pfa.Entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
}
