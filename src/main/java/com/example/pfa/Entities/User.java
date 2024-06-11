package com.example.pfa.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
@Builder  /* generation de constructeur par des parametres specefiques */
@Entity
@NoArgsConstructor /*  generation de constructeur avec parametres */
@Table(name="User")
@AllArgsConstructor /* generation de constructeur parametre */
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Nom;
    private String Prenom;
    private Boolean status;
    private int Telephone;
    private String email;
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

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword(){
        return password ;
    }
}
