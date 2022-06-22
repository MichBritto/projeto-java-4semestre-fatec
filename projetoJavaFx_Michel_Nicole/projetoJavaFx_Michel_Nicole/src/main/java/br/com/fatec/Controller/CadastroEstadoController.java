/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.Estado;
import br.com.fatec.persistencia.Banco;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class CadastroEstadoController implements Initializable {
  
    

    static String nomeAux, siglaAux;
    //cria uma coleção para armazenar todos os 
    //produtos que aparecerão na combo e cria alguns objetos com valores defaults
    Estado e1 = new Estado("SP","São paulo");
    Estado e2 = new Estado("RS","Rio grande do sul");
    Estado e3 = new Estado("MG","Minas gerais");
    Estado e4 = new Estado("BA","Bahia");
    Estado e5 = new Estado("SC","Santa catarina");
    Estado e6 = new Estado("RJ","Rio de janeiro");
    
    private ObservableList<Estado> estados = 
           FXCollections.observableArrayList(e1,e2,e3,e4,e5,e6,new Estado("AM","Amazonas"));
    
    
    @FXML
    private TextField txtSigla;
    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox<Estado> cbEstado;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private TextField txtNovaSigla;
    @FXML
    private TextField txtNovoNome;
    @FXML
    private Button btnSalvar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //coloca a coleção dentro da combo
        cbEstado.setItems(estados);
        
       
        //trata do evento change da comboBox
         cbEstado.valueProperty().addListener((value, velho, novo) -> {
                    //coloca os dados do objeto NOVO selecionado
                    //dentro dos texts
                    txtSigla.setText(novo.getSiglaEstado());
                    txtNome.setText(novo.getNomeEstado());
        });
    }    

    @FXML
    private void btnRegistrar_Click(ActionEvent event) {
        
        //cria um objeto para ser adicionado
        Estado e1 = new Estado(txtSigla.getText(),txtNome.getText());
        
        //verifica se existem espaços em branco
        if(txtNome.getText().isEmpty() || txtSigla.getText().isEmpty()){
           Alert alerta = new Alert(Alert.AlertType.WARNING,
                "Espaços em branco, tente novamente!", 
                ButtonType.OK);
            alerta.showAndWait();
            return;
        }
        //verifica se a sigla contem menos de dois caracteres
        if((txtSigla.lengthProperty().getValue() > 2)){  
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Sigla deve conter apenas dois caracteres!", 
                    ButtonType.OK);
            alerta.showAndWait();
            
            return;
        }

        //adiciona o Estado criado na coleção e fornece um aviso
        estados.add(e1);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Novo estado adicionado com sucesso!", 
                    ButtonType.OK);
            alerta.showAndWait();
       
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
      
        //pega a String da comboBox
       String a = cbEstado.getValue().toString();
 
       //objeto auxiliar para realizar a busca e possivel exclusao
       Estado aux = new Estado(txtSigla.getText(),txtNome.getText());
       
       //um laço (Foreach) para passar por todos os objetos do arrayList
       for(Estado estado: estados){
           //se o objeto procurado existir no arrayList
           if(estado.equals(aux)){
               //objeto é Excluido 
               cbEstado.getItems().remove(estado);
               break;
           }
       }
       //selecionar o primeiro elemento da comboBox
      cbEstado.getSelectionModel().selectFirst();
      
       //Limpar texto
       txtNome.setText("");
       txtSigla.setText("");
       
       //Criar uma msgBox para alertar que o Estado foi excluido
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Estado Excluido: " + a, 
                    ButtonType.OK);
            alerta.showAndWait();
             
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
        
        for (Estado estado : estados) {
            if(estado.equals(new Estado(siglaAux,nomeAux))){
                System.out.println("sao iguais");
                estado.setSiglaEstado(txtSigla.getText());
                estado.setNomeEstado(txtNome.getText());
                
                
                //atualizar comboBox
                   txtNome.setText("");
                   txtSigla.setText("");
                   
                   
                 Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Atualizaçôes realizadas com sucesso!", 
                    ButtonType.OK);
                alerta.showAndWait();
            }
        }
/*        for (Estado estado : estados) {
            if(estado.equals(new Estado(txtSigla.getText(),txtNome.getText()))){
                System.out.println("sao iguais");
                estado.setSiglaEstado(txtNovaSigla.getText());
                estado.setNomeEstado(txtNovoNome.getText());
                
                
                //atualizar comboBox
                   txtNome.setText("");
                   txtSigla.setText("");
                   txtNovoNome.setText("");
                   txtNovaSigla.setText("");
                   
                 Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Atualizaçôes realizadas com sucesso!", 
                    ButtonType.OK);
                alerta.showAndWait();
            }
        } 
   */             
       txtNome.setText("");
       txtSigla.setText("");
       cbEstado.getSelectionModel().selectFirst();
       nomeAux = "";
       siglaAux = "";
       
    }

    @FXML
    private void btnSalvar_Click(ActionEvent event) {
        try {
            System.out.println("Conectando ....");
            //testar a conexao
            Banco.conectar();
            System.out.println("Conectado!!!");
        } catch (Exception e) {
        }
       /* nomeAux = txtNome.getText();
        siglaAux = txtSigla.getText();
         Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Dados guardados e prontos para atualizaçao!", 
                    ButtonType.OK);
                alerta.showAndWait();
        */
              
    }
    
   
    
}
