package com.example.pfa.Service;

import com.example.pfa.Dto.RequestCategorie;
import com.example.pfa.Dto.ResponseCategorie;
import com.example.pfa.Entities.Categorie;

import java.util.List;

public interface CategorieService {

    void createCategorie(RequestCategorie requestCategorie);

    ResponseCategorie getCategorieById(Long id);

    List<ResponseCategorie> getAllCategories();

    Categorie updateCategorie(Long id, RequestCategorie requestCategorie);

    boolean deleteCategorie(Long id);
}
