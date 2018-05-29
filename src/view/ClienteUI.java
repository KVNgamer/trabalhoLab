/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import java.util.List;
import util.Console;
import dao.ClienteDao;
import dao.impl_BD.ClienteDaoBd;
import model.Cliente;
import view.menu.ClienteMenu;

/**
 *
 * @author KEVIN
 */
public class ClienteUI {
    
    private ClienteDao clienteDao;

    public ClienteUI() {
        clienteDao = new ClienteDaoBd();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        deletarCliente();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        mostrarClientes();
                        break;
                    case ClienteMenu.OP_CONSULTAR:
                        consultarClientesPorNome();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);
    }

    private void cadastrarCliente() {
        String rg = Console.scanString("CPF: ");
        String nome = Console.scanString("Nome: ");
        String email = Console.scanString("Email: ");
        clienteDao.salvar(new Cliente(rg, nome,email));
        System.out.println("Cliente " + nome + " cadastrado com sucesso!");
    }

    public void mostrarClientes() {
        List<Cliente> listaClientes = clienteDao.listar();
        this.mostrarClientes(listaClientes);
    }

    private void deletarCliente() {
        String cpf = Console.scanString("cpf do Cliente a ser deletado: ");
        Cliente cli = clienteDao.procurarPorCpf(cpf);
        this.mostrarCliente(cli);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
            clienteDao.deletar(cli);
            System.out.println("cliente deletado com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }
    }

    private void atualizarCliente() {
        String cpf = Console.scanString("cpf do cliente a ser alterado: ");
        Cliente cli = clienteDao.procurarPorCpf(cpf);
        this.mostrarCliente(cli);
        System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
        String nome = Console.scanString("Nome: ");
        String email = Console.scanString("email: ");
        if (!nome.isEmpty()) {
            cli.setNome(nome);
        }
        if (!email.isEmpty()) {
            cli.setEmail(email);
        }

        clienteDao.atualizar(cli);
        System.out.println("Cliente " + nome + " atualizado com sucesso!");

    }

    private void consultarClientesPorNome() {
        String nome = Console.scanString("Nome: ");
        List<Cliente> listacli = clienteDao.listarPorNome(nome);
        this.mostrarClientes(listacli);

    }

    private void mostrarCliente(Cliente c) {
        System.out.println("-----------------------------");
        System.out.println("Paciente");
        System.out.println("RG: " + c.getCpf());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Email: "+ c.getEmail());
        System.out.println("-----------------------------");
    }

    private void mostrarClientes(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Pacientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "Cpf") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|Email"));
            for (Cliente cliente : listaClientes) {
                System.out.println(String.format("%-10s", cliente.getCpf()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getEmail()));
            }
        }
    }
}
