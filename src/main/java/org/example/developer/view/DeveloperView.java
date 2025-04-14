package org.example.developer.view;

import org.example.base.interfaces.IValidateInput;
import org.example.developer.controller.DeveloperController;
import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;
import org.example.developer.model.Developer;
import org.example.project.controller.ProjectController;
import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.model.Project;

import java.util.List;
import java.util.Scanner;


public class DeveloperView implements IValidateInput {

    private final DeveloperController developerController;
    private final ProjectController projectController;
    private final Scanner sc;

    public DeveloperView(DeveloperController developerController, ProjectController proyectController) {
        this.developerController = developerController;
        this.projectController = proyectController;
        this.sc = new Scanner(System.in);
    }

    public void developerMenu() {
        int opcion;
        do {
            printDeveloperMenu();
            opcion = readInt(sc, "\uD83D\uDC49 Ingrese una opción: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Agregar desarrollador⤵️");
                    addDeveloperView();
                }
                case 2 -> {
                    System.out.println("Modificar desarrollador⤵️");
                    updateDeveloperView();
                }
                case 3 -> {
                    System.out.println("Eliminar desarrollador ️⤵️");
                    deleteDeveloperView();
                }
                case 4 -> {
                    System.out.println("Lista de desarrolladores⤵️");
                    getAllView();
                }
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void printDeveloperMenu() {
        System.out.println("\uD83D\uDCA1 Desarrollador \uD83D\uDCA1");
        System.out.println("1️⃣ Agregar Desarrollador");
        System.out.println("2️⃣ Modificar Desarrollador");
        System.out.println("3️⃣ Eliminar Desarrollador");
        System.out.println("4️⃣ Mostrar desarrolladores");
        System.out.println("5️⃣ Volver al menú principal⤴️");
    }

    private void addDeveloperView() {
        try{
            projectController.getAll();

        } catch (ProjectException e) {
            System.out.println("⛔Error: " + e.getMessage());
            return;
        }
        String name = readString(sc, "\uD83D\uDD39 Ingrese el nombre del desarrollador: ");
        String surname = readString(sc, "\uD83D\uDD39 Ingrese el apellido del desarrollador: ");
        Long dni = readLong(sc, "Ingrese el dni del desarrollador: ");
        Integer age = readInt(sc, "Ingrese el edad del desarrollador: ");
        sc.nextLine();
        String mainLanguage = readString(sc, "Ingrese el lenguaje principal del desarrollador: ");
        int id = readInt(sc, "Ingrese el id del proyecto a asignar: ");
        sc.nextLine();

        try {
            Project project = projectController.getById(id);
            developerController.save(name, surname, dni, age, mainLanguage, project);
            System.out.println("✅Desarrollador agregado con éxito.");
        } catch (ProjectNotFoundException | DeveloperException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void deleteDeveloperView() {
        Long dni = readLong(sc, "\uD83D\uDD39 Ingrese el dni del desarrollador: ");

        try {
            developerController.delete(dni);
            System.out.println("✅Desarrollador eliminado con éxito.");
        } catch (DeveloperNotFoundException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void updateDeveloperView() {
        Long dni = readLong(sc, "\uD83D\uDD39 Ingrese el dni del desarrollador: ");
        sc.nextLine();

        try {
            Developer developer = developerController.getByDni(dni);
            System.out.println("Desarrollador encontrado: " + developer.toString());
            printUpdateMenu();
            int opcion = readInt(sc, "\uD83D\uDC49 Ingrese una opción: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    String newName = readString(sc, "\uD83D\uDD39 Ingrese el nuevo nombre: ");
                    developerController.updateNameDeveloper(newName, developer);
                }
                case 2 -> {
                    String newSurname = readString(sc, "\uD83D\uDD39 Ingrese el nuevo apellido: ");
                    developerController.updateSurnameDeveloper(newSurname, developer);
                }
                case 3 -> {
                    Integer newAge = readInt(sc, "\uD83D\uDD39 Ingrese la nueva edad: ");
                    developerController.updateAgeDeveloper(newAge, developer);
                }
                case 4 -> {
                    String mainLanguage = readString(sc, "\uD83D\uDD39 Ingrese la nueva especialidad: ");
                    developerController.updateMainLanguageDeveloper(mainLanguage, developer);
                }
                case 5 -> {
                    int id = readInt(sc, "\uD83D\uDD39 Ingrese el id del proyecto a asignar: ");
                    try {
                        Project project = projectController.getById(id);
                        developerController.updateProjectDeveloper(project, developer);
                    } catch (ProjectNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
            System.out.println("✅Desarrollador modificado con éxito.");
            System.out.println(developer.toString());

        } catch (DeveloperNotFoundException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void printUpdateMenu() {
        System.out.println("\uD83D\uDD39 ¿Que atributo desea modificar?");
        System.out.println("1️⃣ Nombre");
        System.out.println("2️⃣ Apellido");
        System.out.println("3️⃣ Edad");
        System.out.println("4️⃣ Lenguaje principal");
        System.out.println("5️⃣ Asignar un nuevo proyecto");
    }

    private void getAllView() {
        try {
            List<Developer> lista = developerController.getAll();
            lista.forEach(System.out::println);
        } catch (DeveloperException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }
}
