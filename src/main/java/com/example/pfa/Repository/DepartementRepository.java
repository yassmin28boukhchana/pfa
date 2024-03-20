package com.example.pfa.Repository;

import com.example.pfa.Entities.Departement;
import com.example.pfa.Entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository  extends JpaRepository<Departement,Long> {
}
