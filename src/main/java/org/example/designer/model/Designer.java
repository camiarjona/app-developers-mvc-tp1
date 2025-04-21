package org.example.designer.model;

import org.example.project.model.Project;
import org.example.base.Worker;

public class Designer extends Worker {
    private String specialty;
    private Project project;

    public Designer() {
        super();
    }

    public Designer(String name, String surname, Integer dni, Integer age, String specialty, Project proyect) {
        super(name, surname, dni, age);
        this.specialty = specialty;
        this.project = proyect;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Diseñador" + super.toString() + "\nEspecialidad: " + specialty
                + "\n" + (project != null ? project.toString() : "Proyecto sin asignar");
    }


}
