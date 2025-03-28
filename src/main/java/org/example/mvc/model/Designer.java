package org.example.mvc.model;

public class Designer extends Worker {
    private String specialty;
    private Proyect proyect;

    public Designer(String name, String surname, Long dni, Integer age, String specialty, Proyect proyect) {
        super(name, surname, dni, age);
        this.specialty = specialty;
        this.proyect = proyect;
    }

    @Override
    public String working() {
        return name + " está diseñando en " + specialty;
    }

    @Override
    public String resting() {
        return name + " está descansando...";
    }

    @Override
    public String toString() {
        return "Diseñador" + super.toString() + "\nEspecialidad: " + specialty;
    }

    @Override
    public void updateSpecificAttribute(String newValue) {
        this.specialty = newValue;
    }

    @Override
    public String getSpecificAttribute() {
        return "Especialidad actual: " + this.specialty;
    }


}
