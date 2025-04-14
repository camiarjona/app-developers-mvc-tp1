package org.example.project.model;

import java.util.Objects;

public class Project {

    private static int ID_AUTOINCREMENT = 1;
    private int id;
    private String proyectName;
    private String description;

    public Project(String nameProyect, String description) {
        this.id = ID_AUTOINCREMENT++;
        this.proyectName = nameProyect;
        this.description = description;
    }

    public String getProyectName() {
        return proyectName;
    }

    public void setProyectName(String proyectName) {
        this.proyectName = proyectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Proyecto" +
                "\nId: " + id +
                "\nNombre: " + proyectName +
                "\nDescripción: " + description;
    }
}
