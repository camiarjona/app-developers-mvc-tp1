package org.example.mvc.view;

import org.example.mvc.controller.worker.WorkerController;
import org.example.mvc.exceptions.DuplicateElementException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.exceptions.ValidateDataException;
import org.example.mvc.exceptions.ElementNotFoundException;
import org.example.mvc.model.Worker;

import java.util.Scanner;

public class WorkerView {

    private final WorkerController workerController;
    private final Scanner sc;


    public WorkerView(WorkerController workerController) {
        this.workerController = workerController;
        sc = new Scanner(System.in);
    }

    public void printMenuView() {
        int opcion;
        do {
            printPrincipalMenu();
            opcion = readInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    addWorkerView();
                    break;
                case 2:
                    deleteWorkerView();
                    break;
                case 3:
                    updateWorkerView();
                    break;
                case 4:
                    findAllView();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente");
            }

        } while (opcion != 5);

        sc.close();

    }

    //metodos crud
    private void addWorkerView() {
        System.out.println("Ingrese el nombre del trabajador: ");
        String nombre = readString();
        System.out.println("Ingrese el apellido: ");
        String apellido = readString();

        System.out.println("Ingrese el DNI: ");
        long dni = readLong();

        printWorkerMenu();
        int type = readInt();
        sc.nextLine();

        Worker worker = null;

        switch (type) {
            case 1:
                System.out.println("Ingrese el lenguaje principal del trabajador: ");
                String mainLanguage = readString();
                worker = DeveloperController.createDeveloper(nombre, apellido, dni, mainLanguage);
                break;
            case 2:
                System.out.println("Ingrese la especialidad del trabajador: ");
                String specialty = readString();
                worker = DesignerController.createDesigner(nombre, apellido, dni, specialty);
                break;
            default:
                System.out.println("Opcion no valida. Intente nuevamente");
                break;
        }
        try {
            workerController.saveWorkerController(worker);
            System.out.println("Trabajador agregado correctamente.");

        } catch (DuplicateElementException | ValidateDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteWorkerView() {
        System.out.println("Ingrese el dni del trabajador: ");
        Long dni = readLong();

        try {
            workerController.deleteWorkerController(dni);
            System.out.println("Trabajador eliminado correctamente.");
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateWorkerView() {
        System.out.println("Ingrese el dni del trabajador: ");
        Long dni = readLong();

        try {
            Worker worker = workerController.findByDNIController(dni);
            System.out.println("\nTrabajador encontrado.");

            printUpdateMenu();
            int opcion = readInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    String newName = readString();
                    workerController.updateNameController(worker, newName);

                    break;
                case 2:
                    System.out.println("Ingrese el nuevo apellido: ");
                    String newApellido = readString();
                    workerController.updateSurnameController(worker, newApellido);

                    break;
                case 3:
                    System.out.println(workerController.getSpecificAttributeController(worker));
                    System.out.println("\nIngrese el nuevo dato: ");
                    String newValue = readString();
                    workerController.updateSpecificAttributeController(worker, newValue);
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente");
                    break;
            }
            System.out.println("Trabajador modificado con exito.");
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findAllView() {
        try {
            workerController.findAllWorkerController().forEach(System.out::println);
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
    }


    //metodos para imprimir menues
    private void printPrincipalMenu() {
        System.out.println("\n---------------");
        System.out.println("MENU PRINCIPAL");
        System.out.println("---------------");
        System.out.println("1. Agregar trabajador");
        System.out.println("2. Eliminar trabajador por DNI");
        System.out.println("3. Modificar trabajador");
        System.out.println("4. Listar trabajadores");
        System.out.println("5. Salir");
        System.out.println("Seleccione una opcion: ");
    }

    private void printWorkerMenu() {
        System.out.println("1. Desarrollador");
        System.out.println("2. Diseñador");
        System.out.println("Seleccione el tipo de trabajador: ");
    }

    private void printUpdateMenu() {
        System.out.println("\nQue campo desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Atributo específico");
    }

    //metodos para validar las entradas
    private long readLong() {
        while (!sc.hasNextLong()) {
            System.out.println("Entrada inválida. Intente nuevamente.");
            sc.next();
        }
        return sc.nextLong();
    }

    private String readString() {
        String input;
        do {
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Entrada invalida, intente nuevamente.");
            }
        } while (input.isEmpty());
        return input;
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Intente nuevamente.");
            sc.next();
        }
        return sc.nextInt();
    }

}
