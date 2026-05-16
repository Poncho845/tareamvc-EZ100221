package com.tareamvc.controller;

import com.tareamvc.model.Categoria;
import com.tareamvc.model.Trip;
import com.tareamvc.service.ICategoriaService;
import com.tareamvc.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ITripService tripService;

    @Autowired
    private ICategoriaService categoriaService;

    // Listar todos los trips
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trips", tripService.listarTodos());
        return "listado";
    }

    // Formulario nuevo trip
    @GetMapping("/new")
    public String nuevo(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formTrip";
    }

    // ✅ FIX: guardar recibe categoriaId por separado
    @PostMapping("/save")
    public String guardar(@ModelAttribute Trip trip,
                          @RequestParam(value = "categoriaId", required = false) Integer categoriaId,
                          RedirectAttributes redirectAttributes) {

        // Asignamos la categoría al trip si viene seleccionada
        if (categoriaId != null && categoriaId > 0) {
            Categoria cat = categoriaService.buscarPorId(categoriaId);
            trip.setCategoria(cat);
        }

        tripService.guardar(trip);
        redirectAttributes.addFlashAttribute("mensaje", "Trip guardado correctamente ✓");
        return "redirect:/listado";
    }

    // Formulario editar
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable int id, Model model) {
        Trip trip = tripService.buscarPorId(id);
        if (trip == null) return "redirect:/listado";
        model.addAttribute("trip", trip);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formTrip";
    }

    // Eliminar trip
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes redirectAttributes) {
        tripService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Trip eliminado correctamente");
        return "redirect:/listado";
    }

    // Ver detalle
    @GetMapping("/view/{id}")
    public String ver(@PathVariable int id, Model model) {
        Trip trip = tripService.buscarPorId(id);
        if (trip == null) return "redirect:/listado";
        model.addAttribute("trip", trip);
        return "detalle";
    }

    // Query Method 1: por estatus
    @GetMapping("/porEstatus")
    public String porEstatus(@RequestParam String estatus, Model model) {
        model.addAttribute("trips", tripService.buscarPorEstatus(estatus));
        model.addAttribute("titulo", "Trips con estatus: " + estatus);
        return "listado";
    }

    // Query Method 2: entre costos
    @GetMapping("/entreCosto")
    public String entreCosto(@RequestParam double min,
                             @RequestParam double max,
                             Model model) {
        model.addAttribute("trips", tripService.buscarEntreCosto(min, max));
        model.addAttribute("titulo", "Trips entre $" + min + " y $" + max);
        return "listado";
    }

    // Query Method 3: destacados activos
    @GetMapping("/destacados")
    public String destacados(Model model) {
        model.addAttribute("trips", tripService.buscarDestacadosActivos());
        model.addAttribute("titulo", "Trips Destacados");
        return "listado";
    }
}