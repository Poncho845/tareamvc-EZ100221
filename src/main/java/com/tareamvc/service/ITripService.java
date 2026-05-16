package com.tareamvc.service;

import com.tareamvc.model.Trip;
import java.util.List;

public interface ITripService {

    List<Trip> listarTodos();
    Trip buscarPorId(Integer id);
    void guardar(Trip trip);
    void eliminar(Integer id);

    // ✅ CLASE 3: nuevos métodos con Query Methods
    List<Trip> buscarPorEstatus(String estatus);
    List<Trip> buscarEntreCosto(double min, double max);
    List<Trip> buscarDestacadosActivos();
}