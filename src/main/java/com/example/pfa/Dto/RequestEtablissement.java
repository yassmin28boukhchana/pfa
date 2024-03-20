package com.example.pfa.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestEtablissement {
    Long Id ;
    String Nom ;
    String Adresse ;
    @NotBlank(message="Email is required !")
    @jakarta.validation.constraints.Email(message = "Email is not valid !")
    String Email;
    Boolean status ;
}
