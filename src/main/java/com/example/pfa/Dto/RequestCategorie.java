package com.example.pfa.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategorie {
    @NotBlank(message = "Le nom de la catégorie ne peut pas être vide")
    private String nom;
}
