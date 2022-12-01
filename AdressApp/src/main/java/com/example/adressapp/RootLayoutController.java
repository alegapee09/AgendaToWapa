package com.example.adressapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {

    // Se conecta el Hello Application ( main )
    private HelloApplication mainApp;


    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }


    @FXML //método para crear nuevo
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }


    @FXML //método para abrir un archivo existente
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }


    @FXML // método para guardar este archivo
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML //método para guardar como
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }


    @FXML //método para decir que lo he hecho yo
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("AdrressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Alejandro García Peña");
        alert.showAndWait();
    }


    @FXML //método para salir
    private void handleExit() {
        System.exit(0);
    }
}
