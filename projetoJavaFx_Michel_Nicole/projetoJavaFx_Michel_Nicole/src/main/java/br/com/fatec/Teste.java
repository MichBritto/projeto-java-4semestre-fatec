/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec;

import br.com.fatec.persistencia.Banco;

/**
 *
 * @author Aluno
 */
public class Teste {
    public static void main(String args[]){
        
        try {
            System.out.println("Conectando ....");
            //testar a conexao
            Banco.conectar();
            System.out.println("Conectado!!!");
        } catch (Exception e) {
        }
        
    }
}
