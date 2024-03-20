package com.example.pfa.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserUpdate {
    Long id;
    String Nom;
    String Prenom;
    @Max(value = 99999999,message = "phone number invalid !")
    @Min(value = 10000000,message = "phone number invalid !")// 99506512
    Integer Telephone;
    String Email;
    @Min(value=1,message="DepartementId is required")
    Long etablissement_id;
    Boolean status ;
    String Adresse ;
}
