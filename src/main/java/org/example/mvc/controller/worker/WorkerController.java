package org.example.mvc.controller.worker;

import org.example.mvc.exceptions.DuplicateWorkerExcepcion;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.exceptions.WorkerNotFoundException;
import org.example.mvc.model.Worker;
import org.example.mvc.model.repositories.WorkerRepository;

import java.util.List;

public class WorkerController {

    private final WorkerRepository repository;

    public WorkerController(WorkerRepository repository) {
        this.repository = repository;
    }

    public void saveWorkerController(Worker worker) throws DuplicateWorkerExcepcion {
        repository.save(worker);
    }

    public void deleteWorkerController(Long dni) throws WorkerNotFoundException {
        repository.delete(dni);
    }

    public Worker findByDNIController(Long dni) throws WorkerNotFoundException {
        return repository.findByDNI(dni);
    }

    public void updateNameController(Worker worker, String newName) {
        worker.setName(newName);
    }

    public void updateSurnameController(Worker worker, String newSurname) {
        worker.setSurname(newSurname);
    }

    public List<Worker> findAllWorkerController() throws EmptyListException {
        return repository.findAll();
    }

}
