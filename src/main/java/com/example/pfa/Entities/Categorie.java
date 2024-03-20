package com.example.pfa.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder  /* generation de constructeur par des parametres specefiques */
@Entity
@NoArgsConstructor /*  generation de constructeur avec parametres */
@Table(name="categorie")
@AllArgsConstructor /* generation de constructeur parametre */
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private   Long id ;
    @NotBlank(message = "Le nom de la catégorie ne peut pas être vide")
     private String nom ;
    @CreationTimestamp
     private Instant createdAt ;
    @UpdateTimestamp
     private Instant updatedAt ;
    @ManyToOne
    @JoinColumn(name = "parent_id",nullable = true)
    private Categorie parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Categorie> sousCategories = new HashSet<>();


    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bien> biens;
}
