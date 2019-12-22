package com.integrador.model;
import java.util.ArrayList;

public class Usuario {

    private long idUsuario;
    private String nome;
    private String email;
    private String senha;


    ArrayList<UsuarioLista> usuarioListas = new ArrayList<>();
    ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    ArrayList<Itens> itens = new ArrayList<>();
    ArrayList<Lista> listas = new ArrayList<>();

    public Usuario() {
        super();
    }


    public Usuario(long idUsuario, String nome, String email, String senha, ArrayList<UsuarioLista> usuarioListas, ArrayList<Etiqueta> etiquetas, ArrayList<Itens> itens, ArrayList<Lista> listas) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.usuarioListas = usuarioListas;
        this.etiquetas = etiquetas;
        this.itens = itens;
        this.listas = listas;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<UsuarioLista> getUsuarioListas() {
        return usuarioListas;
    }

    public void setUsuarioListas(ArrayList<UsuarioLista> usuarioListas) {
        this.usuarioListas = usuarioListas;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public ArrayList<Itens> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Itens> itens) {
        this.itens = itens;
    }

    public ArrayList<Lista> getListas() {
        return listas;
    }

    public void setListas(ArrayList<Lista> listas) {
        this.listas = listas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}