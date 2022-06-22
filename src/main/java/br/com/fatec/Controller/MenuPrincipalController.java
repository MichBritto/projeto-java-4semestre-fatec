/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btnEstado;
    @FXML
    private Button btnClube;
    @FXML
    private Button btnJogador;
    @FXML
    private Button btnConsulta;
    @FXML
    private Button btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEstado_Click(ActionEvent event) {
        String fxml = "cadastroEstado";
        try {
            
            App.setRoot(fxml);  
        } catch (IOException ex) {
            System.out.println("Ocorreu o seguinte erro: " + ex.getMessage());
        } 
    }

    @FXML
    private void btnClube_Click(ActionEvent event) {
        String fxml = "cadastroClube";
        try {
            
            App.setRoot(fxml);  
        } catch (IOException ex) {
            System.out.println("Ocorreu o seguinte erro: " + ex.getMessage());
        } 
    }

    @FXML
    private void btnJogador_Click(ActionEvent event) {
        String fxml = "cadastroJog";
        try {
            
            App.setRoot(fxml);  
        } catch (IOException ex) {
            System.out.println("Ocorreu o seguinte erro: " + ex.getMessage());
        } 
    }

    @FXML
    private void btnConsulta_Click(ActionEvent event) {
        String fxml = "consultaInfo";
        try {
            
            App.setRoot(fxml);  
        } catch (IOException ex) {
            System.out.println("Ocorreu o seguinte erro: " + ex.getMessage());
        } 
    }

    @FXML
    private void btnSair_Click(ActionEvent event) {
        Stage stage = (Stage) btnClube.getScene().getWindow();
        stage.close();
    }
    
}
