/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Jogador;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Viotti
 */
public class JogadoresDAO 
        implements DAO<Jogador> {

    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Jogador jogador; //meu MODEL   
    
    @Override
    public boolean insere(Jogador obj) throws SQLException {
        String sql = "INSERT INTO JOGADOR (nomeJog, posicao, clubeJog, paisOrigem) " +
                " VALUES (?,?,?,?)";
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Jogador com o comando INSERT
        pst.setString(1, obj.getNomeJog());
        pst.setString(2, obj.getPosicao());
        pst.setString(3, obj.getClubeJog());
        pst.setString(4, obj.getPaisOrigem());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Jogador obj) throws SQLException {
        String sql = "DELETE FROM JOGADOR WHERE idJog = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getIdJog());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Jogador obj) throws SQLException {
        String sql = "UPDATE JOGADOR SET nomeJog = ?, posicao = ?, "
                + "clubeJog = ?, paisOrigem = ? "
                + "WHERE idJog = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setString(1, obj.getNomeJog());
        pst.setString(2, obj.getPosicao());
        pst.setString(3, obj.getClubeJog());
        pst.setString(4, obj.getPaisOrigem());
        pst.setInt(5, obj.getIdJog());
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
    public Jogador buscaID(Jogador obj) throws SQLException {
        String sql = "SELECT * FROM JOGADOR "
                + "WHERE idJog = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Jogador com o comando UPDATE
        pst.setInt(1, obj.getIdJog());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //mover os dados(campos da tab) do resultSet para o objeto proprietário
            jogador = new Jogador();
            jogador.setIdJog(rs.getInt("idJog"));
            jogador.setNomeJog(rs.getString("nomeJog"));
            jogador.setPosicao(rs.getString("posicao"));
            jogador.setClubeJog(rs.getString("clubeJog"));
            jogador.setPaisOrigem(rs.getString("paisOrigem"));
        }
        else {
            //não encontrou o registro solicitado
            jogador = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return jogador;

    }

    @Override
    public Collection<Jogador> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Jogador> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM JOGADOR";

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
            jogador = new Jogador();
            
            //mover os dados do resultSet para o objeto proprietário
            jogador.setIdJog(rs.getInt("idJog"));
            jogador.setNomeJog(rs.getString("nomeJog"));
            jogador.setPosicao(rs.getString("posicao"));
            jogador.setClubeJog(rs.getString("clubeJog"));
            jogador.setPaisOrigem(rs.getString("paisOrigem"));
            //move o objeto para a coleção
            lista.add(jogador);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}
