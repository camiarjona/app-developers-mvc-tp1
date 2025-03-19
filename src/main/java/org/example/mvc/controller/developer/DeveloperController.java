package org.example.mvc.controller.developer;

import org.example.mvc.model.Developer;

public class DeveloperController {

    public static Developer createDeveloper(String name, String surname, Long dni, String mainLanguage) {
        return new Developer(name, surname, dni, mainLanguage);
    }

//    public String workingDeveloper() {
//        return developer.working();
//    }
//
//    public String restingDeveloper() {
//        return developer.resting();
//    }


}
