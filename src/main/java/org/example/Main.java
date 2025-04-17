package org.example;

import org.example.base.GeneralView;
import org.example.designer.controller.DesignerController;
import org.example.designer.model.DesignerRepository;
import org.example.designer.view.DesignerView;
import org.example.developer.controller.DeveloperController;
import org.example.developer.model.DeveloperRepository;
import org.example.developer.view.DeveloperView;
import org.example.project.controller.ProjectController;
import org.example.project.exceptions.ProjectException;
import org.example.project.model.Project;
import org.example.project.model.ProjectRepository;
import org.example.project.view.ProjectView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            DeveloperRepository dr = new DeveloperRepository();
            DeveloperController dc = new DeveloperController(dr);
            DesignerRepository dsr = new DesignerRepository();
            DesignerController dsc = new DesignerController(dsr);
            ProjectRepository pr = new ProjectRepository();
            ProjectController pc = new ProjectController(pr, dc, dsc);
            ProjectView pv = new ProjectView(pc);
            DeveloperView dv = new DeveloperView(dc, pc);
            DesignerView dsv = new DesignerView(dsc, pc);
            GeneralView gv = new GeneralView(dv, dsv, pv);

            gv.generalMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}