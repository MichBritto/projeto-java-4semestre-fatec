/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.App;
import br.com.fatec.Estado;
import br.com.fatec.model.Clube;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miche
 */
public class CadastroEstadoController implements Initializable {

    //variaveis auxiliares usadas para alterar objeto
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
           FXCollections.observableArrayList(e1,e2,e3,e4,e5,e6);
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
    private TextField txtNovaSigla;
    private TextField txtNovoNome;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCadastroClube;
    @FXML
    private Button btnMenu;

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
        //criando objeto para ser adicionado      
        Estado e1 = new Estado(txtSigla.getText(),txtNome.getText());
        
        //verifica se existem campos em branco
        if(txtNome.getText().isEmpty() || txtSigla.getText().isEmpty()){
            //envia uma mensagem notificando
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Existem campos em branco que devem sem preenchidos!", 
                    ButtonType.OK);
            alerta.showAndWait();
            return;
        }
        //verifica se a sigla tem mais de dois caracteres
        else if((txtSigla.lengthProperty().getValue() > 2)){  
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Sigla deve conter apenas duas letras!", 
                    ButtonType.OK);
            alerta.showAndWait();
            
            return;
        }
        
        //verifica se o objeto já existe no arrayList, para nao aceitar valores repetidos
         for (Estado estado : estados) {
            if(estado.equals(e1)){
                //caso exista, o usuário é notificado
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Estado já cadastrado no sistema, verifique a sigla\ne tente novamente...", 
                    ButtonType.OK);
                alerta.showAndWait();
                //limpa os campos
                limpaCampos();
                return;
            }
        }
        //adiciona o Estado criado na arrayList
        estados.add(e1);
        //notifica a inclusão
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Novo estado adicionado com sucesso!", 
                    ButtonType.OK);
            alerta.showAndWait();
            
        //setando o primeiro elemento da comboBox e restaurando textBox
        cbEstado.getSelectionModel().selectFirst();
        limpaCampos();
        
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        //objeto auxiliar
        Estado aux = new Estado(txtSigla.getText(), txtNome.getText());
        
        //verifica se existem campos em branco
        if(txtNome.getText().isEmpty() || txtSigla.getText().isEmpty()){
            //envia uma mensagem notificando
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Preencha os campos em branco!", 
                    ButtonType.OK);
            alerta.showAndWait();
            return;
        }
        //verifica se a sigla tem mais de dois caracteres
        else if((txtSigla.lengthProperty().getValue() > 2)){  
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Sigla deve conter apenas duas letras!", 
                    ButtonType.OK);
            alerta.showAndWait();
            
            return;
        }
        
        //realiza comparação para possivel exclusão
        for (Estado estado : estados) {
            if(estado.equals(aux)){
                //exclui o objeto
                cbEstado.getItems().remove(estado);
                //caso exista, o usuário é notificado
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Estado excluído com sucesso!", 
                    ButtonType.OK);
                alerta.showAndWait();
                //limpa os campos
                limpaCampos();
                return;
            }
        }
        //notifica o usuario que nao foi encontrado um usuario 
        Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Estado não encontrado, verifique as informaçôes...", 
                    ButtonType.OK);
                alerta.showAndWait();
        
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
        
        //verifica se o usuario já salvou as informaçôes
        if(nomeAux == null || siglaAux == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Clique no botão 'Salvar' antes de prosseguir.", 
                    ButtonType.OK);
            alerta.showAndWait();
            return;
        }
        
        //objeto auxiliar para realizar comparação
        Estado eAux = new Estado(siglaAux,nomeAux);
        
        //realizando comparação para possivel alteração
        for (Estado estado : estados) {
            if(estado.equals(eAux)){
                //alterando elementos
                estado.setSiglaEstado(txtSigla.getText());
                estado.setNomeEstado(txtNome.getText());
                //notificando a mudança
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Alterações realizadas com suceso!", 
                    ButtonType.OK);
                alerta.showAndWait();
                //restaura campos
                limpaCampos();
                //retorna
                return;
            }
        }
        //caso não seja encontrado o elemento para alteração
        Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Elemento não encontrado para alteração.", 
                    ButtonType.OK);
                alerta.showAndWait();       
    }

    @FXML
    private void btnSalvar_Click(ActionEvent event) {
        
        //verifica se existem campos em branco
        if(txtNome.getText().isEmpty() || txtSigla.getText().isEmpty()){
            //notifica o usuario dos campos vazios
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Existem campos em branco que devem sem preenchidos!", 
                    ButtonType.OK);
            alerta.showAndWait();
            return;
        }
       //verifica se a sigla tem mais de dois caracteres
        else if((txtSigla.lengthProperty().getValue() > 2)){  
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Sigla deve conter apenas duas letras!", 
                    ButtonType.OK);
            alerta.showAndWait();
            
            return;
        }
        //caso esteja tudo certo, ele salva os atributos
        nomeAux = txtNome.getText();
        siglaAux = txtSigla.getText();
        
        //notifica o usuario
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "O elemento foi salvo e está pronto para mudança", 
                    ButtonType.OK);
        alerta.showAndWait();
    }
 
    public void limpaCampos(){
        //limpa campos e seleciona o primeiro elemento da comboBox
       cbEstado.getSelectionModel().selectFirst();
       txtNome.setText("");
       txtSigla.setText("");
    }

    

    //Criado para chamar a nova tela de CadastroClubes e setar a observableList no construtor
    @FXML
    private void btnCadastroClube_Click(ActionEvent event) throws Exception {
         Clube rec = new Clube(cbEstado.getItems());
         rec.start(new Stage());
         
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

    
}
