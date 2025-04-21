package org.example.designer.model;

import org.example.designer.exceptions.DesignerException;
import org.example.designer.exceptions.DesignerNotFoundException;
import org.example.designer.persistence.DesignerDAO;
import org.example.developer.model.Developer;
import org.example.project.model.Project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DesignerRepository {

    private final List<Designer> designers;
    private final DesignerDAO dao = new DesignerDAO();

    public DesignerRepository() throws SQLException {
        this.designers = new ArrayList<>();
        loadAllDesignersFromDatabase();
    }

    private void loadAllDesignersFromDatabase() throws SQLException {
        try {
            List<Designer> dbDes = dao.getDesigners();
            designers.addAll(dbDes);
        } catch (SQLException e) {
            System.err.println("Error al cargar desarrolladores iniciales: " + e.getMessage());
            throw e;
        }
    }

    public void save(Designer designer) throws DesignerException, SQLException {
        if (designer != null && !designers.contains(designer)) {
            designers.add(designer);
            dao.add(designer, designer.getProject());
        } else {
            throw new DesignerException("El diseñador ya se encuentra registrado.");
        }
    }

    public List<Designer> findAll() {
        return designers;
    }

    public Optional<Designer> findByDNI(Integer dni) {
        return designers.stream()
                .filter(designer -> designer.getDni().equals(dni))
                .findFirst();
    }

    public void delete(Integer dni) throws DesignerNotFoundException, SQLException {
        Optional<Designer> op = findByDNI(dni);
        if (op.isPresent()) {
            designers.remove(op.get());
            dao.deleteDesigner(op.get());
        } else {
            throw new DesignerNotFoundException("El diseñador no se encuentra en la lista.");
        }
    }

    public Designer getByDni(Integer dni) throws DesignerNotFoundException {
        Optional<Designer> op = findByDNI(dni);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new DesignerNotFoundException("El diseñador solicitado no existe.");
        }
    }

    public void updateName(String name, Designer designer) throws SQLException {
        designer.setName(name);
        dao.updateName(designer);
    }

    public void updateSurname(Designer designer, String surname) throws SQLException {
        designer.setSurname(surname);
        dao.updateSurname(designer);
    }

    public void updateAge(Designer designer, Integer age) throws SQLException {
        designer.setAge(age);
        dao.updateAge(designer);
    }

    public void updateSpecialty(Designer designer, String mainLanguage) throws SQLException {
        designer.setSpecialty(mainLanguage);
        dao.updateSpecialty(designer);
    }

    public void updateProject(Project project, Designer designer) throws SQLException {
        designer.setProject(project);
        dao.updateProject(designer, project);
    }


}
