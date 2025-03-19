package org.example.mvc.model.repositories;

import org.example.mvc.exceptions.DuplicateWorkerException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.exceptions.ValidateDataException;
import org.example.mvc.exceptions.WorkerNotFoundException;
import org.example.mvc.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerRepository {

    private List<Worker> workers;

    public WorkerRepository() {
        this.workers = new ArrayList<>();
    }

    public void save(Worker worker) throws DuplicateWorkerException{
        if(!workers.contains(worker) && worker !=null) {
            workers.add(worker);
        }
        else{
            throw new DuplicateWorkerException("El trabajador ya existe");
        }
    }

    public List<Worker> findAll() throws EmptyListException {
        if(workers.isEmpty()){
            throw new EmptyListException("La lista está vacía.");
        }
        return workers;
    }

    public Worker findByDNI(Long dni) throws WorkerNotFoundException {
        Worker worker  = null;
        for (Worker value : workers) {
            if (dni.equals(value.getDni())) {
                worker = value;
            }
        }

        if (worker == null) {
            throw new WorkerNotFoundException("El trabajador ingresado no existe");
        }
        return worker;
    }

    public void delete(Long dni) throws WorkerNotFoundException {
        Worker worker = findByDNI(dni);
        workers.remove(worker);
    }



}
