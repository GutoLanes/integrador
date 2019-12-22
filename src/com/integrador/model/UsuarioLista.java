package com.integrador.model;

public class UsuarioLista {

    private long idUsuarioLista;
    private Usuario usuario;
    private Lista lista;

    public UsuarioLista() {
        super();
    }

    public UsuarioLista(long idUsuarioLista, Usuario usuario, Lista lista) {
        this.idUsuarioLista = idUsuarioLista;
        this.usuario = usuario;
        this.lista = lista;
    }

    public long getIdUsuarioLista() {
        return idUsuarioLista;
    }

    public void setIdUsuarioLista(long idUsuarioLista) {
        this.idUsuarioLista = idUsuarioLista;
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

}


