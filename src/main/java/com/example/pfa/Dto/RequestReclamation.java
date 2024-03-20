package com.example.pfa.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestReclamation {
    private Long ligneReservationId; // ID de la ligne de réservation associée au bien
    private String description; // Description de la réclamation
}
