package org.kaven.Cafeteria.web.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones(CRUD) para todos los estudiantes")
public class EstudianteController {
    //Controller
}
