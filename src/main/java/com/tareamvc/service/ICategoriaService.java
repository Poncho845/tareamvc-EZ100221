package com.tareamvc.service;

import com.tareamvc.model.Categoria;
import java.util.List;

public interface ICategoriaService {

    List<Categoria> listarTodas();
    Categoria buscarPorId(Integer id);
    void guardar(Categoria categoria);
    void eliminar(Integer id);
}