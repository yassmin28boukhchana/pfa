package com.example.pfa.Dto;

import com.example.pfa.Entities.BienStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestBien {
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String nom;

    @NotBlank(message = "La description ne peut pas être vide.")
    private String description;

    @Min(value = 1, message = "La capacité doit être supérieure à 0.")
    private Integer capacite;

    @NotBlank(message = "Le statut ne peut pas être vide.")
    private BienStatus status;

    @NotNull(message = "L'identifiant de la catégorie ne peut pas être null.")
    private Long categorieId;

    @NotNull(message = "L'identifiant du département ne peut pas être null.")
    private Long departementId ;
}
