package org.example.developer.controller;

import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;
import org.example.developer.factory.DeveloperFactory;
import org.example.developer.model.Developer;
import org.example.developer.model.DeveloperRepository;
import org.example.project.model.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DeveloperController {

    private final DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public void save(String name, String surname, Integer dni, Integer age, String mainLanguage, Project project) throws DeveloperException, SQLException {
        validateString(name, "El nombre no puede estar vacío.");
        validateString(surname, "El apellido no puede estar vacío.");
        validateString(mainLanguage, "El lenguaje principal no puede quedar vacío.");
        validateAge(age, "El desarrollador debe ser mayor de edad.");
        validateDni(dni, "El dni no puede estar vacío o ser menor o igual a cero.");

        Developer developer = DeveloperFactory.createDeveloper(name, surname, dni, age, mainLanguage, project);

        developerRepository.save(developer);

    }

    public Optional<Developer> findByDni(Integer dni){
        return developerRepository.findByDNI(dni);
    }

    public void delete (Integer dni) throws DeveloperNotFoundException, SQLException {
      developerRepository.delete(dni);
    }

    public void validateString(String input, String message) throws DeveloperException {
        if(input == null || input.isEmpty()){
            throw new DeveloperException(message);
        }
    }

    public void validateDni(Integer input, String message) throws DeveloperException {
        if(input == null || input <= 0){
            throw new DeveloperException(message);
        }
    }

    public void validateAge(Integer input, String message) throws DeveloperException {
        if(input == null || input <= 0 || input < 18){
            throw new DeveloperException(message);
        }
    }

    public void updateNameDeveloper(String name, Developer developer) throws SQLException {
        developerRepository.updateName(name, developer);
    }

   public void updateSurnameDeveloper(String surname, Developer developer) throws SQLException {
        developerRepository.updateSurname(developer, surname);
   }

   public void updateAgeDeveloper(Integer age, Developer developer) throws SQLException {
        developerRepository.updateAge(developer, age);
   }

   public void updateMainLanguageDeveloper(String mainLanguage, Developer developer) throws SQLException {
        developerRepository.updateMainLanguage(developer, mainLanguage);
   }

   public void updateProjectDeveloper(Project project, Developer developer) throws SQLException {
        developerRepository.updateProject(project, developer);
   }

    public Developer getByDni(Integer dni) throws DeveloperNotFoundException {
        return developerRepository.getByDni(dni);
    }

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }
}
