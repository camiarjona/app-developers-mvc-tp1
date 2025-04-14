package org.example.developer.controller;

import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;
import org.example.developer.factory.DeveloperFactory;
import org.example.developer.model.Developer;
import org.example.developer.model.DeveloperRepository;
import org.example.project.model.Project;

import java.util.List;
import java.util.Optional;

public class DeveloperController {

    private final DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public void save(String name, String surname, Long dni, Integer age, String mainLanguage, Project project) throws DeveloperException {
        validateString(name, "El nombre no puede estar vacío.");
        validateString(surname, "El apellido no puede estar vacío.");
        validateString(mainLanguage, "El lenguaje principal no puede quedar vacío.");
        validateAge(age, "El desarrollador debe ser mayor de edad.");
        validateDni(dni, "El dni no puede estar vacío o ser menor o igual a cero.");

        Developer developer = DeveloperFactory.createDeveloper(name, surname, dni, age, mainLanguage, project);

        developerRepository.save(developer);

    }

    public Optional<Developer> findByDni(Long dni){
        return developerRepository.findByDNI(dni);
    }

    public void delete (Long dni) throws DeveloperNotFoundException {
      developerRepository.delete(dni);
    }

    public void validateString(String input, String message) throws DeveloperException {
        if(input == null || input.isEmpty()){
            throw new DeveloperException(message);
        }
    }

    public void validateDni(Long input, String message) throws DeveloperException {
        if(input == null || input <= 0){
            throw new DeveloperException(message);
        }
    }

    public void validateAge(Integer input, String message) throws DeveloperException {
        if(input == null || input <= 0 || input < 18){
            throw new DeveloperException(message);
        }
    }

    public void updateNameDeveloper(String name, Developer developer){
        developer.setName(name);
    }

   public void updateSurnameDeveloper(String surname, Developer developer){
        developer.setSurname(surname);
   }

   public void updateAgeDeveloper(Integer age, Developer developer){
        developer.setAge(age);
   }

   public void updateMainLanguageDeveloper(String mainLanguage, Developer developer){
        developer.setMainLanguage(mainLanguage);
   }

   public void updateProjectDeveloper(Project project, Developer developer){
        developer.setProject(project);
   }

    public Developer getByDni(Long dni) throws DeveloperNotFoundException {
        return developerRepository.getByDni(dni);
    }

    public List<Developer> getAll() throws DeveloperException {
        return developerRepository.findAll();
    }
}
