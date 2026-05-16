package com.tareamvc;

import com.tareamvc.model.Categoria;
import com.tareamvc.model.Perfil;
import com.tareamvc.model.Trip;
import com.tareamvc.model.Usuario;
import com.tareamvc.repository.ICategoriaRepository;
import com.tareamvc.repository.IPerfilRepository;
import com.tareamvc.repository.ITripRepository;
import com.tareamvc.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private IPerfilRepository perfilRepository;
    @Autowired private IUsuarioRepository usuarioRepository;
    @Autowired private ICategoriaRepository categoriaRepository;
    @Autowired private ITripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {

        if (tripRepository.count() == 0) {
            System.out.println("=== Cargando datos iniciales ===");

            // Perfiles
            Perfil cliente   = new Perfil("Cliente");
            Perfil admin     = new Perfil("Administrador");
            Perfil moderador = new Perfil("Moderador");
            perfilRepository.save(cliente);
            perfilRepository.save(admin);
            perfilRepository.save(moderador);

            // Usuario
            Usuario user1 = new Usuario();
            user1.setNombre("David Escobar");
            user1.setEmail("david@ufg.edu.sv");
            user1.setUsername("david");
            user1.setPassword("123456");
            user1.setEstatus("Activo");
            user1.agregarPerfil(cliente);
            user1.agregarPerfil(admin);
            usuarioRepository.save(user1);

            // Categorías
            Categoria catAventura   = new Categoria("Aventura",   "Actividades extremas");
            Categoria catCultura    = new Categoria("Cultura",    "Sitios históricos");
            Categoria catNaturaleza = new Categoria("Naturaleza", "Parques y paisajes");
            categoriaRepository.save(catAventura);
            categoriaRepository.save(catCultura);
            categoriaRepository.save(catNaturaleza);

            // ✅ FIX: ahora usamos LocalDate en lugar de Date
            crearTrip("Rapel en Volcán de Izalco", "Descenso en rapel",
                    LocalDate.of(2026, 5, 20), 25.0, catAventura,   user1, "/images/rapel-volcan.jpg");
            crearTrip("Surf en El Tunco", "Surf en la playa",
                    LocalDate.of(2026, 6, 25), 20.0, catAventura,   user1, "/images/surf-tunco.jpg");
            crearTrip("Kayak en Coatepeque", "Paseo en kayak",
                    LocalDate.of(2026, 7, 10), 18.0, catNaturaleza, user1, "/images/kayak-coatepeque.jpg");
            crearTrip("Visita a Joya de Cerén", "Sitio arqueológico",
                    LocalDate.of(2026, 7, 20),  8.0, catCultura,    user1, "/images/joya-ceren.jpg");
            crearTrip("Comida y Flores en Ataco", "Pueblo con encanto",
                    LocalDate.of(2026, 4, 25), 12.0, catCultura,    user1, "/images/comida-flores.jpg");
            crearTrip("Senderismo en Cerro Verde", "Caminata con vista",
                    LocalDate.of(2026, 8,  5), 10.0, catNaturaleza, user1, "/images/deslizadero.jpg");

            System.out.println("¡Datos iniciales cargados correctamente!");
        } else {
            System.out.println("Ya existen datos. No se cargaron nuevos.");
        }
    }

    private void crearTrip(String nombre, String desc, LocalDate fecha,
                           double costo, Categoria cat, Usuario user, String img) {
        Trip t = new Trip();
        t.setNombre(nombre);
        t.setDescripcion(desc);
        t.setFecha(fecha);
        t.setCosto(costo);
        t.setEstatus("Activo");
        t.setDestacado(1);
        t.setImagen(img);
        t.setDetalles("Hermosa experiencia en " + nombre + ". " + desc + ".");
        t.setCategoria(cat);
        t.setUsuario(user);
        tripRepository.save(t);
    }
}