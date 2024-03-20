package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@Builder  /* generation de constructeur par des parametres specefiques */
@Entity
@NoArgsConstructor /*  generation de constructeur avec parametres */
@Table(name="ligne_reservation")
@AllArgsConstructor /* generation de constructeur parametre */
public class LigneReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detailsReservais;

    @ManyToOne
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @OneToOne(mappedBy = "ligneReservation", fetch = FetchType.LAZY, optional = true)
    private Reclamation reclamation;


}
