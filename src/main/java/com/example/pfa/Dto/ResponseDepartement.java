package com.example.pfa.Dto;

import com.example.pfa.Entities.Departement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDepartement {
    Long id;
    String name;
    boolean status ;
    Instant createdAt;
    Instant updatedAt;
    ResponseEtablissement etablissement;

    public static ResponseDepartement makeDepartement(Departement departement){
        return ResponseDepartement.builder()
                .id(departement.getId())
                .name(departement.getName())
                .status(departement.isStatus())
                .createdAt(departement.getCreatedAt())
                .updatedAt(departement.getUpdatedAt())
                .etablissement(ResponseEtablissement.makeEtablissement(departement.getEtablissement()))
                .build();
    }
}
