package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Etiqueta;
import com.integrador.model.Itens;
import com.integrador.model.Lista;
import com.integrador.model.ListaItens;
import com.integrador.model.ListaEtiqueta;
import com.integrador.model.Usuario;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class ListaDAO{

    private ConexaoMysql conexao;
    private ListaEtiquetaDAO listaEtiqueta = new ListaEtiquetaDAO();
    private ListaItensDAO listaItens = new ListaItensDAO();

    public ListaDAO() {
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);

    }

    // salvar
    public Lista salvar(Lista lista) {
        // abrir conexao mysql
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO lista VALUES(null, ?, ?, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, lista.getNome());
            prepare.setString(2, lista.getDescricao());
            prepare.setString(3, lista.getCor());
            prepare.setLong(4, lista.getUsuario().getIdUsuario());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs =prepare.getGeneratedKeys();
            if(rs.next()) {
                long id = rs.getLong(1);
                lista.setIdLista(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // fechar a conexao com mysql
            this.conexao.fecharConexao();
        }
        return lista;
    }

    public void editar(Lista lista) {
        // abrir conexao
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = "UPDATE lista SET nome=?, descricao=?, cor=?, id_usuario=? WHERE id_lista=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setString(1, lista.getNome());
            prepare.setString(2, lista.getDescricao());
            prepare.setString(3, lista.getCor());
            prepare.setLong(4, lista.getUsuario().getIdUsuario());
            prepare.setLong(5, lista.getIdLista());
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
        String sqlDelete = "DELETE FROM lista WHERE id_lista=?";
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
    public List<Lista> buscarTodos() {
        this.conexao.abrirConexao();
        List<Lista> listaListas = new ArrayList<Lista>();
        Lista lista = null;
        String sqlBuscarTodos = "select * from lista li INNER JOIN usuario u WHERE li.id_usuario = u.id_usuario";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                lista = new Lista();
                lista.setUsuario(new Usuario());
                lista.getUsuario().setIdUsuario(rs.getLong("id_usuario"));
                lista.getUsuario().setEmail(rs.getString("email"));
                lista.getUsuario().setNome(rs.getString("nome"));
                lista.setIdLista(rs.getLong("id_lista"));
                lista.setNome(rs.getString("nome"));
                lista.setDescricao(rs.getString("descricao"));
                lista.setCor(rs.getString("cor"));

                
                lista.setEtiquetaArrayList(listaEtiqueta.buscarEtiquetaPorLista(lista.getIdLista()));
                lista.setItensArrayList(listaItens.buscarItensPorLista(lista.getIdLista()));

                listaListas.add(lista);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaListas;
    }

    // buscarTodos(PorId)
    public Lista buscarPorId(long id) {
        this.conexao.abrirConexao();
        Lista lista = null;
        String sqlBuscarPorId = "SELECT * FROM lista WHERE id_lista=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if(rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                lista = new Lista();
                lista.setIdLista(rs.getLong("id_lista"));
                lista.setNome(rs.getString("nome"));
                lista.setDescricao(rs.getString("descricao"));
                lista.setCor(rs.getString("cor"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista;
    }



    //



}
