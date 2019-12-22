package com.integrador.model;

public class ListaItens {
    private long idListaItens;
    private Lista lista;
    private Itens itens;


    public ListaItens() {
        super();
    }


    public ListaItens(long idListaItens, Lista lista, Itens itens) {
        this.idListaItens = idListaItens;
        this.lista = lista;
        this.itens = itens;
    }

    public long getIdListaItens() {
        return idListaItens;
    }

    public void setIdListaItens(long idListaItens) {
        this.idListaItens = idListaItens;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }
}