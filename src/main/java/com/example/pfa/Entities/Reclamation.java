package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamation;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;
    private Boolean status = false; // par defaut la reclamation est not seen cad false

    // La relation OneToOne indique qu'une ligne de réservation est associée à une seule réclamation.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ligne_reservation_id", nullable = false)
    private LigneReservation ligneReservation;

    // Relation ManyToOne avec User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
