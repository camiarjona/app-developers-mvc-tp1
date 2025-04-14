package org.example.developer.factory;

import org.example.developer.model.Developer;
import org.example.project.model.Project;

public class DeveloperFactory {
    public static Developer createDeveloper(String name, String surname, Long dni, Integer age, String mainLanguage, Project proyect) {
        return new Developer(name, surname, dni, age, mainLanguage, proyect);
    }
}
