package com.tareamvc.repository;

import com.tareamvc.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
}