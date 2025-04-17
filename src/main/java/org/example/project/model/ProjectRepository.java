package org.example.project.model;

import org.example.designer.model.Designer;
import org.example.developer.model.Developer;
import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.persistence.ProjectDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectRepository {

    private final List<Project> projects;
    private final ProjectDAO dao = new ProjectDAO();

    public ProjectRepository() throws SQLException {
        this.projects = new ArrayList<>();
        loadAllProjectsFromDatabase();

    }

    private void loadAllProjectsFromDatabase() throws SQLException {
        try {
            List<Project> dbProjects = dao.getProjects();
            projects.addAll(dbProjects);
        } catch (SQLException e) {
            System.err.println("Error al cargar proyectos iniciales: " + e.getMessage());
            throw e;
        }
    }

    public void save(Project project) throws ProjectException, SQLException {
        if (!projects.contains(project) && project != null) {
            projects.add(project);
            dao.add(project);
        } else {
            throw new ProjectException("El proyecto ya se encuentra cargado.");
        }
    }

    public List<Project> findAll() throws ProjectException {
        if (projects.isEmpty()) {
            throw new ProjectException("La lista de proyectos esta vacía.");
        }
        return projects;
    }

    public Optional<Project> findById(int id) {
        return projects.stream()
                .filter(project -> project.getId() == id)
                .findFirst();
    }

    public void delete(int id) throws ProjectNotFoundException, SQLException {
        Optional<Project> op = findById(id);
        if (op.isPresent()) {
            projects.remove(op.get());
            dao.deleteProject(op.get());
        } else {
            throw new ProjectNotFoundException("El proyecto no se encuentra en la lista.");
        }
    }

    public Project getById(int id) throws ProjectNotFoundException {
        Optional<Project> op = findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new ProjectNotFoundException("El proyecto solicitado no existe.");
        }
    }

    public void updateName(String name, Project project) throws ProjectException, SQLException {
        project.setProyectName(name);
        dao.updateName(project);
    }

    public void updateDescription(String description, Project project) throws ProjectException, SQLException {
        project.setDescription(description);
        dao.updateDescription(project);
    }

    public List<Developer> getDevelopersAssignedToProject(int projectId, List<Developer> developers) {
        return developers.stream()
                .filter(developer -> developer.getProject().getId() == projectId)
                .toList();
    }

    public List<Designer> getDesignersAssignedToProject(int projectId, List<Designer> designers) {
        return designers.stream()
                .filter(designer -> designer.getProject().getId() == projectId)
                .toList();
    }

}
