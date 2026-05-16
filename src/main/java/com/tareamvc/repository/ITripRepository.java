package com.tareamvc.repository;

import com.tareamvc.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ITripRepository extends JpaRepository<Trip, Integer> {

    // ✅ CLASE 3 - Query Method 1: buscar por estatus
    List<Trip> findByEstatus(String estatus);

    // ✅ CLASE 3 - Query Method 2: buscar por destacado Y estatus, ordenado por id desc
    List<Trip> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);

    // ✅ CLASE 3 - Query Method 3: buscar trips cuyo costo esté entre dos valores
    List<Trip> findByCostoBetween(double costoMin, double costoMax);

    // ✅ CLASE 3 - Query Method 4: buscar por lista de estatus
    List<Trip> findByEstatusIn(List<String> estatusList);
}