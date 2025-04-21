package org.example.developer.model;

import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;
import org.example.developer.persistence.DeveloperDAO;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.model.Project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperRepository {

    private final List<Developer> developers;
    private final DeveloperDAO dao = new DeveloperDAO();

    public DeveloperRepository() throws SQLException {
        this.developers = new ArrayList<>();
        loadAllDevelopersFromDatabase();
    }

    private void loadAllDevelopersFromDatabase() throws SQLException {
        try {
            List<Developer> dbDevs = dao.getDevelopers();
            developers.addAll(dbDevs);
        } catch (SQLException e) {
            System.err.println("Error al cargar desarrolladores iniciales: " + e.getMessage());
            throw e;
        }
    }

    public void save(Developer developer) throws DeveloperException, SQLException {
        if (!developers.contains(developer) && developer != null) {
            developers.add(developer);
            dao.add(developer, developer.getProject());
        } else {
            throw new DeveloperException("El desarrollador ya se encuentra registrado.");
        }
    }

    public List<Developer> findAll() {
        return developers;
    }

    public Optional<Developer> findByDNI(Integer dni) {
        return developers.stream()
                .filter(developer -> developer.getDni().equals(dni))
                .findFirst();
    }

    public void delete(Integer dni) throws DeveloperNotFoundException, SQLException {
        Optional<Developer> op = findByDNI(dni);
        if (op.isPresent()) {
            developers.remove(op.get());
            dao.deleteDeveloper(op.get());
        } else {
            throw new DeveloperNotFoundException("El desarrollador no se encuentra en la lista.");
        }
    }

    public Developer getByDni(Integer dni) throws DeveloperNotFoundException {
        Optional<Developer> op = findByDNI(dni);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new DeveloperNotFoundException("El desarrollador solicitado no existe.");
        }
    }

    public void updateName(String name, Developer dev) throws SQLException {
        dev.setName(name);
        dao.updateName(dev);
    }

    public void updateSurname(Developer dev, String surname) throws SQLException {
        dev.setSurname(surname);
        dao.updateSurname(dev);
    }

    public void updateAge(Developer dev, Integer age) throws SQLException {
        dev.setAge(age);
        dao.updateAge(dev);
    }

    public void updateMainLanguage(Developer dev, String mainLanguage) throws SQLException {
        dev.setMainLanguage(mainLanguage);
        dao.updateMainLanguage(dev);
    }

    public void updateProject(Project project, Developer dev) throws SQLException {
        dev.setProject(project);
        dao.updateProject(dev, project);
    }


}
