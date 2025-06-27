package com.iescantillana.services;

import com.iescantillana.models.Aposito;
import com.iescantillana.models.Marca;
import com.iescantillana.repositories.ApositoRepository;
import com.iescantillana.repositories.MarcaRepository;
import com.iescantillana.services.ApositoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApositoServiceImpl implements ApositoService {

	@Autowired
	private ApositoRepository apositoRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Override
	public Iterable<Aposito> getAllApositos() {
		return apositoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Aposito getApositoById(Long id) {
		return apositoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Aposito no encontrado con ID: " + id));
	}

	public Aposito createAposito(Aposito aposito, Set<Long> marcaIds) {
		// Validar entrada
		if (aposito == null) {
			throw new IllegalArgumentException("El objeto Aposito no puede ser nulo");
		}
		if (marcaIds == null || marcaIds.isEmpty()) {
			throw new IllegalArgumentException("Debe proporcionar al menos un ID de marca");
		}

		// Recuperar las marcas desde los IDs proporcionados
		Set<Marca> marcas = marcaIds.stream()
				.map(id -> marcaRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id)))
				.collect(Collectors.toSet());

		aposito.setMarcas(marcas);

		return apositoRepository.save(aposito);
	}

	@Override
	public Aposito updateAposito(Long id, Aposito apositoDetails, Set<Long> marcaIds) {
		Aposito existingAposito = getApositoById(id);

		existingAposito.setName(apositoDetails.getName());
		existingAposito.setAbsorption(apositoDetails.getAbsorption());
		existingAposito.setAntimicrobial(apositoDetails.getAntimicrobial());

		// Asociar las marcas proporcionadas al apósito
		Set<Marca> marcas = marcaIds.stream()
				.map(idMarca -> marcaRepository.findById(idMarca)
						.orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + idMarca)))
				.collect(Collectors.toSet());

		existingAposito.setMarcas(marcas);
		return apositoRepository.save(existingAposito);
	}

	@Override
	public void deleteAposito(Long id) {
		apositoRepository.deleteById(id);
	}

}
