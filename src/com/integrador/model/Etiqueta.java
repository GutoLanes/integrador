package com.integrador.model;

import java.util.ArrayList;

public class Etiqueta {
    private long idEtiqueta;
    private String nome;
    private String cor;
    private Lista lista;
    private Usuario usuario;


    public Etiqueta() {
        super();
    }

    public Etiqueta(long idEtiqueta, String nome, String cor, Lista lista, Usuario usuario) {
        this.idEtiqueta = idEtiqueta;
        this.nome = nome;
        this.cor = cor;
        this.lista = lista;
        this.usuario = usuario;
    }

    ArrayList<ListaEtiqueta> listaEtiquetas = new ArrayList<>();

    public long getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(long idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public ArrayList<ListaEtiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(ArrayList<ListaEtiqueta> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }

    @Override
    public String toString() {
        return "Etiqueta{" +
                "idEtiqueta=" + idEtiqueta +
                ", nome='" + nome + '\'' +
                ", cor='" + cor + '\'' +
                ", lista=" + lista +
                '}';
    }
}
