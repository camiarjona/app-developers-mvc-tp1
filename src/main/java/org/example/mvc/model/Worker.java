package org.example.mvc.model;

import org.example.mvc.interfaces.IWorker;

public abstract class Worker extends Person implements IWorker {

    protected static Integer ID_AUTOINCREMENT = 1;
    protected final Integer id;
    protected WorkerStatus status;

    public Worker(String name, String surname, Long dni) {
        super(name, surname, dni);
        this.id = ID_AUTOINCREMENT++;
        this.status = WorkerStatus.DESCANSANDO;
    }

    @Override
    public String toString() {
        return  super.toString() + "\nId de trabajador: " + id + "\nEstado: " + status;
    }

    public WorkerStatus getStatus() {
        return status;
    }

    public void setStatus(WorkerStatus status) {
        this.status = status;
    }

    public boolean isWorking(){
        return this.status == WorkerStatus.TRABAJANDO;
    }

    public abstract void updateSpecificAttribute(String newValue);

    public abstract String getSpecificAttribute();

}
