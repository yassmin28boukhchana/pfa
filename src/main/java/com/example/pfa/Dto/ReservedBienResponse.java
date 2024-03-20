package com.example.pfa.Dto;

import com.example.pfa.Entities.Bien;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservedBienResponse {
    private String nom;
    private String categorie;

    public static ReservedBienResponse makeReservedBien( Bien bien) {
        return ReservedBienResponse.builder()
                .nom(bien.getNom())
                .categorie(bien.getCategorie().getNom())
                .build();
    }
}
