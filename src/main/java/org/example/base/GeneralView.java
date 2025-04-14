package org.example.base;

import org.example.base.interfaces.IValidateInput;
import org.example.designer.view.DesignerView;
import org.example.developer.view.DeveloperView;
import org.example.project.view.ProjectView;

import java.util.Scanner;

public class GeneralView implements IValidateInput {

    private final DeveloperView developerView;
    private final DesignerView designerView;
    private final ProjectView projectView;
    private final Scanner sc;

    public GeneralView(DeveloperView developerView, DesignerView designerView, ProjectView projectView) {
        this.developerView = developerView;
        this.designerView = designerView;
        this.projectView = projectView;
        this.sc = new Scanner(System.in);
    }

    public void generalMenu(){
        int option;
        do{
            printMenu();
            option = readInt(sc, "\uD83D\uDC49 Seleccione una opcion: ");
            sc.nextLine();

            switch (option) {
                case 1 -> developerView.developerMenu();
                case 2 -> designerView.designerMenu();
                case 3 -> projectView.projectMenu();
                case 4 -> System.out.println("Saliendo...\uD83D\uDC4B");
                default -> System.out.println("⛔Opción inválida. Intente nuevamente.");
            }
        }while(option != 4);
        sc.close();
    }

    private void printMenu(){
        System.out.println("\uD83D\uDCA1 MENÚ PRINCIPAL \uD83D\uDCA1");
        System.out.println("1️⃣ Menú desarrolladores");
        System.out.println("2️⃣ Menú diseñadores");
        System.out.println("3️⃣ Menú proyectos");
        System.out.println("4️⃣ Salir");
    }

}
