package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Itens;
import com.integrador.model.Lista;
import com.integrador.model.ListaItens;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ListaItensDAO{

    private ConexaoMysql conexao;

    public ListaItensDAO() {
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);

    }

    // salvar
    public ListaItens salvar(ListaItens listaItens) {
        // abrir conexao mysql
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO lista_itens (id_lista_itens, id_lista, id_itens) VALUES(null, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setLong(1, listaItens.getLista().getIdLista());
            prepare.setLong(2, listaItens.getItens().getIdItens());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs =prepare.getGeneratedKeys();
            if(rs.next()) {
                long id = rs.getLong(1);
                listaItens.setIdListaItens(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // fechar a conexao com mysql
            this.conexao.fecharConexao();
        }
        return listaItens;
    }

    // editar
    public void editar(ListaItens listaItens) {
        // abrir conexao
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = "UPDATE listaItens SET id_lista=?, id_itens=? WHERE id_itens=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setLong(1, listaItens.getLista().getIdLista());
            prepare.setLong(2, listaItens.getItens().getIdItens());
            prepare.setLong(3, listaItens.getIdListaItens());
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
        String sqlDelete = "DELETE FROM listaItens WHERE id_listaItens=?";
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
    public List<ListaItens> buscarTodos() {
        this.conexao.abrirConexao();
        List<ListaItens> listaListaItens = new ArrayList<ListaItens>();
        ListaItens listaItens = null;
        String sqlBuscarTodos = "SELECT * FROM listaItens";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                listaItens = new ListaItens();
                listaItens.setIdListaItens(rs.getLong("id_listaItens"));
                listaItens.getLista().setIdLista(rs.getLong("id_lista"));
                listaItens.getItens().setIdItens(rs.getLong("id_itens"));
                listaListaItens.add(listaItens);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaListaItens;
    }

    // buscarTodos(PorId)
    public ListaItens buscarPorId(long id) {
        this.conexao.abrirConexao();
        ListaItens listaItens = null;
        String sqlBuscarPorId = "SELECT * FROM listaItens WHERE id_listaItens=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                listaItens = new ListaItens();
                listaItens.setIdListaItens(rs.getLong("id_listaItens"));
                listaItens.getLista().setIdLista(rs.getLong("id_lista"));
                listaItens.getItens().setIdItens(rs.getLong("id_itens"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaItens;
    }

    public ArrayList<Itens> buscarItensPorLista(long idLista) {
        this.conexao.abrirConexao();
        ArrayList<Itens> listaItens = new ArrayList<>();
        String sqlBuscarPorId = "select e.* from lista_itens le RIGHT JOIN itens e ON le.id_itens = e.id_itens WHERE le.id_lista = ?";


        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, idLista);

            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                Itens itens = new Itens();
                itens.setIdItens(rs.getLong("id_itens"));
                itens.setNome(rs.getString("nome"));
                itens.setStatusItens(rs.getString("statusItens"));
                listaItens.add(itens);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaItens;
    }
    //



}
