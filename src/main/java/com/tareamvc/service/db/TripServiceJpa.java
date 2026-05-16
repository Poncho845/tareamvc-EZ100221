package com.tareamvc.service.db;

import com.tareamvc.model.Trip;
import com.tareamvc.repository.ITripRepository;
import com.tareamvc.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TripServiceJpa implements ITripService {

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

    @Override
    public List<Trip> buscarPorEstatus(String estatus) {
        return tripRepository.findByEstatus(estatus);
    }

    @Override
    public List<Trip> buscarEntreCosto(double min, double max) {
        return tripRepository.findByCostoBetween(min, max);
    }

    @Override
    public List<Trip> buscarDestacadosActivos() {
        return tripRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Activo");
    }
}