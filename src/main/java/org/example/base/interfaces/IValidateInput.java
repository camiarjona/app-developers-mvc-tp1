package org.example.base.interfaces;

import java.util.Scanner;

public interface IValidateInput {

    //metodos para validar las entradas
    default long readLong(Scanner sc, String prompt) {
        System.out.println(prompt);
        while (!sc.hasNextLong()) {
            System.out.println("Entrada inválida. Intente nuevamente.");
            sc.next();
        }
        return sc.nextLong();
    }

    default String readString(Scanner sc, String prompt) {
        System.out.println(prompt);
        String input;
        do {
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Entrada invalida, intente nuevamente.");
            }
        } while (input.isEmpty());
        return input;
    }

    default int readInt(Scanner sc, String prompt) {
        System.out.println(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Intente nuevamente.");
            sc.next();
        }
        return sc.nextInt();
    }
}
