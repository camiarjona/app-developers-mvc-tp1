package org.example.mvc.model;

import java.util.Objects;

public abstract class Person {
    protected String name;
    protected String surname;
    protected Long dni;

    public Person() {
    }

    public Person(String name, String surname, Long dni) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getDni() {
        return dni;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(dni, person.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return "\nNombre: " + name + "\nApellido: " + surname + "\nDNI: " + dni;
    }
}

