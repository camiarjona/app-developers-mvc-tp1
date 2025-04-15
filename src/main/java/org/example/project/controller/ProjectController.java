package org.example.project.controller;

import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.factory.ProjectFactory;
import org.example.project.model.Project;
import org.example.project.model.ProjectRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository proyectRepository) {
        this.projectRepository = proyectRepository;
    }

    public void save(String name, String description) throws ProjectException, ProjectNotFoundException, SQLException {
        validateString(name, "El nombre no puede estar vacío.");
        validateString(description, "La descripción no puede estar vacía.");

        Project project = ProjectFactory.createProject(name, description);

        projectRepository.save(project);
    }

    public Optional<Project> findById(int id){
        return projectRepository.findById(id);
    }

    public void delete (int id) throws ProjectNotFoundException, SQLException {
        projectRepository.delete(id);
    }

    public void validateString(String input, String message) throws ProjectException {
        if(input == null || input.isEmpty()){
            throw new ProjectException(message);
        }
    }

    public void updateNameProject(String name, Project project) throws ProjectException, SQLException {
        projectRepository.updateName(name, project);
    }

    public void updateDescriptionProject(String description, Project project) throws ProjectException, SQLException {
        projectRepository.updateDescription(description, project);
    }

    public Project getById(int id) throws ProjectNotFoundException {
        return projectRepository.getById(id);
    }

    public List<Project> getAll() throws ProjectException {
       return projectRepository.findAll();
    }

}
