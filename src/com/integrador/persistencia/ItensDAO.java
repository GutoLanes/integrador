package com.integrador.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.integrador.model.Itens;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ItensDAO {

    private ConexaoMysql conexao;

    public ItensDAO() {
        this.conexao = new ConexaoMysql(ConstantesLogin.IP_BD, ConstantesLogin.PORTA_BD, ConstantesLogin.NOME_BD, ConstantesLogin.LOGIN_BD, ConstantesLogin.SENHA_BD);
    }

    // MÉTODO PARA SALVAR VALORES NO BANCO
    public Itens salvar(Itens itens) {
        //ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        // criar a instrucao sql de insert
        String sqlInsert = "INSERT INTO itens (id_itens, statusItens, nome, id_lista, id_usuario) VALUES(NULL, ?, ?, ?, ?)";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, itens.getStatusItens());
            prepare.setString(2, itens.getNome());
            prepare.setLong(3, itens.getLista().getIdLista());
            prepare.setLong(4, itens.getUsuario().getIdUsuario());
            // executar essa instrucao
            prepare.executeUpdate();

            ResultSet rs = prepare.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                itens.setIdItens(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //FECHANDO A CONEXÃO COM O MYSQL APÓS SALVAR OS ITENS NECESSÁRIOS
            this.conexao.fecharConexao();
        }
        return itens;
    }

    // MÉTODO PARA EDITAR VALORES NO BANCO
    public void editar(Itens itens) {
        // ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        // criar a instrucao sql
        String sqlUpdate = "UPDATE itens SET nome=?, status=? WHERE id_itens=?";
        // preparar a instrucao para ser executada
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
            prepare.setString(1, itens.getNome());
            prepare.setString(2, itens.getStatusItens());
            prepare.setLong(3, itens.getIdItens());
            // executar a instrucao
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //FECHANDO CONEXÃO COM O BANCO APÓS FAZER AS ALTERAÇÕES NECESSÁRIAS
            this.conexao.fecharConexao();
        }
    }

    // MÉTODO PARA EXCLUIR VALORES NO BANCO
    public void excluir(long id) {
        //ABRINO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        String sqlDelete = "DELETE FROM itens WHERE id_itens=?";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlDelete);
            prepare.setLong(1, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //FECHANDO CONEXÃO COM O BANCO APÓS EXCLUIR OS ITENS NECESSÁRIOS
            this.conexao.fecharConexao();
        }
    }

    // MÉTODO PARA BUSCAR TODOS OS VALORES NO BANCO
    public List<Itens> buscarTodos() {
        // ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        List<Itens> listaItens = new ArrayList<Itens>();
        Itens itens = null;
        String sqlBuscarTodos = "SELECT * FROM itens";
        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);

            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                itens = new Itens();
                itens.setIdItens(rs.getLong("id_itens"));
                itens.setNome(rs.getString("nome"));
                itens.setStatusItens(rs.getString("statusItens"));
                listaItens.add(itens);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaItens;
    }

    // MÉTODO PARA BUSCAR VALORES ESPECÍFICOS DE ACORDO COM O ID
    public Itens buscarPorId(long id) {
        //ABRINDO CONEXÃO COM O MYSQL
        this.conexao.abrirConexao();
        Itens itens = null;
        String sqlBuscarPorId = "SELECT * FROM itens WHERE id_itens=?";

        try {
            PreparedStatement prepare = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
            prepare.setLong(1, id);

            ResultSet rs = prepare.executeQuery();

            if (rs.next()) {
                // EXISTE A LINHA E TEMOS QUE CONVERTER A LINHA PARA UM OBJETO USUARIO
                itens = new Itens();
                itens.setIdItens(rs.getLong("id_itens"));
                itens.setNome(rs.getString("nome"));
                itens.setStatusItens(rs.getString("status"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itens;
    }


}
