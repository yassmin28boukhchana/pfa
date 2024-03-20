package com.example.pfa.Dto;

import com.example.pfa.Entities.Bien;
import com.example.pfa.Entities.BienStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBien {
    private int id;
    private String nom;
    private String description;
    private int capacite;
    private BienStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String nomCategorie; // Nom de la catégorie associée pour la réponse
    private String nomDepartement; // Nom du département associé pour la réponse


 public static ResponseBien makeBien(Bien bien){
     return ResponseBien.builder()
             .nom(bien.getNom())
             .id(bien.getId_bien())
             .capacite(bien.getCapacite())
             .status(bien.getStatus())
             .createdAt(bien.getCreatedAt())
             .updatedAt(bien.getUpdatedAt())
             .description(bien.getDescription())
             .nomCategorie(bien.getCategorie().getNom())
             .nomDepartement(bien.getDepartement().getName())
             .build();
 }
}
