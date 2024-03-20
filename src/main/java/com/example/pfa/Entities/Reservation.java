package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder  /* generation de constructeur par des parametres specefiques */
@Entity
@NoArgsConstructor /*  generation de constructeur avec parametres */
@Table(name="reservation")
@AllArgsConstructor /* generation de constructeur parametre */
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private Instant createdAt;  // Instant type defini mte3 wakt l instant hedhi yaani
    @UpdateTimestamp
    private Instant updatedAt;

    @Temporal(TemporalType.DATE)
    private Date date_Debut;

    @Temporal(TemporalType.DATE)
    private Date date_Fin;
    private ReservationStatus status ;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneReservation> ligneReservations;

}
