package com.example.pfa.Repository;

import com.example.pfa.Entities.Categorie;
import com.example.pfa.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    List<Categorie> findByParentId(Long parentId);
}
