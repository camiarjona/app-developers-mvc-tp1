package org.example.mvc.model.repositories;

import org.example.mvc.exceptions.DuplicateElementException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperRepository {

    private List<Developer> developers;

    public DeveloperRepository() {
        this.developers = new ArrayList<>();
    }

    public void save(Developer developer) throws DuplicateElementException {
        if(!developers.contains(developer) && developer != null) {
            developers.add(developer);
        }
        else {
            throw new DuplicateElementException("El diseñador ya se encuentra registrado.");
        }
    }

    public List<Developer> findAll() throws EmptyListException {
        if(developers.isEmpty()){
            throw new EmptyListException("La lista esta vacia");
        }
        return developers;
    }

    public Optional<Developer> findByDNI(Long dni) {
        return developers.stream()
                .filter(developer -> developer.getDni().equals(dni))
                .findFirst();
    }



}
