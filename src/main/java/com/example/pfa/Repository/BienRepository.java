package com.example.pfa.Repository;

import com.example.pfa.Entities.Bien;
import com.example.pfa.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BienRepository extends JpaRepository<Bien,Long> {
}
