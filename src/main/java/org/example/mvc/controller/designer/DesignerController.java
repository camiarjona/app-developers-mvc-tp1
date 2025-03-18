package org.example.mvc.controller.designer;

import org.example.mvc.model.Designer;

public class DesignerController {

    private final Designer designer;

    public DesignerController(Designer designer) {
        this.designer = designer;
    }

    public Designer createDesigner(String name, String surname, Long dni, String specialty) {
        return new Designer(name, surname, dni, specialty);
    }

    public void updateSpecialtyDesigner(String newSpecialty) {
        designer.setSpecialty(newSpecialty);
    }

    public String workingDesigner() {
        return designer.working();
    }

    public String restingDesigner() {
        return designer.resting();
    }

}
