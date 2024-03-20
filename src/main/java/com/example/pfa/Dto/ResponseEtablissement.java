package com.example.pfa.Dto;

import com.example.pfa.Entities.Etablissement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEtablissement {
    Long Id ;
    String Nom ;
    String Adresse ;
    String Email ;
    boolean status ;
    Instant createdAt ;
    Instant updatedAt ;
    public static ResponseEtablissement makeEtablissement(Etablissement etablissement){
        return ResponseEtablissement.builder()
                .Id(etablissement.getId())
                .Nom(etablissement.getNom())
                .Email(etablissement.getEmail())
                .Adresse(etablissement.getAdresse())
                .createdAt(etablissement.getCreatedAt())
                .updatedAt(etablissement.getUpdatedAt())
                .build();
    }
}
