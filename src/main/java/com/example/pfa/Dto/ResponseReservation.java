package com.example.pfa.Dto;

import com.example.pfa.Entities.Reservation;
import com.example.pfa.Entities.ReservationStatus;
import com.example.pfa.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseReservation {


     Long id;
     Date dateDebut;
     Date dateFin;
     ReservationStatus status;
     Instant createdAt;
     Instant updatedAt;
     ReservedByUserResponse user ;
    private List<ReservedBienResponse> biens;


    public static ResponseReservation makeReservationResponse(Reservation reservation) {
        ReservedByUserResponse userResponse = ReservedByUserResponse.makeReservedByUser(reservation.getUser());

        List<ReservedBienResponse> biensResponse = reservation.getLigneReservations().stream()
                .map(ligne -> ReservedBienResponse.makeReservedBien(ligne.getBien()))
                .collect(Collectors.toList());

        return ResponseReservation.builder()
                .id(reservation.getId())
                .dateDebut(reservation.getDate_Debut())
                .dateFin(reservation.getDate_Fin())
                .status(reservation.getStatus())
                .createdAt(reservation.getCreatedAt())
                .updatedAt(reservation.getUpdatedAt())
                .user(userResponse)
                .biens(biensResponse)
                .build();
    }

}
