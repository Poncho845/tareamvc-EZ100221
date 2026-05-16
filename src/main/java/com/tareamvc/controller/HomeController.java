package com.tareamvc.controller;

import com.tareamvc.model.Trip;
import com.tareamvc.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ITripService tripService;

    @GetMapping("/")
    public String home(Model model) {
        List<Trip> trips = tripService.listarTodos();
        model.addAttribute("trips", trips);
        model.addAttribute("mensaje", "¡Bienvenido a Trips en El Salvador!");
        model.addAttribute("fecha", new Date());
        return "home";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Trip> trips = tripService.listarTodos();
        model.addAttribute("trips", trips);
        return "listado";
    }
}