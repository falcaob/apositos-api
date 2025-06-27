package com.iescantillana.controllers;

import com.iescantillana.models.Marca;
import com.iescantillana.repositories.MarcaRepository;
import com.iescantillana.services.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private MarcaRepository marcaRepo;

	@GetMapping
	public Iterable<Marca> getAllMarcas() {
		return marcaService.getAllMarcas();
	}

	@GetMapping("/{id}")
	public Marca getMarcaById(@PathVariable Long id) {
		return marcaService.getMarcaById(id);
	}

	@PostMapping
	public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) {
		Marca savedMarca = marcaRepo.save(marca);
		return ResponseEntity.ok(savedMarca);
	}

	@PutMapping("/{id}")
	public Marca updateMarca(@PathVariable Long id, @Valid @RequestBody Marca marcaDetails) {
		return marcaService.updateMarca(id, marcaDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteMarca(@PathVariable Long id) {
		marcaService.deleteMarca(id);
	}

}
