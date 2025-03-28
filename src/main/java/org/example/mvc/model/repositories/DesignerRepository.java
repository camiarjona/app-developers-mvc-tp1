package org.example.mvc.model.repositories;

import org.example.mvc.exceptions.DuplicateElementException;
import org.example.mvc.exceptions.EmptyListException;
import org.example.mvc.model.Designer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DesignerRepository {

    private List<Designer> designers;

    public DesignerRepository() {
        this.designers = new ArrayList<>();
    }

    public void save(Designer designer) throws DuplicateElementException {
        if(designer != null && !designers.contains(designer)){
            designers.add(designer);
        }
        else{
            throw new DuplicateElementException("El diseñador ya se encuentra registrado.");
        }
    }

    public List<Designer> findAll() throws EmptyListException {
        if(designers.isEmpty()){
            throw new EmptyListException("La lista esta vacia");
        }
        return designers;
    }

    public Optional<Designer> findByDNI(Long dni){
        return designers.stream()
                .filter(designer -> designer.getDni().equals(dni))
                .findFirst();
    }



}
