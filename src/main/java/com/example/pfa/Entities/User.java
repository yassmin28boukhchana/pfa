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
@Table(name="User")
@AllArgsConstructor /* generation de constructeur parametre */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Nom;
    private String Prenom;
    private Boolean status;
    private int Telephone;
    private String Email;
    private String password;
    private String Adresse ;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne

    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reclamation> reclamations;

}
