package com.example.pfa.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategorie {
    @NotBlank(message = "Le nom de la catégorie ne peut pas être vide")
    private String nom;

    @PositiveOrZero(message = "L'ID du parent doit être positif ou zéro pour indiquer aucune catégorie parente")
    private Long parentId; // Peut être null ou zéro pour une catégorie de niveau supérieur
}
