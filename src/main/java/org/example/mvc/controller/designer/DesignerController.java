package org.example.mvc.controller.designer;

import org.example.mvc.model.Designer;

public class DesignerController {

    public static Designer createDesigner(String name, String surname, Long dni, String specialty) {
        return new Designer(name, surname, dni, specialty);
    }

//    public String workingDesigner() {
//        return designer.working();
//    }
//
//    public String restingDesigner() {
//        return designer.resting();
//    }

}
