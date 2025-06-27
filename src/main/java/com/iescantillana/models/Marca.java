package com.iescantillana.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre de la marca no puede estar vacío")
	@Size(max = 50, message = "El nombre de la marca no puede tener más de 50 caracteres")
	private String name;

	@ManyToMany(mappedBy = "marcas")
	// @JsonManagedReference
	private Set<Aposito> apositos = new HashSet<>();

}
