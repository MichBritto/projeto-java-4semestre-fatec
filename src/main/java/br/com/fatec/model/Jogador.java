/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.time.LocalDate;



/**
 *
 * @author myhea
 */
public class Jogador {
    private int idJog;
    private String nomeJog;
   
    private String posicao;
    private String clubeJog;
    private String paisOrigem;

    /**
     * @return the idJog
     */
    public int getIdJog() {
        return idJog;
    }

    /**
     * @param idJog the idJog to set
     */
    public void setIdJog(int idJog) {
        this.idJog = idJog;
    }

    /**
     * @return the nomeJog
     */
    public String getNomeJog() {
        return nomeJog;
    }

    /**
     * @param nomeJog the nomeJog to set
     */
    public void setNomeJog(String nomeJog) {
        this.nomeJog = nomeJog;
    }

    

    /**
     * @return the posicao
     */
    public String getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    /**
     * @return the clubeJog
     */
    public String getClubeJog() {
        return clubeJog;
    }

    /**
     * @param clubeJog the clubeJog to set
     */
    public void setClubeJog(String clubeJog) {
        this.clubeJog = clubeJog;
    }

    /**
     * @return the paisOrigem
     */
    public String getPaisOrigem() {
        return paisOrigem;
    }

    /**
     * @param paisOrigem the paisOrigem to set
     */
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
    //construtor
    public Jogador(){
        
    }

    public Jogador(int idJog, String nomeJog, String posicao, String clubeJog, String paisOrigem) {
        this.idJog = idJog;
        this.nomeJog = nomeJog;
        this.posicao = posicao;
        this.clubeJog = clubeJog;
        this.paisOrigem = paisOrigem;
    }
    
    public Jogador(String nomeJog, String posicao, String clubeJog, String paisOrigem) {
       
        this.nomeJog = nomeJog;
        this.posicao = posicao;
        this.clubeJog = clubeJog;
        this.paisOrigem = paisOrigem;
    }

    public Jogador(int idJog) {
        this.idJog = idJog;
    }

    
    //equals e hashset

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idJog;
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
        final Jogador other = (Jogador) obj;
        return this.idJog == other.idJog;
    }
    
    
}
