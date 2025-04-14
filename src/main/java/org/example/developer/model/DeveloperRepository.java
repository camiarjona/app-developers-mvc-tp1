package org.example.developer.model;

import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperRepository {

    private final List<Developer> developers;

    public DeveloperRepository() {
        this.developers = new ArrayList<>();
    }

    public void save(Developer developer) throws DeveloperException {
        if(!developers.contains(developer) && developer != null) {
            developers.add(developer);
        }
        else {
            throw new DeveloperException("El desarrollador ya se encuentra registrado.");
        }
    }

    public List<Developer> findAll() throws DeveloperException {
        if(developers.isEmpty()){
            throw new DeveloperException("La lista esta vacía");
        }
        return developers;
    }

    public Optional<Developer> findByDNI(Long dni) {
        return developers.stream()
                .filter(developer -> developer.getDni().equals(dni))
                .findFirst();
    }

    public void delete(Long dni) throws DeveloperNotFoundException {
        Optional<Developer> op = findByDNI(dni);
        if (op.isPresent()) {
            developers.remove(op.get());
        }
        else{
            throw new DeveloperNotFoundException("El desarrollador no se encuentra en la lista.");
        }
    }

    public Developer getByDni(Long dni) throws DeveloperNotFoundException {
        Optional<Developer> op = findByDNI(dni);
        if (op.isPresent()) {
            return op.get();
        }
        else {
            throw new DeveloperNotFoundException("El desarrollador solicitado no existe.");
        }
    }



}
