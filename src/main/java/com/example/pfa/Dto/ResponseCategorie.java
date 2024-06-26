package com.example.pfa.Dto;

import com.example.pfa.Entities.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCategorie {

    private Long id;
    private String nom;
    Instant createdAt;
    Instant updatedAt;

    public static ResponseCategorie makeCategorie(Categorie categorie){
        return ResponseCategorie.builder()
                .id(categorie.getId())
                .nom(categorie.getNom())
                .createdAt(categorie.getCreatedAt())
                .updatedAt(categorie.getUpdatedAt())
                .build();
    }
}
