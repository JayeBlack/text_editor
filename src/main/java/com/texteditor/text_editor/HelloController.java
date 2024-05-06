package com.texteditor.text_editor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {

    @FXML
    private Button btnedit;

    @FXML
    private Button btnlock;

    @FXML
    private Button btnopen;

    @FXML
    private Button btnsave;

    @FXML
    private Label lbldisplay;

    @FXML
    private MenuItem mnclose;

    @FXML
    private TextArea txteditor;
    FileChooser dialogbox = new FileChooser();

    @FXML
    void getcharacters(KeyEvent event) {
        lbldisplay.setText("Characters Typed:" + txteditor.getText().length());

    }

    @FXML
    void onedit(ActionEvent event) {
        txteditor.setEditable(true);
    }

    @FXML
    void onlock(ActionEvent event) {
        txteditor.setEditable(false);
    }

    @FXML
    void onopen(ActionEvent event) throws FileNotFoundException {
        //add filters to show only text files
        //learn to add several filters
        dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files(*.txt)", "*.txt"));
        File selectedfile = dialogbox.showOpenDialog(new Stage());
        //check if user has selected a file
        if (selectedfile == null) {
            //do nothing
        } else {
            //clear content before opening
            txteditor.clear();
            Scanner scan = new Scanner(selectedfile);
            while (scan.hasNextLine()) {
                txteditor.appendText(scan.nextLine() + "\n");
            }
            scan.close();
        }
        lbldisplay.setText("Characters Typed:" + txteditor.getText().length());


    }

    @FXML
    void onsave(ActionEvent event) throws FileNotFoundException {
        dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files(*.txt)", "*.txt"));
        File selectedfile = dialogbox.showSaveDialog(new Stage());

        //check if user selected a file
        if (selectedfile == null) {
            //do nothing
        } else {
            PrintWriter output = new PrintWriter(selectedfile);
            output.println(txteditor.getText());
            output.close();
            //clear content after saving
            txteditor.clear();
            lbldisplay.setText("Characters Typed:" + txteditor.getText().length());

        }
    }

    @FXML
    void onexit(ActionEvent event) {
        //platform should exit
        Platform.exit();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Codes here will always execute first");
        //set default directory to open/save dialog box
        dialogbox.setInitialDirectory(new File("C:\\users"));

    }
}
