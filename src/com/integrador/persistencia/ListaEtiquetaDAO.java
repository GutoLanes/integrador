package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Etiqueta;
import com.integrador.model.ListaEtiqueta;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ListaEtiquetaDAO{

    private ConexaoMysql conexao;

    public ListaEtiquetaDAO() {
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);

    }

    // salvar
    public ListaEtiqueta salvar(ListaEtiqueta listaEtiqueta) {
        // abrir conexao mysql
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO lista_etiqueta (id_lista_etiqueta, id_lista, id_etiqueta) VALUES(null, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setLong(1, listaEtiqueta.getLista().getIdLista());
            prepare.setLong(2, listaEtiqueta.getEtiqueta().getIdEtiqueta());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs =prepare.getGeneratedKeys();
            if(rs.next()) {
                long id = rs.getLong(1);
                listaEtiqueta.setIdListaEtiqueta(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // fechar a conexao com mysql
            this.conexao.fecharConexao();
        }
        return listaEtiqueta;
    }

    // editar
    public void editar(ListaEtiqueta listaEtiqueta) {
        // abrir conexao
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = "UPDATE listaEtiqueta SET id_lista=?, id_etiqueta=? WHERE id_listaEtiqueta=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setLong(1, listaEtiqueta.getLista().getIdLista());
            prepare.setLong(2, listaEtiqueta.getEtiqueta().getIdEtiqueta());
            prepare.setLong(3, listaEtiqueta.getIdListaEtiqueta());
            // executar a instrucao
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // fechar conexao
            this.conexao.fecharConexao();
        }
    }

    // excluir
    public void excluir(long id) {
        this.conexao.abrirConexao();
        String sqlDelete = "DELETE FROM listaEtiqueta WHERE id_listaEtiqueta=?";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlDelete);
            prepare.setLong(1, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            this.conexao.fecharConexao();
        }
    }

    // buscarTodos
    public List<ListaEtiqueta> buscarTodos() {
        this.conexao.abrirConexao();
        List<ListaEtiqueta> listaListaEtiqueta = new ArrayList<ListaEtiqueta>();
        ListaEtiqueta listaEtiqueta = null;
        String sqlBuscarTodos = "SELECT * FROM listaEtiqueta";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                listaEtiqueta = new ListaEtiqueta();
                listaEtiqueta.setIdListaEtiqueta(rs.getLong("id_listaEtiqueta"));
                listaEtiqueta.getLista().setIdLista(rs.getLong("id_lista"));
                listaEtiqueta.getEtiqueta().setIdEtiqueta(rs.getLong("id_etiqueta"));
                listaListaEtiqueta.add(listaEtiqueta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaListaEtiqueta;
    }

    // buscarTodos(PorId)
    public ListaEtiqueta buscarPorId(long id) {
        this.conexao.abrirConexao();
        ListaEtiqueta listaEtiqueta = null;
        String sqlBuscarPorId = "SELECT * FROM listaEtiqueta WHERE id_listaEtiqueta=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                listaEtiqueta = new ListaEtiqueta();
                listaEtiqueta.setIdListaEtiqueta(rs.getLong("id_listaEtiqueta"));
                listaEtiqueta.getLista().setIdLista(rs.getLong("id_lista"));
                listaEtiqueta.getEtiqueta().setIdEtiqueta(rs.getLong("id_etiqueta"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaEtiqueta;
    }

    public ArrayList<Etiqueta> buscarEtiquetaPorLista(long idLista) {
        this.conexao.abrirConexao();
        ArrayList<Etiqueta> listaEtiqueta = new ArrayList<>();
        String sqlBuscarPorId = "select e.* from lista_etiqueta le INNER JOIN etiqueta e ON le.id_etiqueta = e.id_etiqueta WHERE le.id_lista = ?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, idLista);

            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setIdEtiqueta(rs.getLong("id_etiqueta"));
                etiqueta.setCor(rs.getString("cor"));
                etiqueta.setNome(rs.getString("nome"));
                listaEtiqueta.add(etiqueta);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaEtiqueta;
    }
    //



}
