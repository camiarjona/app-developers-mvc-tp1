package org.example;

import org.example.mvc.controller.worker.WorkerController;
import org.example.mvc.exceptions.DuplicateWorkerException;
import org.example.mvc.model.Designer;
import org.example.mvc.model.Developer;
import org.example.mvc.model.repositories.WorkerRepository;
import org.example.mvc.view.WorkerView;

public class Main {
    public static void main(String[] args) {
        Developer developer = new Developer("Camila", "Arjona", 1234L, "Java");
        Designer designer = new Designer("Nico", "Ros", 5678L, "Photoshop");

        WorkerRepository repository = new WorkerRepository();

        try {
            repository.save(developer);
            repository.save(designer);

        } catch (DuplicateWorkerException ex) {
            System.out.println(ex.getMessage());
        }

        WorkerController controller = new WorkerController(repository);
        WorkerView view = new WorkerView(controller);

        view.printMenuView();



    }

}