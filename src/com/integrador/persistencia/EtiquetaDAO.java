package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Etiqueta;
import com.integrador.model.ListaEtiqueta;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class EtiquetaDAO {

    private ConexaoMysql conexao;
    private ListaEtiquetaDAO listaEtiquetaDAO = new ListaEtiquetaDAO();

    public EtiquetaDAO() {
        //PASSA AS INFORMAÇÕES NECESSÁRIAS PARA LOGAR NO BANCO
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);
    }

    // MÉTODO PARA SALVAR NO BANCO
    public Etiqueta salvar(Etiqueta etiqueta) {
        // ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO etiqueta(id_etiqueta, nome, cor, id_usuario) VALUES(null, ?, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, etiqueta.getNome());
            prepare.setString(2, etiqueta.getCor());
            prepare.setLong(3, etiqueta.getUsuario().getIdUsuario());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs = prepare.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                etiqueta.setIdEtiqueta(id);
                ListaEtiqueta listaEtiqueta = new ListaEtiqueta();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //FECHANDO CONEXÃO COM O MYSQL APÓS TER REALIZADO AS ALTERAÇÕES NECESSÁRIAS
            this.conexao.fecharConexao();
        }
        return etiqueta;
    }

    // MÉTODO PARA EDITAR VALORES NO BANCO
    public void editar(Etiqueta etiqueta) {
        // ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = "UPDATE etiqueta SET nome=?, cor=?, id_usuario = ? WHERE id_etiqueta=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setString(1, etiqueta.getNome());
            prepare.setString(2, etiqueta.getCor());
            prepare.setLong(3, etiqueta.getUsuario().getIdUsuario());
            prepare.setLong(4, etiqueta.getIdEtiqueta());
            // executar a instrucao
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //FECHANDO CONEXÃO COM O MYSQL APÓS TER REALIZADO AS ALTERAÇÕES NECESSÁRIAS
            this.conexao.fecharConexao();
        }
    }

    // MÉTODO PARA EXCLUIR VALORES NO BANCO
    public void excluir(long id) {
        //ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        String sqlDelete = "DELETE FROM etiqueta WHERE id_etiqueta=?";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlDelete);
            prepare.setLong(1, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //FECHANDO CONEXÃO COM O MYSQL APÓS TER EXCLUÍDO O QUE DEVERIA EXCLUIR
            this.conexao.fecharConexao();
        }
    }

    // MÉTODO PARA BUSCAR TODOS OS VALORES NO BANCO
    public List<Etiqueta> buscarTodos() {
        //ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        List<Etiqueta> listaEtiquetas = new ArrayList<Etiqueta>();
        Etiqueta etiqueta = null;
        String sqlBuscarTodos = "SELECT * FROM etiqueta";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                etiqueta = new Etiqueta();
                etiqueta.setIdEtiqueta(rs.getLong("id_etiqueta"));
                etiqueta.setNome(rs.getString("nome"));
                etiqueta.setCor(rs.getString("cor"));
                listaEtiquetas.add(etiqueta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEtiquetas;
    }

    // MÉTODO PARA BUSCAR VALORES ESPECÍFICOS DE ACORDO COM O ID
    public Etiqueta buscarPorId(long id) {
        //ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        Etiqueta etiqueta = null;
        String sqlBuscarPorId = "SELECT * FROM etiqueta WHERE id_etiqueta=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if (rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                etiqueta = new Etiqueta();
                etiqueta.setIdEtiqueta(rs.getLong("id_etiqueta"));
                etiqueta.setNome(rs.getString("nome"));
                etiqueta.setCor((rs.getString("cor")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return etiqueta;
    }


}
