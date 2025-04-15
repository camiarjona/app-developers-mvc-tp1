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

//        Project project = new Project("Romulo", "App de citas");
//        Project project1 = new Project("Remo", "App de pedidos");
        try {
            ProjectRepository pr = new ProjectRepository();
            ProjectController pc = new ProjectController(pr);
            ProjectView pv = new ProjectView(pc);
            pv.projectMenu();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//        try{
//            pr.save(project);
//            pr.save(project1);
//        } catch (ProjectException e) {
//            System.out.println(e.getMessage());
//        }




//        DeveloperRepository dr = new DeveloperRepository();
//        DeveloperController dc = new DeveloperController(dr);
//        DeveloperView dv = new DeveloperView(dc, pc);
//
//        DesignerRepository dsr = new DesignerRepository();
//        DesignerController dsc = new DesignerController(dsr);
//        DesignerView dsv = new DesignerView(dsc, pc);
//
//        GeneralView gv = new GeneralView(dv, dsv, pv);
//
//        gv.generalMenu();


    }

}