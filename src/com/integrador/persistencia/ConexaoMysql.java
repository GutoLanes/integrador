package com.integrador.persistencia;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoMysql {

    private String ip;
    private String porta;
    private String nomeBd;
    private String login;
    private String senha;
    private Connection conexao;

    public ConexaoMysql() {
        super();
    }

    public ConexaoMysql(String ip, String porta, String nomeBd, String login, String senha) {
        super();
        this.ip = ip;
        this.porta = porta;
        this.nomeBd = nomeBd;
        this.login = login;
        this.senha = senha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    // ABRIR CONEXÃO COM O BANCO
    public void abrirConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexao = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.ip+":"+this.porta+"/"+this.nomeBd+"?autoReconnect=true&useSSL=false",this.login, this.senha);
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Mensagem de erro no catch da conexão");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Mensagem de erro no outro catch da conexao");
        }
    }

    // FECHAR CONEXAO COM O BANCO
    public void fecharConexao() {
        try {
            // VERIFICA SE A CONEXÃO ESTÁ ABERTA, CASO ESTEJA FECHA
            if(!conexao.isClosed()) {
                conexao.close();
            }
            // CASO NÃO ESTEJA
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }






}
