package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestCategorie;
import com.example.pfa.Dto.ResponseCategorie;
import com.example.pfa.Entities.Categorie;
import com.example.pfa.Entities.Departement;
import com.example.pfa.Repository.CategorieRepository;
import com.example.pfa.Service.CategorieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository ;

    @Override
    public void createCategorie(RequestCategorie requestCategorie) {
        // Crée une nouvelle instance de Categorie
        Categorie newCategorie = new Categorie();
        newCategorie.setNom(requestCategorie.getNom());

        // Gère le cas où parentId est null ou 0, ce qui indique l'absence de parent
        if (requestCategorie.getParentId() == null || requestCategorie.getParentId() == 0) {
            newCategorie.setParent(null); // Explicitement défini à null pour les catégories de niveau supérieur
        } else {
            // Recherche la catégorie parente existante par parentId
            Categorie parent = categorieRepository.findById(requestCategorie.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found with ID: " + requestCategorie.getParentId()));
            newCategorie.setParent(parent);
        }

        // Persiste la nouvelle catégorie dans la base de données
        categorieRepository.save(newCategorie);
    }

    @Override
    public ResponseCategorie getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée pour cet id :: " + id));
        return ResponseCategorie.makeCategorie(categorie) ;
    }

    @Override
    public List<ResponseCategorie> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        List<ResponseCategorie> categoriesFormated = new ArrayList<>() ;
        for( Categorie categorie:categories){
            ResponseCategorie categorie1 = ResponseCategorie.makeCategorie(categorie);
            categoriesFormated.add(categorie1);
        }
        return categoriesFormated;
    }

    @Override
    public Categorie updateCategorie(Long id, RequestCategorie requestCategorie) {
        Categorie categorieToUpdate = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée avec l'id : " + id));

        // Mettez à jour le nom seulement s'il est non null dans la demande
        if (requestCategorie.getNom() != null && !requestCategorie.getNom().trim().isEmpty()) {
            categorieToUpdate.setNom(requestCategorie.getNom());
        }

        // Mettez à jour le parentId, avec une gestion pour null ou 0 si votre logique métier le permet
        if (requestCategorie.getParentId() != null) {
            if (requestCategorie.getParentId() == 0) {
                // Traitement spécifique lorsque parentId est 0 (potentiellement définir comme sans parent)
                categorieToUpdate.setParent(null);
            } else {
                // Recherche et affecte le nouveau parent si parentId est positif
                Categorie parent = categorieRepository.findById(requestCategorie.getParentId())
                        .orElseThrow(() -> new RuntimeException("Parent category not found with ID: " + requestCategorie.getParentId()));
                categorieToUpdate.setParent(parent);
            }
        }

        return categorieRepository.save(categorieToUpdate);
    }

    @Override
    public boolean deleteCategorie(Long id) {
      if(! categorieRepository.existsById(id)){
          return false ;
      }
      categorieRepository.deleteById(id);
      return true ;
    }

    @Override
    public List<ResponseCategorie> getSousCategories(Long parentId) {
        List<Categorie> sousCategories = categorieRepository.findByParentId(parentId);
        return sousCategories.stream()
                .map(categorie -> new ResponseCategorie(categorie.getId(), categorie.getNom(), categorie.getParent() != null ? categorie.getParent().getId() : null, categorie.getCreatedAt(), categorie.getUpdatedAt()))
                .collect(Collectors.toList());
    }
}
