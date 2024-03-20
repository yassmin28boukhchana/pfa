package com.example.pfa.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestUser {
    String Nom;
    String Prenom;
    @Max(value = 99999999,message = "phone number invalid !")
    @Min(value = 10000000,message = "phone number invalid !")// 99506512
    int Telephone;
    @NotBlank(message="Email is required !")
    @jakarta.validation.constraints.Email(message = "Email is not valid !")
    String Email;
    @NotBlank(message="password is required !")
    String password ;
    @Min(value=1,message="EtablissementId is required")
    Long EtablissementId;
    Boolean status ;
    String Adresse ;
}
