package org.example.mvc.controller;

import org.example.mvc.exceptions.DuplicateElementException;
import org.example.mvc.exceptions.ValidateDataException;
import org.example.mvc.model.Proyect;
import org.example.mvc.model.repositories.ProyectRepository;

public class ProyectController {

    private final ProyectRepository proyectRepository;

    public ProyectController(ProyectRepository proyectRepository) {
        this.proyectRepository = proyectRepository;
    }

    public void saveProyect(String name, String description) throws DuplicateElementException, ValidateDataException {
        validateProyect(name);
        proyectRepository.save(new Proyect(name, description));
    }

    private void validateProyect(String name) throws ValidateDataException {
        if (name.trim().isEmpty()) {
            throw new ValidateDataException("El nombre no puede estar vacio");
        }
    }


}
