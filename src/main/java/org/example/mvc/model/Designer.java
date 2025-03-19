package org.example.mvc.model;

public class Designer extends Worker{
    private String specialty;

    public Designer(String name, String surname, Long dni, String specialty) {
        super(name, surname, dni);
        this.specialty = specialty;
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
