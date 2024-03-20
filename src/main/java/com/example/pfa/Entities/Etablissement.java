package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@ToString
@Builder  /* generation de constructeur par des parametres specefiques */
@Entity
@NoArgsConstructor /*  generation de constructeur avec parametres */
@Table(name="etablissement")
@AllArgsConstructor /* generation de constructeur parametre */
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id ;
    private String Nom ;
    private String Email ;
    private String Adresse ;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    private boolean status ;

    @OneToMany(mappedBy = "etablissement")
    private List<Departement> departements ;

    @OneToMany(mappedBy = "etablissement")
    private List<User> user;

}
