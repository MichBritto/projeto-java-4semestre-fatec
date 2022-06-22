/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Clube;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Viotti
 */
public class ClubeDAO 
        implements DAO<Clube> {

    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Clube clube; //meu MODEL   
    
    @Override
    public boolean insere(Clube obj) throws SQLException {
        String sql = "INSERT INTO CLUBE" +
                " VALUES (?, ?, ?, ?)"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Clube com o comando INSERT
        pst.setString(1, obj.getSiglaClube());
        pst.setString(2, obj.getNomeClube());
        pst.setString(3, obj.getEstado());
        pst.setString(4, obj.getPaisOrigem());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Clube obj) throws SQLException {
        String sql =  "DELETE FROM CLUBE WHERE siglaClube=?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Clube com o comando DELETE
        pst.setString(1, obj.getSiglaClube());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Clube obj) throws SQLException {
        String sql = "UPDATE Clube SET nomeClube = ?, estado = ?, paisOrigem = ? "
                + "WHERE siglaClube = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Clube com o comando UPDATE
        pst.setString(1, obj.getNomeClube());
        pst.setString(2,obj.getEstado());
        pst.setString(3, obj.getPaisOrigem());
        pst.setString(4, obj.getSiglaClube());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    /**
     * Busca um dado de proprietario baseado em sua Chave Primaria
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Clube buscaID(Clube obj) throws SQLException {
        String sql = "SELECT * FROM CLUBE WHERE siglaClube = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setString(1, obj.getSiglaClube());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //mover os dados(campos da tab) do resultSet para o objeto proprietário
            clube = new Clube();
            clube.setSiglaClube(rs.getString("siglaClube"));
            clube.setNomeClube(rs.getString("nomeClube"));
            clube.setEstado(rs.getString("estado"));
            clube.setPaisOrigem(rs.getString("paisOrigem"));
        }
        else {
            //não encontrou o registro solicitado
            clube = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return clube;

    }

    @Override
    public Collection<Clube> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Clube> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM CLUBE ";

        //precisa fazer filtro para listagem
        if(criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }
        
        //abre a conexao com o banco
        Banco.conectar();
        
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //Varre todo o resultado da consulta e coloca cada registro dentro
        //de um objeto e coloca o objeto dentro da coleção
        while(rs.next()) {
            //criar o objeto
            clube = new Clube();
            
            //mover os dados do resultSet para o objeto proprietário
            clube = new Clube();
            clube.setSiglaClube(rs.getString("siglaClube"));
            clube.setNomeClube(rs.getString("nomeClube"));
            clube.setEstado(rs.getString("estado"));
            clube.setPaisOrigem(rs.getString("paisOrigem"));
            
            //move o objeto para a coleção
            lista.add(clube);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}
