package com.iescantillana.services;

import com.iescantillana.models.Marca;
import com.iescantillana.repositories.MarcaRepository;
import com.iescantillana.services.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService {
	
	@Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Iterable<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca getMarcaById(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
    }

    @Override
    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public Marca updateMarca(Long id, Marca marcaDetails) {
        Marca existingMarca = getMarcaById(id);

        existingMarca.setName(marcaDetails.getName());
        return marcaRepository.save(existingMarca);
    }

    @Override
    public void deleteMarca(Long id) {
        marcaRepository.deleteById(id);
    }

}
