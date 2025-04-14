package org.example.project.factory;

import org.example.project.model.Project;

public class ProjectFactory {
    public static Project createProject(String nameProyect, String description) {
        return new Project(nameProyect, description);
    }
}
