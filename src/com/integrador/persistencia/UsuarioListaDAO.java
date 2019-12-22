package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.UsuarioLista;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UsuarioListaDAO{

    private ConexaoMysql conexao;

    public UsuarioListaDAO() {
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);

    }

    // salvar
    public UsuarioLista salvar(UsuarioLista usuarioLista) {
        // abrir conexao mysql
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO usuarioLista VALUES(null, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setLong(1, usuarioLista.getUsuario().getIdUsuario());
            prepare.setLong(2, usuarioLista.getLista().getIdLista());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs =prepare.getGeneratedKeys();
            if(rs.next()) {
                long id = rs.getLong(1);
                usuarioLista.setIdUsuarioLista(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // fechar a conexao com mysql
            this.conexao.fecharConexao();
        }
        return usuarioLista;
    }

    // editar
        public void editar(UsuarioLista usuarioLista) {
        // abrir conexao
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = "UPDATE usuarioLista SET id_usuario=?, id_lista=? WHERE id_usuarioLista=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setLong(1, usuarioLista.getUsuario().getIdUsuario());
            prepare.setLong(2, usuarioLista.getLista().getIdLista());
            prepare.setLong(3, usuarioLista.getIdUsuarioLista());
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
        String sqlDelete = "DELETE FROM usuarioLista WHERE id_usuarioLista=?";
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
    public List<UsuarioLista> buscarTodos() {
        this.conexao.abrirConexao();
        List<UsuarioLista> listaUsuarioLista = new ArrayList<UsuarioLista>();
        UsuarioLista usuarioLista = null;
        String sqlBuscarTodos = "SELECT * FROM usuarioLista";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                usuarioLista = new UsuarioLista();
                usuarioLista.setIdUsuarioLista(rs.getLong("id_usuarioLista"));
                usuarioLista.getUsuario().setIdUsuario(rs.getLong("id_usuario"));
                usuarioLista.getLista().setIdLista(rs.getLong("id_lista"));
                listaUsuarioLista.add(usuarioLista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarioLista;
    }

    // buscarTodos(PorId)
    public UsuarioLista buscarPorId(long id) {
        this.conexao.abrirConexao();
        UsuarioLista usuarioLista = null;
        String sqlBuscarPorId = "SELECT * FROM usuarioLista WHERE id_usuarioLista=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                usuarioLista = new UsuarioLista();
                usuarioLista.setIdUsuarioLista(rs.getLong("id_usuarioLista"));
                usuarioLista.getUsuario().setIdUsuario(rs.getLong("id_usuario"));
                usuarioLista.getLista().setIdLista(rs.getLong("id_lista"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return usuarioLista;
    }



    //



}
