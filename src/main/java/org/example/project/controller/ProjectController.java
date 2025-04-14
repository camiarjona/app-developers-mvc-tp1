package org.example.project.controller;

import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.factory.ProjectFactory;
import org.example.project.model.Project;
import org.example.project.model.ProjectRepository;

import java.util.List;
import java.util.Optional;

public class ProjectController {

    private final ProjectRepository proyectRepository;

    public ProjectController(ProjectRepository proyectRepository) {
        this.proyectRepository = proyectRepository;
    }

    public void save(String name, String description) throws ProjectException, ProjectNotFoundException {
        validateString(name, "El nombre no puede estar vacío.");
        validateString(description, "La descripción no puede estar vacía.");

        Project project = ProjectFactory.createProject(name, description);

        proyectRepository.save(project);
    }

    public Optional<Project> findById(int id){
        return proyectRepository.findById(id);
    }

    public void delete (int id) throws ProjectNotFoundException {
        proyectRepository.delete(id);
    }

    public void validateString(String input, String message) throws ProjectException {
        if(input == null || input.isEmpty()){
            throw new ProjectException(message);
        }
    }

    public void updateNameProject(String name, Project project){
        project.setProyectName(name);
    }

    public void updateDescriptionProject(String description, Project project){
        project.setDescription(description);
    }

    public Project getById(int id) throws ProjectNotFoundException {
        return proyectRepository.getById(id);
    }

    public List<Project> getAll() throws ProjectException {
       return proyectRepository.findAll();
    }

}
