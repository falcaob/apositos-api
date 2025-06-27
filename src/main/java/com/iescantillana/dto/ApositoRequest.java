package com.iescantillana.dto;

import com.iescantillana.models.Aposito;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ApositoRequest {
	
	@NotBlank(message = "El nombre del apósito no puede estar vacío")
    private String name;

	@NotNull(message = "La absorción no puede ser nulo")
    @Positive(message = "El absorción debe ser un número positivo")
    private Integer absorption;

    @NotBlank(message = "El campo antimicrobial no puede estar vacío")
    private String antimicrobial;
    
    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un número positivo")
    private float price;

    @NotNull(message = "Debe proporcionar al menos un ID de marca")
    private Set<Long> marcaIds;

}
