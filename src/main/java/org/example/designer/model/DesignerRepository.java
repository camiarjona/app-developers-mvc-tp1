package org.example.designer.model;

import org.example.designer.exceptions.DesignerException;
import org.example.designer.exceptions.DesignerNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DesignerRepository {

    private final List<Designer> designers;

    public DesignerRepository() {
        this.designers = new ArrayList<>();
    }

    public void save(Designer designer) throws DesignerException {
        if(designer != null && !designers.contains(designer)){
            designers.add(designer);
        }
        else{
            throw new DesignerException("El diseñador ya se encuentra registrado.");
        }
    }

    public List<Designer> findAll() throws DesignerException {
        if(designers.isEmpty()){
            throw new DesignerException("La lista esta vacia");
        }
        return designers;
    }

    public Optional<Designer> findByDNI(Integer dni){
        return designers.stream()
                .filter(designer -> designer.getDni().equals(dni))
                .findFirst();
    }

    public void delete(Integer dni) throws DesignerNotFoundException {
        Optional<Designer> op = findByDNI(dni);
        if (op.isPresent()) {
            designers.remove(op.get());
        }
        else{
            throw new DesignerNotFoundException("El diseñador no se encuentra en la lista.");
        }
    }

    public Designer getByDni(Integer dni) throws DesignerNotFoundException {
        Optional<Designer> op = findByDNI(dni);
        if (op.isPresent()) {
            return op.get();
        }
        else {
            throw new DesignerNotFoundException("El diseñador solicitado no existe.");
        }
    }



}
