package org.example.mvc.model;

import java.util.Objects;

public class Proyect {

    private String proyectName;
    private String description;

    public Proyect(String nameProyect, String description) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Proyect proyect = (Proyect) o;
        return Objects.equals(proyectName, proyect.proyectName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(proyectName);
    }
}
