package com.tareamvc.service;

import com.tareamvc.model.Trip;
import com.tareamvc.repository.ITripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements ITripService {

    @Autowired
    private ITripRepository tripRepository;

    @Override
    public List<Trip> listarTodos() {
        return tripRepository.findAll();
    }

    @Override
    public Trip buscarPorId(Integer id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.orElse(null);
    }

    @Override
    public void guardar(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public void eliminar(Integer id) {
        tripRepository.deleteById(id);
    }

    // ✅ CLASE 3 - Query Method 1
    @Override
    public List<Trip> buscarPorEstatus(String estatus) {
        return tripRepository.findByEstatus(estatus);
    }

    // ✅ CLASE 3 - Query Method 3
    @Override
    public List<Trip> buscarEntreCosto(double min, double max) {
        return tripRepository.findByCostoBetween(min, max);
    }

    // ✅ CLASE 3 - Query Method 2
    @Override
    public List<Trip> buscarDestacadosActivos() {
        return tripRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Activo");
    }
}