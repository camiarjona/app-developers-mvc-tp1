package org.example.mvc.model;

public class WorkerFactory {
    public static Worker createWorker(String type, String name, String surname, Long dni, Integer age, String value, Proyect proyect) {
        return switch (type.toLowerCase()) {
            case "designer" -> new Designer(name, surname, dni, age, value, proyect);
            case "developer" -> new Developer(name, surname, dni, age, value, proyect);
            default -> throw new IllegalArgumentException("Tipo de trabajador inválido");
        };
    }
}
