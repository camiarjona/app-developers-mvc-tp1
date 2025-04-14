package org.example.designer.factory;

import org.example.designer.model.Designer;
import org.example.project.model.Project;

public class DesignerFactory {
    public static Designer createDesigner(String name, String surname, Long dni, Integer age, String specialty, Project proyect) {
        return new Designer(name, surname, dni, age, specialty, proyect);
    }
}
