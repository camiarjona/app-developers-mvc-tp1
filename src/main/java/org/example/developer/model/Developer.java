package org.example.developer.model;

import org.example.project.model.Project;
import org.example.base.Worker;

public class Developer extends Worker {

    private String mainLanguage;
    private Project project;

    public Developer(String name, String surname, Long dni, Integer age, String mainLanguage, Project project) {
        super(name, surname, dni, age);
        this.mainLanguage = mainLanguage;
        this.project = project;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public void setMainLanguage(String mainLanguage) {
        this.mainLanguage = mainLanguage;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Desarrollador" + super.toString() + "\nLenguaje principal: "
                + mainLanguage + "\n" + project.toString();
    }


}
