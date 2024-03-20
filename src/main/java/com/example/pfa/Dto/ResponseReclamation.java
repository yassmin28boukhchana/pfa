package com.example.pfa.Dto;

import com.example.pfa.Entities.Reclamation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseReclamation {
    private Long idReclamation;
    private Date date;
    private String description;
    private Boolean status; // seen or not seen
    private Long ligneReservationId;
    private String nomBien; // Nom du bien concerné par la réclamation
    private Long reservationId; // ID de la réservation concernée
    private String nomUtilisateur; // Nom de l'utilisateur ayant fait la réclamation
    public static ResponseReclamation makeResponseReclamation(Reclamation reclamation, String nomBien, String nomUtilisateur) {
        return ResponseReclamation.builder()
                .date(reclamation.getDate())
                .description(reclamation.getDescription())
                .status(reclamation.getStatus())
                .ligneReservationId((long) reclamation.getLigneReservation().getId_detailsReservais())
                .nomBien(nomBien)
                .reservationId(reclamation.getLigneReservation().getReservation().getId())
                .nomUtilisateur(nomUtilisateur)
                .build();
    }
}
