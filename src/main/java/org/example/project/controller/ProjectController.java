package org.example.project.controller;

import org.example.designer.controller.DesignerController;
import org.example.designer.exceptions.DesignerException;
import org.example.designer.model.Designer;
import org.example.developer.controller.DeveloperController;
import org.example.developer.exceptions.DeveloperException;
import org.example.developer.model.Developer;
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
    private final DeveloperController developerController;
    private final DesignerController designerController;

    public ProjectController(ProjectRepository proyectRepository, DeveloperController developerController, DesignerController designerController) {
        this.projectRepository = proyectRepository;
        this.developerController = developerController;
        this.designerController = designerController;
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

    public void delete (int id) throws ProjectNotFoundException, SQLException, DesignerException, DeveloperException {
        List<Developer> assignedDevs = getDevelopersAssignedToProject(id);
        List<Designer> assignedDes = getDesignersAssignedToProject(id);

        if (!assignedDevs.isEmpty()) {
            System.out.println("⚠️¡Advertencia!⚠️ Este proyecto tiene trabajadores asignados. Al eliminarlo, sus proyectos serán establecidos a NULL.");
        }

        assignedDevs.forEach(dev -> dev.setProject(null));
        assignedDes.forEach(des -> des.setProject(null));

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

    public List<Developer> getDevelopersAssignedToProject(int projectId) throws  DeveloperException {
        return projectRepository.getDevelopersAssignedToProject(projectId, developerController.getAll());
    }

    public List<Designer> getDesignersAssignedToProject(int projectId) throws DesignerException {
        return projectRepository.getDesignersAssignedToProject(projectId, designerController.getAll());
    }
}
