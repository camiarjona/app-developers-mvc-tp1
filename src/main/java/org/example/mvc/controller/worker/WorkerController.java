package org.example.mvc.controller.worker;

import org.example.mvc.exceptions.DuplicateWorkerException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.exceptions.ValidateDataException;
import org.example.mvc.exceptions.WorkerNotFoundException;
import org.example.mvc.model.Worker;
import org.example.mvc.model.repositories.WorkerRepository;

import java.util.List;

public class WorkerController {

    private final WorkerRepository repository;

    public WorkerController(WorkerRepository repository) {
        this.repository = repository;
    }

    public void saveWorkerController(Worker worker) throws DuplicateWorkerException, ValidateDataException {
        validateData(worker);
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

    public void updateSpecificAttributeController(Worker worker, String newSpecification) {
        worker.updateSpecificAttribute(newSpecification);
    }

    public List<Worker> findAllWorkerController() throws EmptyListException {
        return repository.findAll();
    }

    public String getSpecificAttributeController(Worker worker) {
        return worker.getSpecificAttribute();
    }

    private void validateData(Worker worker) throws ValidateDataException {
        if(String.valueOf(worker.getDni()).length() != 8){
            throw new ValidateDataException("El dni debe tener 8 dígitos.");
        }
        if(worker.getName()==null || worker.getName().trim().isEmpty() || worker.getName().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")){
            throw new ValidateDataException("El nombre no puede contener caracteres especiales o estar en blanco.");
        }
        if(worker.getSurname()==null || worker.getSurname().trim().isEmpty()){
            throw new ValidateDataException("El apellido no puede contener caracteres especiales o estar en blanco.");
        }

    }

}
