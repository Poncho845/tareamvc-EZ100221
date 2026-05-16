package com.tareamvc.repository;

import com.tareamvc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    // ✅ CLASE 3: buscar usuario por username (para login futuro)
    Optional<Usuario> findByUsername(String username);
}