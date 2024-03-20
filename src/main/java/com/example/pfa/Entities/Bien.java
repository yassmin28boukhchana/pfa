package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder
@Entity
@NoArgsConstructor
@Table(name="bien")
@AllArgsConstructor
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_bien;

    private String nom;
    private String description;
    private Integer capacite;
    private BienStatus status;
    @CreationTimestamp
    private Instant createdAt ;
    @UpdateTimestamp
    private Instant updatedAt ;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    // Lien vers Departement
    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneReservation> ligneReservations;
}
