package com.example.pfa.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestDepartement {
    @Min(value=1,message="DepartementId is required")
    Long IdEtablissement ;
    String name ;
    Boolean status ;
}
