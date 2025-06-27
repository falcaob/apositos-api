package com.iescantillana.services;

import com.iescantillana.models.Aposito;

import java.util.Set;

public interface ApositoService {
	
	// Obtener todos los apósitos
	Iterable<Aposito> getAllApositos(); 

	// Obtener un apósito por ID
    Aposito getApositoById(Long id);
    
    // Crear un apósito con marcas asociadas
    Aposito createAposito(Aposito aposito, Set<Long> marcaIds);
    
    // Actualizar un apósito
    Aposito updateAposito(Long id, Aposito apositoDetails, Set<Long> marcaIds);
    
    // Eliminar un apósito por su ID
    void deleteAposito(Long id); 

}
