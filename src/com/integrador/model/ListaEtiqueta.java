package com.integrador.model;

public class ListaEtiqueta {
    private long idListaEtiqueta;
    private Lista lista;
    private Etiqueta etiqueta;


    public ListaEtiqueta() {
        super();
    }

    public ListaEtiqueta(long idListaEtiqueta, Lista lista, Etiqueta etiqueta) {
        this.idListaEtiqueta = idListaEtiqueta;
        this.lista = lista;
        this.etiqueta = etiqueta;
    }

    public long getIdListaEtiqueta() {
        return idListaEtiqueta;
    }

    public void setIdListaEtiqueta(long idListaEtiqueta) {
        this.idListaEtiqueta = idListaEtiqueta;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

}