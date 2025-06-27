package com.iescantillana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iescantillana.models.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
