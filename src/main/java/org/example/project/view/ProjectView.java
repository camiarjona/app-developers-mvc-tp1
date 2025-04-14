package org.example.project.view;

import org.example.base.interfaces.IValidateInput;
import org.example.project.controller.ProjectController;
import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.model.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectView implements IValidateInput {

    private final ProjectController proyectController;
    private final Scanner sc;

    public ProjectView(ProjectController proyectController) {
        this.proyectController = proyectController;
        this.sc = new Scanner(System.in);
    }

    public void projectMenu(){
        int opcion;
        do{
            printProjectMenu();
            opcion = readInt(sc, "\uD83D\uDC49 Ingrese una opción: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Agregar proyecto⤵️");
                    addProjectView();
                }
                case 2 -> {
                    System.out.println("Modificar proyecto⤵️");
                    updateProjectView();
                }
                case 3 -> {
                    System.out.println("Eliminar proyecto ️⤵️");
                    deleteProjectView();
                }
                case 4 -> {
                    System.out.println("Lista de proyectos⤵️");
                    getAllView();
                }
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
        }while (opcion != 5);

    }
    private void printProjectMenu(){
        System.out.println("\uD83D\uDCA1 Proyecto \uD83D\uDCA1");
        System.out.println("1️⃣ Agregar Proyecto");
        System.out.println("2️⃣ Modificar Proyecto");
        System.out.println("3️⃣ Eliminar Proyecto");
        System.out.println("4️⃣ Mostrar proyectos");
        System.out.println("5️⃣ Volver al menú principal⤴️");
    }

    private void addProjectView(){
        String name = readString(sc, "\uD83D\uDD39 Ingrese el nombre del proyecto: ");
        String description = readString(sc, "\uD83D\uDD39 Ingrese el descripción del proyecto: ");

        try{
            proyectController.save(name, description);
            System.out.println("✅Proyecto agregado con éxito.");
        } catch (ProjectException | ProjectNotFoundException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void deleteProjectView(){
        int id = readInt(sc, "\uD83D\uDD39 Ingrese el id del proyecto: ");

        try{
            proyectController.delete(id);
            System.out.println("✅Proyecto eliminado con éxito.");
        } catch (ProjectNotFoundException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void updateProjectView(){
        int id = readInt(sc, "\uD83D\uDD39 Ingrese el id del proyecto: ");
        sc.nextLine();

        try{
            Project project = proyectController.getById(id);
            System.out.println("Proyecto encontrado: " + project.toString());
            printUpdateMenu();
            int opcion = readInt(sc, "\uD83D\uDC49 Ingrese una opción: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    String newName = readString(sc, "\uD83D\uDD39 Ingrese el nuevo nombre: ");
                    proyectController.updateNameProject(newName, project);
                }
                case 2 -> {
                    String newDescription = readString(sc, "\uD83D\uDD39 Ingrese la nueva descripción: ");
                    proyectController.updateDescriptionProject(newDescription, project);
                }
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
            System.out.println("✅Proyecto modificado con éxito.");
            System.out.println(project.toString());

        } catch (ProjectNotFoundException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void printUpdateMenu(){
        System.out.println("\uD83D\uDD39 ¿Que atributo desea modificar?");
        System.out.println("1️⃣ Nombre");
        System.out.println("2️⃣ Descripción");
    }

    private void getAllView(){
        try{
            List<Project> lista = proyectController.getAll();
            lista.forEach(System.out::println);
        } catch (ProjectException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }
}
