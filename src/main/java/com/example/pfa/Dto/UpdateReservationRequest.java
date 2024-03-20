package com.example.pfa.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReservationRequest {
    private Date dateDebut;
    private Date dateFin;
    private List<Long> biensToAdd;
    private List<Long> biensToRemove;
}
