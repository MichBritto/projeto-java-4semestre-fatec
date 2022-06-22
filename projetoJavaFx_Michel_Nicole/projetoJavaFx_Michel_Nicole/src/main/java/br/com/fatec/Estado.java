/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec;


import java.util.Objects;

/**
 *
 * @author miche
 */
public class Estado {
    private String siglaEstado;
    private String nomeEstado;

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public Estado(String sigla, String estado){
        siglaEstado = sigla;
        nomeEstado = estado;
    }
    public Estado(){
        
    }
    public void setSiglaEstado(String siglaEstado) {
        if( siglaEstado.length() > 2){
            System.out.println("Sigla estado necessita ser < ou = 2");
        }
        else
            this.siglaEstado = siglaEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.siglaEstado);
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.siglaEstado, other.siglaEstado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeEstado;
    }
    
}
