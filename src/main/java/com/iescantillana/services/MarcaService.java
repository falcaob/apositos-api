package com.iescantillana.services;


import com.iescantillana.models.Marca;

public interface MarcaService {
	
	// Obtener todas las marcas
	Iterable<Marca> getAllMarcas();
	
	// Obtener una marca por ID
    Marca getMarcaById(Long id);
    
    // Crear una nueva marca
    Marca createMarca(Marca marca); 
    
    // Actualizar una marca
    Marca updateMarca(Long id, Marca marcaDetails);
    
    // Eliminar una marca
    void deleteMarca(Long id);

}
