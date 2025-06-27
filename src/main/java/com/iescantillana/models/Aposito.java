package com.iescantillana.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Aposito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre de la marca no puede estar vacío")
	@Size(max = 100, message = "El nombre de la marca no tener más de 100 caracteres")
	private String name;

	@Min(value = 1, message = "El rango de valores es de 1 a 4")
	@Max(value = 4, message = "El rango de valores es de 1 a 4")
	private int absorption;

	@NotBlank(message = "El campo antimicrobial no puede estar vacío")
	private String antimicrobial;

	private float price;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aposito_marca", joinColumns = @JoinColumn(name = "aposito_id"), inverseJoinColumns = @JoinColumn(name = "marca_id"))
	@JsonBackReference
	private Set<Marca> marcas = new HashSet<>();

	// Solución problema, no se puede iterar y a la vez modificar Set
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Marca marca = (Marca) obj;
		return id != null && id.equals(marca.getId());
	}

}
