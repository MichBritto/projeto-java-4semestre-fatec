/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.model;

import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Clube {
    // atributos
    private String siglaClube;
    private String nomeClube;
    private String paisOrigem;
    private String estado;

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

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.siglaClube);
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
        return true;
    }

    @Override
    public String toString() {
        return nomeClube;
    }
    
    
    
}
