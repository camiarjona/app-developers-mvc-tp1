package org.example.designer.view;

import org.example.base.interfaces.IValidateInput;
import org.example.designer.controller.DesignerController;
import org.example.designer.exceptions.DesignerException;
import org.example.designer.exceptions.DesignerNotFoundException;
import org.example.designer.model.Designer;
import org.example.project.controller.ProjectController;
import org.example.project.exceptions.ProjectException;
import org.example.project.exceptions.ProjectNotFoundException;
import org.example.project.model.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DesignerView implements IValidateInput {

    private final DesignerController designerController;
    private final ProjectController projectController;
    private final Scanner sc;

    public DesignerView(DesignerController designerController, ProjectController proyectController) {
        this.designerController = designerController;
        this.projectController = proyectController;
        this.sc = new Scanner(System.in);
    }

    public void designerMenu() {
        int opcion;
        do {
            printDesginerMenu();
            opcion = readInt(sc, "\uD83D\uDC49 Ingrese una opción: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Agregar diseñador⤵️");
                    addDesignerView();
                }
                case 2 -> {
                    System.out.println("Modificar diseñador⤵️");
                    updateDesignerView();
                }
                case 3 -> {
                    System.out.println("Eliminar diseñador ️⤵️");
                    deleteDesignerView();
                }
                case 4 -> {
                    System.out.println("Lista de diseñadores⤵️");
                    getAllView();
                }
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);

    }

    private void printDesginerMenu() {
        System.out.println("\uD83D\uDCA1 Diseñador \uD83D\uDCA1");
        System.out.println("1️⃣ Agregar Diseñador");
        System.out.println("2️⃣ Modificar Diseñador");
        System.out.println("3️⃣ Eliminar Diseñador");
        System.out.println("4️⃣ Mostrar diseñadores");
        System.out.println("5️⃣ Volver al menú principal⤴️");
    }

    private void addDesignerView() {

        try {
            projectController.getAll();

        } catch (ProjectException e) {
            System.out.println("⚠️Advertencia⚠️: NO hay proyectos disponibles en este momento.");
        }

        String name = readString(sc, "\uD83D\uDD39 Ingrese el nombre del diseñador: ");
        String surname = readString(sc, "\uD83D\uDD39 Ingrese el apellido del diseñador: ");
        Integer dni = readInt(sc, "Ingrese el dni del diseñador: ");
        Integer age = readInt(sc, "Ingrese el edad del diseñador: ");
        sc.nextLine();
        String specialty = readString(sc, "Ingrese la especialidad del diseñador: ");
        int id = readInt(sc, "Ingrese el id del proyecto a asignar (o presione 0 para ninguno): ");
        sc.nextLine();
        Project project = null;

        if (id != 0) {
            try {
                project = projectController.getById(id);
            } catch (ProjectNotFoundException e) {
                System.out.println("⛔Error: " + e.getMessage());
            }
        }

        try {
            designerController.save(name, surname, dni, age, specialty, project);
            System.out.println("✅Diseñador agregado con éxito.");
        } catch (DesignerException | SQLException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }

    }

    private void deleteDesignerView() {
        Integer dni = readInt(sc, "\uD83D\uDD39 Ingrese el dni del diseñador: ");

        try {
            designerController.delete(dni);
            System.out.println("✅Diseñador eliminado con éxito.");
        } catch (DesignerNotFoundException | SQLException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void updateDesignerView() {
        Integer dni = readInt(sc, "\uD83D\uDD39 Ingrese el dni del diseñador: ");
        sc.nextLine();

        try {
            Designer designer = designerController.getByDni(dni);
            System.out.println("Diseñador encontrado: " + designer.toString());
            printUpdateMenu();
            int opcion = readInt(sc, "\uD83D\uDC49 Ingrese una opción: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    String newName = readString(sc, "\uD83D\uDD39 Ingrese el nuevo nombre: ");
                    designerController.updateNameDesginer(newName, designer);
                }
                case 2 -> {
                    String newSurname = readString(sc, "\uD83D\uDD39 Ingrese el nuevo apellido: ");
                    designerController.updateSurnameDesigner(newSurname, designer);
                }
                case 3 -> {
                    Integer newAge = readInt(sc, "\uD83D\uDD39 Ingrese la nueva edad: ");
                    designerController.updateAgeDesigner(newAge, designer);
                }
                case 4 -> {
                    String specialty = readString(sc, "\uD83D\uDD39 Ingrese la nueva especialidad: ");
                    designerController.updateSpecialtyDesigner(specialty, designer);
                }
                case 5 -> {
                    int id = readInt(sc, "\uD83D\uDD39 Ingrese el id del proyecto a asignar: ");
                    try {
                        Project project = projectController.getById(id);
                        designerController.updateProjectDesigner(project, designer);
                    } catch (ProjectNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
            System.out.println("✅Diseñador modificado con éxito.");
            System.out.println(designer.toString());

        } catch (DesignerNotFoundException e) {
            System.out.println("⛔Error: " + e.getMessage());
        }
    }

    private void printUpdateMenu() {
        System.out.println("\uD83D\uDD39 ¿Que atributo desea modificar?");
        System.out.println("1️⃣ Nombre");
        System.out.println("2️⃣ Apellido");
        System.out.println("3️⃣ Edad");
        System.out.println("4️⃣ Especialidad");
        System.out.println("5️⃣ Asignar un nuevo proyecto");
    }

    private void getAllView() {

        List<Designer> allDesigners = designerController.getAll();

        if (allDesigners.isEmpty()) {
            System.out.println("🚫 La lista de diseñadores está vacía.");
        } else {
            allDesigners.forEach(System.out::println);
        }

    }
}
