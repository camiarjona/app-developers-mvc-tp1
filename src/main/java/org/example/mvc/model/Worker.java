package org.example.mvc.model;

import org.example.mvc.interfaces.IWorker;

public abstract class Worker extends Person implements IWorker {

    protected static Integer ID_AUTOINCREMENT = 1;
    protected final Integer id;

    public Worker(String name, String surname, Long dni, Integer age) {
        super(name, surname, dni, age);
        this.id = ID_AUTOINCREMENT++;
    }

    @Override
    public String toString() {
        return  super.toString() + "\nId de trabajador: " + id;
    }

    public abstract void updateSpecificAttribute(String newValue);

    public abstract String getSpecificAttribute();

}
