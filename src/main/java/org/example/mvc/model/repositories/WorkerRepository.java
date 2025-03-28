package org.example.mvc.model.repositories;

import org.example.mvc.exceptions.DuplicateElementException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.exceptions.ElementNotFoundException;
import org.example.mvc.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerRepository {

    private List<Worker> workers;

    public WorkerRepository() {
        this.workers = new ArrayList<>();
    }

    public void save(Worker worker) throws DuplicateElementException {
        if(!workers.contains(worker) && worker !=null) {
            workers.add(worker);
        }
        else{
            throw new DuplicateElementException("El trabajador ya existe");
        }
    }

    public List<Worker> findAll() throws EmptyListException {
        if(workers.isEmpty()){
            throw new EmptyListException("La lista está vacía.");
        }
        return workers;
    }

    public Worker findByDNI(Long dni) throws ElementNotFoundException {
        Worker worker  = null;
        for (Worker value : workers) {
            if (dni.equals(value.getDni())) {
                worker = value;
            }
        }

        if (worker == null) {
            throw new ElementNotFoundException("El trabajador ingresado no existe");
        }
        return worker;
    }

    public void delete(Long dni) throws ElementNotFoundException {
        Worker worker = findByDNI(dni);
        workers.remove(worker);
    }



}
