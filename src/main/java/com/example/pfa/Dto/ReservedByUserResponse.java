package com.example.pfa.Dto;

import com.example.pfa.Entities.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservedByUserResponse {
    String nom ;
    String prenom ;
    String email ;
    public static ReservedByUserResponse makeReservedByUser (User user ){
        return ReservedByUserResponse.builder()
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .build();
    }
}
