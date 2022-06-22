/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.App;
import br.com.fatec.DAO.ClubeDAO;
import br.com.fatec.DAO.NacionalidadeDAO;
import br.com.fatec.Estado;
import br.com.fatec.model.Clube;
import br.com.fatec.model.Nacionalidade;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class CadastroClubeController implements Initializable {

    @FXML
    private TextField txtSigla;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtNome;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnMostrar;
    @FXML
    private ComboBox<Estado> cbEstado;
    @FXML
    private Button btnCadMenu;
    @FXML
    private ComboBox<Nacionalidade> cbPais;
    
    Estado e1 = new Estado("SP","São paulo");
    Estado e2 = new Estado("RS","Rio grande do sul");
    Estado e3 = new Estado("MG","Minas gerais");
    Estado e4 = new Estado("BA","Bahia");
    Estado e5 = new Estado("SC","Santa catarina");
    Estado e6 = new Estado("RJ","Rio de janeiro");
    
    private ObservableList<Estado> estados = 
           FXCollections.observableArrayList(e1,e2,e3,e4,e5,e6);
    
    private ObservableList<Nacionalidade> nacionalidades = 
           FXCollections.observableArrayList();

    
    //list criado para receber os dados que vem da tela cadastroEstado
    public ObservableList<Estado> dado = 
           FXCollections.observableArrayList();
    

    //para pegar os dados;
    public ObservableList<Estado> getDado() {
        return dado;
    }
    
    //para setar os dados
    public void setDado(ObservableList<Estado> dado) {
        this.dado = dado;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbEstado.setItems(estados);
        cbPais.setItems(preencheTabela());
        
    }    

    @FXML
    private void btnRegistrar_Click(ActionEvent event) {
       
        //verifica se existem campos em branco ou se a sigla do país tem mais de 3 letras
        if(!verificaCampos())
            return;
  
        //objeto com as querys
        ClubeDAO dao = new ClubeDAO();
        //objeto para incluir no banco, dados que podem vir da tela
        Clube clube = new Clube(txtSigla.getText(),txtNome.getText(),
                cbEstado.getValue().toString(),cbPais.getValue().toString());
        
       //inserir elemento 
        try {
            if(dao.insere(clube)) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Clube adicionado com sucesso!", 
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
                case 1062:
                     Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Erro ("+ex.getErrorCode()+") ao incluir. Chave primeira "+ clube.getSiglaClube() +
                            " já existe no banco!\n "
                                    + "Reveja as informações e tente novamente.", 
                    ButtonType.OK);
                    alerta.showAndWait();
                    break;
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
    private void btnExcluir_Click(ActionEvent event) {
        
        //verifica se existem campos em branco ou se a sigla do país tem mais de 3 letras
        if(!verificaCampos())
            return;
  
        //objeto com as querys
        ClubeDAO dao = new ClubeDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Clube clube = new Clube(txtSigla.getText(),txtNome.getText(),
                cbEstado.getValue().toString(),cbPais.getValue().toString());
        
        try {
             //verifica se existe uma siglaClube = ao txtSigla
            if(dao.buscaID(clube) == null){
                //se nao existir
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Exclusão não realizada. Não existe nenhum clube no banco"
                            + " com a sigla: "+ clube.getSiglaClube(), 
                    ButtonType.OK);
                    alerta.showAndWait();
                 //retorna   
                return;
            }
            //se o objeto for excluido:
            if(dao.remove(clube)){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Clube removido com sucesso!", 
                    ButtonType.OK);
                    alerta.showAndWait();
                     //limpa campos
                    limpaCampos();
            }       
        } catch (SQLException ex) {
            //erro detalhado
            System.out.println("Erro de exclusão: " + ex.getMessage());
            //pegando o numero do erro e tratando ele para o usuário
            switch (ex.getErrorCode()) {
                case 1451:
                     Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Erro ("+ex.getErrorCode()+") ao excluir. Para realizar a exclusão, nenhum"
                            + "jogador pode estar associado a este clube.", 
                    ButtonType.OK);
                    alerta.showAndWait();
                    break;
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
    private void btnAtualizar_Click(ActionEvent event) {
    
        //verifica campos em branco
        if(!verificaCampos())
            return;
        
        //objeto com as querys
        ClubeDAO dao = new ClubeDAO();
        //objeto para incluir no banco, dados que podem vir da tela
        Clube clube = new Clube(txtSigla.getText(),txtNome.getText(),
                cbEstado.getValue().toString(),cbPais.getValue().toString());
        
        
        try {
            //verifica se existe uma siglaClube = ao txtSigla
            if(dao.buscaID(clube) == null){
                //se nao existir
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Alteração não realizada. Não existe nenhum clube no banco"
                            + " com a sigla: "+ clube.getSiglaClube(), 
                    ButtonType.OK);
                    alerta.showAndWait();
                 //retorna   
                return;
            }
            //realiza a alteração caso tudo esteja correto
            if(dao.altera(clube)){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Alterações em "+clube.getSiglaClube() +" realizadas com sucesso", 
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
    private void btnMostrar_Click(ActionEvent event) {
        //verifica se a sigla está preenchida
        if(txtSigla.getText().isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Por favor, preencha a SIGLA DO CLUBE e tente novamente.", 
                    ButtonType.OK);
                    alerta.showAndWait();
            return;
        }
        //objeto para chamar as querys e objeto para pegar a sigla
        ClubeDAO dao = new ClubeDAO();
        Clube clube = new Clube(txtSigla.getText());
        
        try {
            if(dao.buscaID(clube) == null){
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Não foram encontrados dados correspondentes a sigla: "+ txtSigla.getText(), 
                    ButtonType.OK);
                    alerta.showAndWait();
            }
            else{
                txtNome.setText(dao.buscaID(clube).getNomeClube());
                txtSigla.setText(dao.buscaID(clube).getSiglaClube());
                
                //criando variaveis auxiliares para estado e país para setar valor na combo
                int auxEstado=0, auxPais=0;
                //utilizando foreach para incrementar os auxEstado, para encontrarmos o index da cbEstado
                for (Estado estado : estados) {
                    
                    if(estado.getSiglaEstado().equals(dao.buscaID(clube).getEstado())){
                        System.out.println("Num "+auxEstado+" = "+estado);
                        break;
                    }
                     auxEstado++;   
                } 
                
                //utilizando foreach para incrementar os auxEstado, para encontrarmos o index da cbEstado
             
                for (Nacionalidade nac : nacionalidades) {
                    if(nac.getSiglaPais().equals(dao.buscaID(clube).getPaisOrigem())){
                        System.out.println("Num "+auxPais+" = "+nac.getSiglaPais());
                        break;
                    }
                    auxPais++;
                }
                
                cbEstado.getSelectionModel().select(auxEstado);
                /* CASO QUEIRA MOSTRAR AS INFORMAÇÕES COMO UM ALERT
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Sigla :" + dao.buscaID(clube).getSiglaClube() +
                            "\nNome Clube: "+ dao.buscaID(clube).getNomeClube() +
                             "\nEstado: "+ dao.buscaID(clube).getEstado() +
                              "\nPaís: "+ dao.buscaID(clube).getPaisOrigem(),     
                    ButtonType.OK);
                    alerta.showAndWait();
                */
                   // limpaCampos();
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
    
    //para atualizar a comboBox com os valores vindos da outra tela;
    public void atualiza() {
        //txtDestino.setText(getDado());
        cbEstado.setItems(getDado());
    }
    
    
    public boolean verificaCampos(){
        boolean condicao = true;
        //Verifica se existem campos em branco
        if(txtNome.getText().isEmpty() || txtSigla.getText().isEmpty() || 
                cbPais.getSelectionModel().isEmpty() || cbEstado.getSelectionModel().isEmpty()){
            //notifica os campos em branco
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Existem campos em branco que devem sem preenchidos!", 
                    ButtonType.OK);
            alerta.showAndWait();
            //retorna falso
            condicao = false;
       
        }
        //Verifica se sigla pais tem o tamanho correto
        else if( txtSigla.getText().length() >  3){
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
    
    public void limpaCampos(){
        txtNome.setText("");
        txtSigla.setText("");
        cbEstado.getSelectionModel().clearSelection();
        cbPais.getSelectionModel().clearSelection();
        
                
    }

    @FXML
    private void btnCadMenu_Click(ActionEvent event) {
        String fxml = "menuPrincipal";
        try {
            
            App.setRoot(fxml);  
        } catch (IOException ex) {
            System.out.println("Ocorreu o seguinte erro: " + ex.getMessage());
        }   
        Stage stage = (Stage) btnAtualizar.getScene().getWindow();
        stage.close();
    }
    
    
    //método para trazer informaçôes do banco para a comboBox cbPais
    private ObservableList<Nacionalidade> preencheTabela() {
        NacionalidadeDAO dao = new NacionalidadeDAO();
        ObservableList<Nacionalidade> nacionalidades
            = FXCollections.observableArrayList();
        
        try {
            nacionalidades.addAll(dao.lista(""));
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        
        return nacionalidades;
    }
}
