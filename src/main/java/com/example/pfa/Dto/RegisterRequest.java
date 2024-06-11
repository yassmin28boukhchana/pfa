package com.example.pfa.Dto;

import com.example.pfa.Entities.Role;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String Nom ;
    private String Prenom ;
    @NotBlank(message="Email is required !")
    @jakarta.validation.constraints.Email(message = "Email is not valid !")
    private String email ;
    private String password ;
    private String adresse ;
    @Max(value = 99999999,message = "phone number invalid !")
    @Min(value = 10000000,message = "phone number invalid !")// 99506512
    private int telephone ;
    @Min(value=1,message="EtablissementId is required")
    Long EtablissementId;
    Role role ;
}
