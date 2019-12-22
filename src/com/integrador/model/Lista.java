package com.integrador.model;
import java.util.ArrayList;

public class Lista {

    private long idLista;
    private String nome;
    private String descricao;
    private String cor;
    private ArrayList<Etiqueta> etiquetaArrayList;
    private ArrayList<Itens> itensArrayList;
    private Usuario usuario;

    public Lista() {
        super();
    }

    public Lista(long idLista, String nome, String descricao, String cor, Usuario usuario, ArrayList<UsuarioLista> usuarioListas, ArrayList<ListaEtiqueta> listaEtiquetas, ArrayList<Itens> itens) {
        this.idLista = idLista;
        this.nome = nome;
        this.descricao = descricao;
        this.cor = cor;
        this.usuario = usuario;
        this.usuarioListas = usuarioListas;
        this.listaEtiquetas = listaEtiquetas;
        this.itens = itens;
    }

    ArrayList<UsuarioLista> usuarioListas = new ArrayList<>();
    ArrayList<ListaEtiqueta> listaEtiquetas = new ArrayList<>();
    ArrayList<Itens> itens = new ArrayList<>();

    public long getIdLista() {
        return idLista;
    }

    public void setIdLista(long idLista) {
        this.idLista = idLista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Etiqueta> getEtiquetaArrayList() {
        return etiquetaArrayList;
    }

    public void setEtiquetaArrayList(ArrayList<Etiqueta> etiquetaArrayList) {
        this.etiquetaArrayList = etiquetaArrayList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public ArrayList<UsuarioLista> getUsuarioListas() {
        return usuarioListas;
    }

    public void setUsuarioListas(ArrayList<UsuarioLista> usuarioListas) {
        this.usuarioListas = usuarioListas;
    }

    public ArrayList<ListaEtiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(ArrayList<ListaEtiqueta> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }

    public ArrayList<Itens> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Itens> itens) {
        this.itens = itens;
    }

    public ArrayList<Itens> getItensArrayList() {
        return itensArrayList;
    }

    public void setItensArrayList(ArrayList<Itens> itensArrayList) {
        this.itensArrayList = itensArrayList;
    }

    //    @Override
//    public String toString() {
//        return "Lista{" +
//                "idLista=" + idLista +
//                ", nome='" + nome + '\'' +
//                ", descricao='" + descricao + '\'' +
//                ", cor='" + cor + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Lista{" +
                "idLista=" + idLista +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", cor='" + cor + '\'' +
                ", etiquetaArrayList=" + etiquetaArrayList +
                ", itensArrayList=" + itensArrayList +
                ", usuario=" + usuario +
                '}';
    }
}