package com.example.pfa.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestReservation {
    private Date dateDebut;
    private Date dateFin;
    private Long userId;
    private List<Long> bienIds; // Liste des identifiants des biens à réserver
}
