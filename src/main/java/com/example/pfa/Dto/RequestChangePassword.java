package com.example.pfa.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestChangePassword {
    @NotBlank
    String oldPassword ;
    @NotBlank
    String newPassword ;
}
