package org.example.designer.controller;

import org.example.designer.exceptions.DesignerException;
import org.example.designer.exceptions.DesignerNotFoundException;
import org.example.designer.factory.DesignerFactory;
import org.example.designer.model.Designer;
import org.example.designer.model.DesignerRepository;
import org.example.project.model.Project;

import java.util.List;
import java.util.Optional;

public class DesignerController {

    private final DesignerRepository designerRepository;

    public DesignerController(DesignerRepository designerRepository) {
        this.designerRepository = designerRepository;
    }

    public void save(String name, String surname, Long dni, Integer age, String specialty, Project project) throws DesignerException {
        validateString(name, "El nombre no puede estar vacío.");
        validateString(surname, "El apellido no puede estar vacío.");
        validateString(specialty, "La especialidad no puede quedar vacía.");
        validateAge(age, "El diseñador debe ser mayor de edad.");
        validateDni(dni, "El dni no puede estar vacío o ser menor o igual a cero.");

        Designer designer = DesignerFactory.createDesigner(name, surname, dni, age, specialty, project);

        designerRepository.save(designer);
    }

    public Optional<Designer> findByDni(Long dni){
        return designerRepository.findByDNI(dni);
    }

    public void delete (Long dni) throws DesignerNotFoundException {
        designerRepository.delete(dni);
    }

    public void validateString(String input, String message) throws DesignerException {
        if(input == null || input.isEmpty()){
            throw new DesignerException(message);
        }
    }

    public void validateDni(Long input, String message) throws DesignerException {
        if(input == null || input <= 0){
            throw new DesignerException(message);
        }
    }

    public void validateAge(Integer input, String message) throws DesignerException {
        if(input == null || input <= 0 || input < 18){
            throw new DesignerException(message);
        }
    }

    public void updateNameDesginer(String name, Designer desginer){
        desginer.setName(name);
    }

    public void updateSurnameDesigner(String surname, Designer desginer){
        desginer.setSurname(surname);
    }

    public void updateAgeDesigner(Integer age, Designer desginer){
        desginer.setAge(age);
    }

    public void updateSpecialtyDesigner(String specialty, Designer desginer){
        desginer.setSpecialty(specialty);
    }

    public void updateProjectDesigner(Project project, Designer desginer){
        desginer.setProject(project);
    }

    public Designer getByDni(Long dni) throws DesignerNotFoundException {
        return designerRepository.getByDni(dni);
    }

    public List<Designer> getAll() throws DesignerException {
        return designerRepository.findAll();
    }
}
