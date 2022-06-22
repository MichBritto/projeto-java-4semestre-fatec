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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author myhea
 */
public class ConsultaInfoController implements Initializable {

    @FXML
    private TableColumn<Jogador, Integer> col_id;
    @FXML
    private TableColumn<Jogador, String> col_nome;
    @FXML
    private TableColumn<Jogador, String> col_pos;
    @FXML
    private TableColumn<Jogador, String> col_clube;
    @FXML
    private TableColumn<Jogador, String> col_pais;
    @FXML
    private TableView<Jogador> tblJogadores;
    @FXML
    private TextField txtBusca;
    @FXML
    private ComboBox<String> cbFiltro;
    
   //criando um arrayList e setando valores
    private ObservableList<String> filtros = 
           FXCollections.observableArrayList("ID","Nome","Posição","Clube","País");
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnRestaurar;
    @FXML
    private Button btnMenu;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //iniciando combo
        cbFiltro.setItems(filtros);
        //associando colunas com as colunas da tabela jogador no bd
        col_id.setCellValueFactory(
                new PropertyValueFactory<>("idJog"));
        col_nome.setCellValueFactory(
                new PropertyValueFactory<>("nomeJog"));
        col_pos.setCellValueFactory(
                new PropertyValueFactory<>("posicao"));
        col_clube.setCellValueFactory(
                new PropertyValueFactory<>("clubeJog"));
        col_pais.setCellValueFactory(
                new PropertyValueFactory<>("paisOrigem"));
        
        

        //preenche a tabela
        tblJogadores.setItems(preencheTabela());
    }    
    
    private ObservableList<Jogador> preencheTabela() {
        //cria um objeto dao para utilizar as funções do sql 
        JogadoresDAO dao = new JogadoresDAO();
        //cria uma list de Jogador
        ObservableList<Jogador> jogadores
            = FXCollections.observableArrayList();
        
        try {
            //seleciona todos os jogadores da tabela JOGADOR e adiciona a uma collection, sem nenhuma condição
            jogadores.addAll(dao.lista(""));
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        //retorna a collection com os jogadores 
        return jogadores;
    }

    @FXML
    private void btnFiltrar_Click(ActionEvent event) {
        //verifica se existem campos vazios
        if(!verificaCampos())
            return;
        
        //cria um objeto dao para utilizar as funções do sql 
        JogadoresDAO dao = new JogadoresDAO();
        //cria uma list de Jogador
        ObservableList<Jogador> jogadores
            = FXCollections.observableArrayList();
        
        try {
            //adiciona todos os 'jogadores' com suas informações de acordo com a condição
            jogadores.addAll(dao.lista(alteraCombo()+" = '"+txtBusca.getText()+"'"));
        } catch (SQLException ex) {
            System.out.println("Erro: "+ ex.getErrorCode());
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        //cria a nova tableView com os valores filtrados
        tblJogadores.setItems(jogadores);
        
        txtBusca.setText("");
        
    }

    @FXML
    private void btnRestaurar_Click(ActionEvent event) {
        tblJogadores.setItems(preencheTabela());
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
    
    //altera o nome contido na combo para o nome da coluna do banco
    public String alteraCombo(){
        //variavel auxiliar para alterar o nome da 'coluna' conforme a comboBox
        String aux;
        //escolhe conforme o index: 0 = ID, 1 = Nome, 3 =....
        switch (cbFiltro.getSelectionModel().getSelectedIndex()) {
            case 0:
                aux = "idJog";
                break;
            case 1:
                aux = "nomeJog";
                break;
            case 2:
                aux = "posicao";
                break;
            case 3:
                aux = "clubeJog";
                break;
            default:
                aux = "paisOrigem";
                break;
        }
        //retorna a string da coluna referente na combo, essa string será usada na funcao dao.lista
         return aux;
    }
    
    //limpa os campos
   public boolean verificaCampos (){
       
       if("".equals(cbFiltro.getValue()) || txtBusca.getText().isEmpty()){
           Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Existem campos em branco, verifique e tente novamente.",
                    ButtonType.OK);
            alerta.showAndWait();
            return false;
       }
       return true;
   }
          
}
