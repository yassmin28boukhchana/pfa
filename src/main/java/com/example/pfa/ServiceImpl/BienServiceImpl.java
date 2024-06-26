package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestBien;
import com.example.pfa.Dto.ResponseBien;
import com.example.pfa.Entities.Bien;
import com.example.pfa.Entities.Categorie;
import com.example.pfa.Entities.Departement;
import com.example.pfa.Repository.BienRepository;
import com.example.pfa.Repository.CategorieRepository;
import com.example.pfa.Repository.DepartementRepository;
import com.example.pfa.Service.BienService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BienServiceImpl implements BienService {
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public void createBien(RequestBien requestBien) {
        Departement departement = departementRepository.findById(requestBien.getDepartementId())
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé pour cet id :: " + requestBien.getDepartementId()));
        Categorie categorie = categorieRepository.findById(requestBien.getCategorieId())
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée pour cet id :: " + requestBien.getCategorieId()));
        Bien bien = Bien.builder()
                .nom(requestBien.getNom())
                .description(requestBien.getDescription())
                .capacite(requestBien.getCapacite())
                .departement(departement)
                .categorie(categorie)
                .status(requestBien.getStatus())
                .autorisation(requestBien.getAutorisation()) // Ajout de l'autorisation
                .build();
        bienRepository.save(bien);
    }

    @Override
    public List<ResponseBien> getAllBiens() {
        List<Bien> biens = bienRepository.findAll();
        List<ResponseBien> formatedBien = new ArrayList<>();
        for (Bien bien : biens) {
            ResponseBien responseBien = ResponseBien.makeBien(bien);
            formatedBien.add(responseBien);
        }
        return formatedBien;
    }

    @Override
    public ResponseBien getBienById(Long id) {
        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bien non trouvé pour cet id :: " + id));
        return ResponseBien.makeBien(bien);
    }

    @Override
    public Boolean deleteBien(Long id) {
        if (!bienRepository.existsById(id)) {
            return false;
        }
        bienRepository.deleteById(id);
        return true;
    }

    @Override
    public Bien UpdateBien(Long id, RequestBien requestBien) {
        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bien non trouvé pour cet id :: " + id));
        // Vérifie si les champs de requestBien ne sont pas null avant de mettre à jour
        if (requestBien.getNom() != null) {
            bien.setNom(requestBien.getNom());
        }
        if (requestBien.getDescription() != null) {
            bien.setDescription(requestBien.getDescription());
        }
        if (requestBien.getCapacite() != null) {
            bien.setCapacite(requestBien.getCapacite());
        }
        if (requestBien.getStatus() != null) {
            bien.setStatus(requestBien.getStatus());
        }
        if (requestBien.getDepartementId() != null) {
            Departement departement = departementRepository.findById(requestBien.getDepartementId())
                    .orElseThrow(() -> new EntityNotFoundException("Département non trouvé pour cet id :: " + requestBien.getDepartementId()));
            bien.setDepartement(departement);
        }
        if (requestBien.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(requestBien.getCategorieId())
                    .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée pour cet id :: " + requestBien.getCategorieId()));
            bien.setCategorie(categorie);
        }
        if (requestBien.getAutorisation() != null) {
            bien.setAutorisation(requestBien.getAutorisation());
        }
        return bienRepository.save(bien);
    }
}
