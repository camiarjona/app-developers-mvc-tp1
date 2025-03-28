package org.example.mvc.model.repositories;

import org.example.mvc.exceptions.DuplicateElementException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.model.Proyect;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProyectRepository {

    private List<Proyect> proyects;

    public ProyectRepository() {
        this.proyects = new ArrayList<>();
    }

    public void save(Proyect proyect) throws DuplicateElementException {
        if (!proyects.contains(proyect) && proyect != null) {
            proyects.add(proyect);
        } else {
            throw new DuplicateElementException("El proyecto ya se encuentra cargado.");
        }
    }

    public List<Proyect> findAll() throws EmptyListException {
        if (proyects.isEmpty()) {
            throw new EmptyListException("La lista esta vacia");
        }
        return proyects;
    }

    public Optional<Proyect> findByDni(String name) {
        return proyects.stream()
                .filter(proyect -> proyect.getProyectName().equalsIgnoreCase(name))
                .findFirst();
    }
}
