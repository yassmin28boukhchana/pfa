package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder  /* generation de constructeur par des parametres specefiques */
@Entity
@NoArgsConstructor /*  generation de constructeur avec parametres */
@Table(name="departement")
@AllArgsConstructor /* generation de constructeur parametre */
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean status ;

    @CreationTimestamp
    private Instant createdAt;  // Instant type defini mte3 wakt l instant hedhi yaani
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne

    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;

    // Biens dans ce département
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bien> biens;
}
