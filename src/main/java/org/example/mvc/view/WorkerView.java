package org.example.mvc.view;

import org.example.mvc.controller.designer.DesignerController;
import org.example.mvc.controller.developer.DeveloperController;
import org.example.mvc.controller.worker.WorkerController;

public class WorkerView {

    private final WorkerController workerController;
    private final DesignerController designerController;
    private final DeveloperController developerController;

    public WorkerView(WorkerController workerController, DesignerController designerController, DeveloperController developerController) {
        this.workerController = workerController;
        this.designerController = designerController;
        this.developerController = developerController;
    }


}
