package com.example.pfa.Dto;

import com.example.pfa.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data   // kima Getter w setter amataaml moshkla fl Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    Long id;
    String Nom;
    String Prenom;
    Boolean status;
    int Telephone;
    String email;
    String password;
    String Adresse ;
    Instant createdAt;
    Instant updatedAt;
    ResponseEtablissement etablissement ;
    public static ResponseUser makeUser(User user) {
        return ResponseUser.builder()
                .id(user.getId())
                .Nom(user.getNom())
                .Prenom(user.getPrenom())
                .email(user.getEmail())
                .status(user.getStatus())
                .Telephone(user.getTelephone())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .Adresse(user.getAdresse())
                .etablissement(ResponseEtablissement
                        .makeEtablissement(user.getEtablissement()))

                .build();
    }
}
