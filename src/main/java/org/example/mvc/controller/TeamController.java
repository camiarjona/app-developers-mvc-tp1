package org.example.mvc.controller;

import org.example.mvc.model.repositories.DesignerRepository;
import org.example.mvc.model.repositories.DeveloperRepository;

public class TeamController {

    private DesignerRepository designerRepository;
    private DeveloperRepository developerRepository;

    public TeamController(DesignerRepository designerRepository, DeveloperRepository developerRepository) {
        this.designerRepository = designerRepository;
        this.developerRepository = developerRepository;
    }


}
