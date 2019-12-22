package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Usuario;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class UsuarioDAO{

    private ConexaoMysql conexao;


    public UsuarioDAO(){
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);

    }

    // salvar
    public Usuario salvar(Usuario usuario) {
        // abrir conexao mysql
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO usuario VALUES(null, ?, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, usuario.getNome());
            prepare.setString(2, usuario.getEmail());
            prepare.setString(3, usuario.getSenha());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs =prepare.getGeneratedKeys();
            if(rs.next()) {
                long id = rs.getLong(1);
                usuario.setIdUsuario(id);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // fechar a conexao com mysql
            this.conexao.fecharConexao();

        }
        return usuario;
    }

    // editar
    public void editar(Usuario usuario) {
        // abrir conexao
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = " = UPDATE usuario SET nome=?, email?, senha=? WHERE id_usuario=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setString(1, usuario.getNome());
            prepare.setString(2, usuario.getEmail());
            prepare.setString(3, usuario.getSenha());
            prepare.setLong(4, usuario.getIdUsuario());
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
        String sqlDelete = "DELETE FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlDelete);
            prepare.setLong(0, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            this.conexao.fecharConexao();
        }
    }

    // buscarTodos
    public List<Usuario> buscarTodos() {
        this.conexao.abrirConexao();
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario usuario = null;
        String sqlBuscarTodos = "SELECT * FROM usuario";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public Usuario loginUsuario(String email, String senha) {
        this.conexao.abrirConexao();
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario usuario = null;
        String sqlBuscarTodos = "SELECT * FROM usuario WHERE email = ? and senha =?";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            prepare.setString(1, email);
            prepare.setString(2, senha);

            ResultSet rs = prepare.executeQuery();

            if (rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                System.out.println("Você está logado");
            }
        } catch (SQLException e) {
            System.out.println("Login inválido");
            e.printStackTrace();
        }
        return usuario;
    }


    // buscarTodos(PorId)
    public Usuario buscarPorId(long id) {
        this.conexao.abrirConexao();
        Usuario usuario = null;
        String sqlBuscarPorId = "SELECT * FROM usuario WHERE id_usuario=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return usuario;
    }



    //



}
