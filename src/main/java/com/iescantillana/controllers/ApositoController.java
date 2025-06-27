package com.iescantillana.controllers;

import com.iescantillana.dto.ApositoRequest;
import com.iescantillana.models.Aposito;
import com.iescantillana.models.Marca;
import com.iescantillana.repositories.ApositoRepository;
import com.iescantillana.services.ApositoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/apositos")
public class ApositoController {

	@Autowired
	private ApositoService apositoService;

	@Autowired
	private ApositoRepository apositoRepo;

	@GetMapping
	public Iterable<Aposito> getAllApositos() {
		return apositoService.getAllApositos();
	}

	@GetMapping("/{id}")
	public Aposito getApositoById(@PathVariable Long id) {
		return apositoService.getApositoById(id);
	}

	@PostMapping
	public Aposito createAposito(@Valid @RequestBody ApositoRequest request) {
		// Crear un nuevo objeto Aposito a partir de los datos del DTO
		Aposito aposito = new Aposito();
		aposito.setName(request.getName());
		aposito.setAbsorption(request.getAbsorption());
		aposito.setAntimicrobial(request.getAntimicrobial());
		aposito.setPrice(request.getPrice());

		// Llamar al servicio pasando el objeto Aposito y los IDs de marca
		return apositoService.createAposito(aposito, request.getMarcaIds());
	}

	@PutMapping("/{id}")
	public Aposito updateAposito(@PathVariable Long id, @Valid @RequestBody ApositoRequest request) {
		// Buscar el Aposito por ID
		Optional<Aposito> optionalAposito = apositoRepo.findById(id);

		// Si no se encuentra, lanzar una excepción o manejar el caso
		if (!optionalAposito.isPresent()) {
			throw new RuntimeException("Aposito no encontrado con el ID: " + id);
		}

		// Obtener el Aposito existente
		Aposito aposito = optionalAposito.get();

		// Actualizar los campos del Aposito con los nuevos valores
		aposito.setName(request.getName());
		aposito.setAbsorption(request.getAbsorption());
		aposito.setAntimicrobial(request.getAntimicrobial());
		aposito.setPrice(request.getPrice());

		return apositoService.updateAposito(id, aposito, request.getMarcaIds());
	}

	@DeleteMapping("/{id}")
	public void deleteAposito(@PathVariable Long id) {
		apositoService.deleteAposito(id);
	}
}
