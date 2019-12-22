package com.integrador.apresentacao;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import com.integrador.model.*;
import com.integrador.persistencia.*;


public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Usuario u = new Usuario();
        Lista l = new Lista();
        UsuarioDAO uDAO = new UsuarioDAO();
        int idEscolhido;
        System.out.println("Digite 1 para cadastrar-se;");
        System.out.println("Digite 2 para fazer login");
        int numeroEntrada = teclado.nextInt();
        switch (numeroEntrada) {
            case 1:
                System.out.println("Digite seu nome:");
                String nomeUsuario = teclado.next();
                System.out.println("Digite seu e-mail:");
                String email = teclado.next();
                System.out.println("Escolha uma senha");
                String senha = teclado.next();
                u = new Usuario(0, nomeUsuario, email, senha, null, null, null, null);
                uDAO = new UsuarioDAO();
                u = uDAO.salvar(u);
                break;
            case 2:
                System.out.println("Digite seu e-mail");
                String emailLogin = teclado.next();
                System.out.println("Digite sua senha");
                String senhaLogin = teclado.next();

                u = uDAO.loginUsuario(emailLogin, senhaLogin);

                if(u == null){
                    System.out.println("Login inválido");
                    break;
                }else {
                    System.out.println("Você está logado");
                }

                do {
                    System.out.println("O que desejas fazer?");
                    System.out.println("Digite 2 para criar uma lista;");
                    System.out.println("Digite 3 para editar uma lista;");
                    System.out.println("Digite 4 para excluir uma lista;");
                    System.out.println("Digite 5 para visualizar as listas.");
                    System.out.println("Digite 6 para visualizar uma lista em específico.");
                    System.out.println("Digite 7 para criar um item");
                    System.out.println("Digite 8 para excluir um item");
                    System.out.println("Digite 9 para visualizar todos os itens");
                    System.out.println("Digite 10 para criar uma etiqueta");
                    System.out.println("Digite 11 para excluir uma etiqueta");
                    System.out.println("Digite 12 para visualizar todas as etiquetas");
                    System.out.println("Digite 13 para parar de usar.");
                    numeroEntrada = teclado.nextInt();
                    switch (numeroEntrada) {
                        case 2:
                            System.out.println("Digite o nome da lista");
                            String nomeLista = teclado.nextLine();
                            nomeLista = teclado.nextLine();
                            System.out.println("Digite a descrição da lista");
                            String descricaoLista = teclado.nextLine();
                            System.out.println("Digite a cor da lista");
                            String corLista = teclado.next();

                            l = new Lista(0, nomeLista, descricaoLista, corLista, u, null, null, null);
                            ListaDAO lDao = new ListaDAO();
                            lDao.salvar(l);
                            System.out.println("Lista criada com sucesso! Detalhes sobre a mesma:");
                            System.out.println(l);
                            break;
                        case 3:
                            lDao = new ListaDAO();
                            List<Lista> listaListas = lDao.buscarTodos();
                            for (Lista lista : listaListas) {
                                System.out.println(lista);
                            }

                            System.out.println("Digite o id da lista que deseja editar");
                            idEscolhido = teclado.nextInt();


                            System.out.println("Digite um novo nome para a lista");
                            nomeLista = teclado.next();
                            System.out.println("Digite uma nova descrição para a lista");
                            descricaoLista = teclado.next();
                            System.out.println("Digite uma nova cor para a lista");
                            corLista = teclado.next();
                            l = new Lista(idEscolhido, nomeLista, descricaoLista, corLista, u, null, null, null);
                            lDao = new ListaDAO();
                            lDao.editar(l);
                            System.out.println(l);
                            break;
                        case 4:
                            lDao = new ListaDAO();
                            listaListas = lDao.buscarTodos();
                            for (Lista lista : listaListas) {
                                System.out.println(lista);
                            }

                            System.out.println("Digite o id da lista que deseja excluir");
                            idEscolhido = teclado.nextInt();

                            lDao = new ListaDAO();
                            lDao.excluir(idEscolhido);
                            System.out.println("A lista de id " + idEscolhido + " foi excluída com sucesso!");

                            break;
                        case 5:
                            lDao = new ListaDAO();
                            listaListas = lDao.buscarTodos();
                            for (Lista lista : listaListas) {
                                System.out.println(lista);
                            }
                            break;

                        case 6:
                            lDao = new ListaDAO();
                            listaListas = lDao.buscarTodos();
                            for (Lista lista : listaListas) {
                                System.out.println(lista);
                            }
                            System.out.println("Digite o id da lista que deseja visualizar");
                            idEscolhido = teclado.nextInt();
                            lDao = new ListaDAO();
                            Lista lista = lDao.buscarPorId(idEscolhido);
                            System.out.println(lista);
                            break;
                        case 7:
                            lDao = new ListaDAO();
                            listaListas = lDao.buscarTodos();
                            for (Lista lista1 : listaListas) {
                                System.out.println(lista1);
                            }
                            System.out.println("Digite o id da lista que deseja criar um item");
                            idEscolhido = teclado.nextInt();
                            lDao = new ListaDAO();
                            lista = lDao.buscarPorId(idEscolhido);
                            System.out.println(lista);

                            System.out.println("Digite o nome que deseja atribuir ao item");
                            String nomeItem = teclado.next();
                            System.out.println("Digite o status do item");
                            String statusItem = teclado.next();

                            Itens i = new Itens(0, statusItem, nomeItem, l, u);
                            ItensDAO iDao = new ItensDAO();
                            i.setLista(lista);
                            i.setUsuario(u);
                            i = iDao.salvar(i);
                            System.out.println("Item " + nomeItem + " criado com sucesso, detalhes sobre o mesmo:");
                            System.out.println(i);

                            ListaItens listaItens = new ListaItens();
                            listaItens.setItens(i);
                            listaItens.setLista(lista);
                            ListaItensDAO listaItensDAO = new ListaItensDAO();
                            listaItensDAO.salvar(listaItens);


//                            System.out.println("Digite o nome da etiqueta");
//                            String nomeEtiqueta = teclado.next();
//                            System.out.println("Digite a cor da etiqueta");
//                            String corEtiqueta = teclado.next();
//                            Etiqueta e = new Etiqueta(0, nomeEtiqueta, corEtiqueta, l, u);
//                            EtiquetaDAO eDao = new EtiquetaDAO();
//                            e.setLista(lista);
//                            e.setUsuario(u);
//                            e = eDao.salvar(e);
//                            ListaEtiqueta listaEtiqueta = new ListaEtiqueta();
//                            listaEtiqueta.setEtiqueta(e);
//                            listaEtiqueta.setLista(lista);
//                            ListaEtiquetaDAO listaEtiquetaDAO = new ListaEtiquetaDAO();
//                            listaEtiquetaDAO.salvar(listaEtiqueta);
//                            System.out.println("Etiqueta " + nomeEtiqueta + " criada com sucesso, detalhes sobre a mesma:");
//
//                            System.out.println(e);

                            break;
                        case 8:
                            iDao = new ItensDAO();
                            List<Itens> listaListaItens = iDao.buscarTodos();
                            for (Itens itens : listaListaItens) {
                                System.out.println(itens);
                            }

                            System.out.println("Digite o id do item que deseja excluir:");
                            idEscolhido = teclado.nextInt();

                            iDao = new ItensDAO();
                            iDao.excluir(idEscolhido);
                            System.out.println("O item de id " + idEscolhido + " foi excluído com sucesso!");
                            System.out.println("Lista atualizada de itens:");
                            iDao = new ItensDAO();
                            listaListaItens = iDao.buscarTodos();
                            for (Itens itens : listaListaItens) {
                                System.out.println(itens);

                            }
                            break;

                        case 9:
                            iDao = new ItensDAO();
                            listaListaItens = iDao.buscarTodos();
                            for (Itens itens : listaListaItens) {
                                System.out.println(itens);
                            }
                            break;
                        case 10:
                            lDao = new ListaDAO();
                            listaListas = lDao.buscarTodos();
                            for (Lista lista1 : listaListas) {
                                System.out.println(lista1);
                            }
                            System.out.println("Digite o id da lista que deseja criar uma etiqueta");
                            idEscolhido = teclado.nextInt();
                            lDao = new ListaDAO();
                            lista = lDao.buscarPorId(idEscolhido);
                            System.out.println(lista);


                            System.out.println("Digite o nome da etiqueta");
                            String nomeEtiqueta = teclado.next();
                            System.out.println("Digite a cor da etiqueta");
                            String corEtiqueta = teclado.next();
                            Etiqueta e = new Etiqueta(0, nomeEtiqueta, corEtiqueta, l, u);
                            EtiquetaDAO eDao = new EtiquetaDAO();
                            e.setLista(lista);
                            e.setUsuario(u);
                            e = eDao.salvar(e);
                            ListaEtiqueta listaEtiqueta = new ListaEtiqueta();
                            listaEtiqueta.setEtiqueta(e);
                            listaEtiqueta.setLista(lista);
                            ListaEtiquetaDAO listaEtiquetaDAO = new ListaEtiquetaDAO();
                            listaEtiquetaDAO.salvar(listaEtiqueta);
                            System.out.println("Etiqueta " + nomeEtiqueta + " criada com sucesso, detalhes sobre a mesma:");

                            System.out.println(e);
                            break;
                        case 11:
                            eDao = new EtiquetaDAO();
                            List<Etiqueta> listaEtiquetas = eDao.buscarTodos();
                            for (Etiqueta etiqueta : listaEtiquetas) {
                                System.out.println(etiqueta);
                            }

                            System.out.println("Digite o id da etiqueta que deseja excluir");
                            idEscolhido = teclado.nextInt();

                            eDao = new EtiquetaDAO();
                            eDao.excluir(idEscolhido);
                            System.out.println("A etiqueta de id " + idEscolhido + " foi excluída com sucesso!");
                            System.out.println("Lista atualizada de etiquetas:");

                            eDao = new EtiquetaDAO();
                            listaEtiquetas = eDao.buscarTodos();
                            for (Etiqueta etiqueta : listaEtiquetas) {
                                System.out.println(etiqueta);
                            }
                            break;
                        case 12:
                            eDao = new EtiquetaDAO();
                            listaEtiquetas = eDao.buscarTodos();
                            for (Etiqueta etiqueta : listaEtiquetas) {
                                System.out.println(etiqueta);
                            }
                            break;
                    }


                } while (numeroEntrada != 13);
                System.out.println("Encerrando o App...");

        }
    }
}


