package com.integrador.model;

public class Itens {

    private long idItens;
    private String statusItens;
    private String nome;
    private Lista lista;
    private Usuario usuario;

    public Itens(){
        super();
    }

    public Itens(long idItens, String statusItens, String nome, Lista lista, Usuario usuario) {
        this.idItens = idItens;
        this.statusItens = statusItens;
        this.nome = nome;
        this.lista = lista;
        this.usuario = usuario;
    }

    public long getIdItens() {
        return idItens;
    }

    public void setIdItens(long idItens) {
        this.idItens = idItens;
    }

    public String getStatusItens() {
        return statusItens;
    }

    public void setStatusItens(String statusItens) {
        this.statusItens = statusItens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    @Override
    public String toString() {
        return "Itens{" +
                "idItens=" + idItens +
                ", statusItens='" + statusItens + '\'' +
                ", nome='" + nome + '\'' +
                ", lista=" + lista +
                '}';
    }
}
