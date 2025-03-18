package org.example.mvc.controller.developer;

import org.example.mvc.model.Developer;

public class DeveloperController {
    private final Developer developer;

    public DeveloperController(Developer developer) {
        this.developer = developer;
    }

    public Developer createDeveloper(String name, String surname, Long dni, String mainLanguage) {
        return new Developer(name, surname, dni, mainLanguage);
    }

    public void updateMainLanguageDeveloper(String mainLanguage) {
        developer.setMainLanguage(mainLanguage);
    }

    public String workingDeveloper() {
        return developer.working();
    }

    public String restingDeveloper() {
        return developer.resting();
    }


}
