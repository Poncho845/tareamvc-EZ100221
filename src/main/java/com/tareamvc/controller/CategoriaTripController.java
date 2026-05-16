package com.tareamvc.controller;

import com.tareamvc.model.Categoria;
import com.tareamvc.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaTripController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "listCategoria";
    }

    @GetMapping("/new")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formCategoria";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            return "redirect:/categorias";
        }
        model.addAttribute("categoria", categoria);
        return "formCategoria";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }
}