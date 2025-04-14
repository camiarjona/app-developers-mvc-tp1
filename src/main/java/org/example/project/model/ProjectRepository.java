package org.example.project.model;

import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository {

    private final List<Project> proyects;

    public ProjectRepository() {
        this.proyects = new ArrayList<>();
    }

    public void save(Project proyect) throws ProjectException {
        if (!proyects.contains(proyect) && proyect != null) {
            proyects.add(proyect);
        } else {
            throw new ProjectException("El proyecto ya se encuentra cargado.");
        }
    }

    public List<Project> findAll() throws ProjectException {
        if (proyects.isEmpty()) {
            throw new ProjectException("La lista de proyectos esta vacía.");
        }
        return proyects;
    }

    public Optional<Project> findById(int id) {
        return proyects.stream()
                .filter(proyect -> proyect.getId() == id)
                .findFirst();
    }

    public void delete(int id) throws ProjectNotFoundException {
        Optional<Project> op = findById(id);
        if (op.isPresent()) {
            proyects.remove(op.get());
        }
        else{
            throw new ProjectNotFoundException("El proyecto no se encuentra en la lista.");
        }
    }

    public Project getById(int id) throws ProjectNotFoundException {
        Optional<Project> op = findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        else {
            throw new ProjectNotFoundException("El proyecto solicitado no existe.");
        }
    }

}
