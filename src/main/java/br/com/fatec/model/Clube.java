/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import br.com.fatec.App;
import br.com.fatec.Controller.CadastroClubeController;
import br.com.fatec.Estado;
import java.util.Objects;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author miche
 */
public class Clube extends Application{
    private String siglaClube;
    private String nomeClube;
    private String estado;
    private String paisOrigem;

    public String getSiglaClube() {
        return siglaClube;
    }

    public void setSiglaClube(String siglaClube) {
        this.siglaClube = siglaClube;
    }

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.siglaClube);
        hash = 83 * hash + Objects.hashCode(this.nomeClube);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clube other = (Clube) obj;
        if (!Objects.equals(this.siglaClube, other.siglaClube)) {
            return false;
        }
        return Objects.equals(this.nomeClube, other.nomeClube);
    }

    
    //construtores
    public Clube() {
    }

    public Clube(String siglaClube, String nomeClube, String estado, String paisOrigem) {
        this.siglaClube = siglaClube;
        this.nomeClube = nomeClube;
        this.estado = estado;
        this.paisOrigem = paisOrigem;
    }
    
    public Clube (String siglaClube){
        this.siglaClube = siglaClube;
    }
    
    
    
    //TUDO DAQUI PARA BAIXO È REFERENTE AO RECEBIMENTO DA COMBOBOX
    
    public static Stage tela;
    private ObservableList<Estado> recebido = 
           FXCollections.observableArrayList();
    
    //recebe o dado da tela anterior
    public Clube(ObservableList<Estado> recebido) {
        this.recebido = recebido;
    }
    
    public static void setStage(Stage t) {
        tela = t;
    }
    
    @Override
    public void start(Stage tela) throws Exception {
        //passa o Stage recebido para a variavel
        //local da classe
        setStage(tela);
        
        //carrega o próximo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("cadastroClube.fxml"));
        //efetua o carregamento na memória
        Parent root = fxmlLoader.load();
        
        //obtem o acesso ao objeto controller do formulario
        CadastroClubeController controler = fxmlLoader.getController();
        
        //passa os dados necessários para a próxima tela
        controler.setDado(recebido);
        controler.atualiza();
        
        Scene scene = new Scene(root, 650, 480);
        
        tela.setScene(scene);
        tela.show();     
    }
    
}
