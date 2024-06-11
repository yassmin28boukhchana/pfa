package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestCategorie;
import com.example.pfa.Dto.ResponseCategorie;
import com.example.pfa.Entities.Categorie;
import com.example.pfa.Repository.CategorieRepository;
import com.example.pfa.Service.CategorieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public void createCategorie(RequestCategorie requestCategorie) {
        Categorie newCategorie = new Categorie();
        newCategorie.setNom(requestCategorie.getNom());
        categorieRepository.save(newCategorie);
    }

    @Override
    public ResponseCategorie getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée pour cet id :: " + id));
        return ResponseCategorie.makeCategorie(categorie);
    }

    @Override
    public List<ResponseCategorie> getAllCategories() {
        return categorieRepository.findAll().stream()
                .map(ResponseCategorie::makeCategorie)
                .collect(Collectors.toList());
    }

    @Override
    public Categorie updateCategorie(Long id, RequestCategorie requestCategorie) {
        Categorie categorieToUpdate = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée avec l'id : " + id));

        if (requestCategorie.getNom() != null && !requestCategorie.getNom().trim().isEmpty()) {
            categorieToUpdate.setNom(requestCategorie.getNom());
        }
        return categorieRepository.save(categorieToUpdate);
    }

    @Override
    public boolean deleteCategorie(Long id) {
        if (!categorieRepository.existsById(id)) {
            return false;
        }
        categorieRepository.deleteById(id);
        return true;
    }
}
