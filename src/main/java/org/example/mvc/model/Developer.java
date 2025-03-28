package org.example.mvc.model;

public class Developer extends Worker {

    private String mainLanguage;
    private Proyect proyect;

    public Developer(String name, String surname, Long dni, Integer age, String mainLanguage, Proyect proyect) {
        super(name, surname, dni, age);
        this.mainLanguage = mainLanguage;
        this.proyect = proyect;
    }

    @Override
    public String working() {
        return name + " está prgramando en " + mainLanguage;
    }

    @Override
    public String resting() {
        return name + " se está tomando un descanso...";
    }


    @Override
    public String toString() {
        return "Desarrollador" + super.toString() + "\nLenguaje principal: " + mainLanguage;
    }

    @Override
    public void updateSpecificAttribute(String newValue) {
        this.mainLanguage = newValue;

    }

    @Override
    public String getSpecificAttribute() {
        return "Lenguaje actual: " + this.mainLanguage;
    }

}
