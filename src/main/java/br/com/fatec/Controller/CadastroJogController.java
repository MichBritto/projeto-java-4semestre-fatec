package br.com.fatec.Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import br.com.fatec.App;
import br.com.fatec.DAO.JogadoresDAO;
import br.com.fatec.model.Jogador;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author myhea
 */
public class CadastroJogController implements Initializable {

    @FXML
    private TextField txt_nomeJog;
    private DatePicker dt_dataNasc;
    @FXML
    private TextField txt_Pos;
    @FXML
    private TextField txt_Clube;
    @FXML
    private TextField txt_Pais;
    @FXML
    private Button btn_criaJog;
    @FXML
    private Button btn_editaJog;
    @FXML
    private Button btn_excluiJog;
    @FXML
    private TextField txt_idJog;
    @FXML
    private Button btnMostrar;
    @FXML
    private Button btnMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_criaJog_Click(MouseEvent event) {
        //verifica se existem campos em branco
        if(!verificaCampos())
            return;
        ///objeto das querys
        JogadoresDAO dao = new JogadoresDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Jogador jogador = new Jogador(txt_nomeJog.getText(),
                txt_Pos.getText(),txt_Clube.getText(),txt_Pais.getText());
            
        try {
            if(dao.insere(jogador)) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Jogador adicionado com sucesso adicionado com sucesso!", 
                    ButtonType.OK);
                    alerta.showAndWait();
                     //limpa campos
                    limpaCampos();
            }
        } catch (SQLException ex) {
            //erro detalhado
            System.out.println("Erro de inclusão: " + ex.getMessage());
            //pegando o numero do erro e tratando ele para o usuário
            switch (ex.getErrorCode()) {
                default:
                        Alert alerta1 = new Alert(Alert.AlertType.WARNING,
                    "Erro ("+ex.getErrorCode()+") ao incluir. Contacte o suporte para mais informaçôes", 
                    ButtonType.OK);
                    alerta1.showAndWait();
                    break;    
            }
        }
    }


    @FXML
    private void btnMostrar_Click(ActionEvent event) {
        //verifica se existem campos em branco
        if(txt_idJog.getText().isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Por favor, preencha o ID DO JOGADOR e tente novamente.", 
                    ButtonType.OK);
                    alerta.showAndWait();
                     //limpa campos
                    limpaCampos();
                    return;
        }
            
        ///objeto das querys
        JogadoresDAO dao = new JogadoresDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Jogador jogador = new Jogador(Integer.parseInt(txt_idJog.getText()));
            
        try {
            if(dao.buscaID(jogador) == null){
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Não foram encontrados dados correspondentes ao ID JOGADOR: "+ txt_idJog.getText(), 
                    ButtonType.OK);
                    alerta.showAndWait();
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "ID JOGADOR :" + dao.buscaID(jogador).getIdJog() +
                            "\nNome Jogador: "+ dao.buscaID(jogador).getNomeJog() +
                             "\nPosição: "+ dao.buscaID(jogador).getPosicao() +
                             "\nClube Jogador: "+ dao.buscaID(jogador).getClubeJog() +
                              "\nPaís: "+ dao.buscaID(jogador).getPaisOrigem(),     
                    ButtonType.OK);
                    alerta.showAndWait();
                    limpaCampos();
            }
        } catch (SQLException ex) {
            //dando o erro expecificado
            System.out.println("Erro de select: " + ex.getMessage());
            //tratando o erro para o usuario entender
            switch (ex.getErrorCode()) {
                default:
                        Alert alerta1 = new Alert(Alert.AlertType.WARNING,
                    "Erro ("+ex.getErrorCode()+") ao mostrar. Contacte o suporte para mais informaçôes", 
                    ButtonType.OK);
                    alerta1.showAndWait();
                    break;    
            }
        }
    }


    @FXML
    private void btn_editaJog_Click(MouseEvent event) {
        //verifica campos em branco
        if(!verificaCampos())
            return;
        
        //objeto com as querys
        JogadoresDAO dao = new JogadoresDAO();
        //objeto para incluir no banco, dados que podem vir da tela
        Jogador jogador = new Jogador(Integer.parseInt(txt_idJog.getText()),txt_nomeJog.getText(), 
                txt_Pos.getText(), txt_Clube.getText(),txt_Pais.getText());
        
        
        try {
            //verifica se existe uma siglaClube = ao txtSigla
            if(dao.buscaID(jogador) == null){
                //se nao existir
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Alteração não realizada. Não existe nenhum jogador no banco"
                            + " com o ID: "+ jogador.getIdJog(), 
                    ButtonType.OK);
                    alerta.showAndWait();
                 //retorna   
                return;
            }
            //realiza a alteração caso tudo esteja correto
            if(dao.altera(jogador)){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Alterações em jogador de ID: "+jogador.getIdJog()+" realizadas com sucesso", 
                    ButtonType.OK);
                    alerta.showAndWait();
                    //limpa campos
                    limpaCampos();
            }
                
        } catch (SQLException ex) {
            //dando o erro expecificado
            System.out.println("Erro de alteração: " + ex.getMessage());
            //tratando o erro para o usuario entender
            switch (ex.getErrorCode()) {
                default:
                        Alert alerta1 = new Alert(Alert.AlertType.WARNING,
                    "Erro ("+ex.getErrorCode()+") ao alterar. Contacte o suporte para mais informaçôes", 
                    ButtonType.OK);
                    alerta1.showAndWait();
                    break;    
            }
            
        }
    }

    @FXML
    private void btn_excluiJog_Click(MouseEvent event) {
        //verifica se existem campos em branco ou se a sigla do país tem mais de 3 letras
        if(txt_idJog.getText().isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Por favor, preencha o ID DO JOGADOR e tente novamente a exclusão.", 
                    ButtonType.OK);
                    alerta.showAndWait();
                     //limpa campos
                    limpaCampos();
                    return;
        }
            
  
        //objeto com as querys
        JogadoresDAO dao = new JogadoresDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Jogador jogador = new Jogador(Integer.parseInt(txt_idJog.getText()));
        
        try {
             //verifica se existe uma siglaClube = ao txtSigla
            if(dao.buscaID(jogador) == null){
                //se nao existir
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Exclusão não realizada. Não existe nenhum jogador no banco"
                            + " com o ID: "+ jogador.getIdJog(), 
                    ButtonType.OK);
                    alerta.showAndWait();
                 //retorna   
                return;
            }
            //se o objeto for excluido:
            if(dao.remove(jogador)){
                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION,
                    "Jogador removido com sucesso!", 
                    ButtonType.OK);
                    alerta1.showAndWait();
                     //limpa campos
                    limpaCampos();
            }       
        } catch (SQLException ex) {
            //erro detalhado
            System.out.println("Erro de exclusão: " + ex.getMessage());
            //pegando o numero do erro e tratando ele para o usuário
            switch (ex.getErrorCode()) {
                default:
                        Alert alerta1 = new Alert(Alert.AlertType.WARNING,
                    "Erro ("+ex.getErrorCode()+") ao excluir. Contacte o suporte para mais informaçôes", 
                    ButtonType.OK);
                    alerta1.showAndWait();
                    break;    
            }
        }
    }
    
    
    @FXML
    private void btnMenu_Click(ActionEvent event) {
        String fxml = "menuPrincipal";
        try {
            
            App.setRoot(fxml);
        } catch (IOException ex) {
            System.out.println("Ocorreu o seguinte erro: " + ex.getMessage());
        }
    }
    
    public void limpaCampos(){
        txt_Clube.setText("");
        txt_Pais.setText("");
        txt_Pos.setText("");
        txt_idJog.setText("");
        txt_nomeJog.setText("");
        
    }
    
    public boolean verificaCampos(){
        boolean condicao = true;
        //Verifica se existem campos em branco
        if(txt_Clube.getText().isEmpty() || txt_Pais.getText().isEmpty() || 
                txt_Pos.getText().isEmpty() || txt_nomeJog.getText().isEmpty()){
            //notifica os campos em branco
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Existem campos em branco que devem sem preenchidos!", 
                    ButtonType.OK);
            alerta.showAndWait();
            //retorna falso
            condicao = false;
       
        }
        //Verifica se sigla pais tem o tamanho correto
        else if(txt_Pais.getText().length() >  3 || txt_Clube.getText().length() >  3 ||
                txt_Pos.getText().length() > 3){
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Sigla do País ou do Clube devem conter no maximo 3 letras."
                            + "  Verifique as informações e tente novamente!", 
                    ButtonType.OK);
            alerta.showAndWait();
            //retorna falso
            condicao = false;
        }
        return condicao;
    }
}
