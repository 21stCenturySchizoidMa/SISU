package Main;

import Menus.MenuSecundario;
import Menus.Menuprincipal;
import Notas.CalcularNotas;
import SistemaDeLogin.UsuariosCadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menuprincipal menu = new Menuprincipal();
        MenuSecundario tela = new MenuSecundario();
        UsuariosCadastro sistema = new UsuariosCadastro();
        CalcularNotas calc = new CalcularNotas();
        String opcao;

        boolean sair = false;

        do {
            opcao = menu.ExibirMenu();

            switch (opcao) {
                case "1":
                    System.out.print("\nDigite seu login: ");
                    String login = menu.getScanner().next();
                    System.out.print("Digite sua senha: ");
                    String senha = menu.getScanner().next();

                    if (sistema.verificarUsuario(login, senha)) {
                        System.out.println("\nBem-vindo ao sistema!");
                        String subOpcao;
                        do {
                            subOpcao = tela.Exibirtela();

                            switch (subOpcao) {
                                case "1":
                                    System.out.println("\nLista de cursos e suas informações:");
                                    System.out.printf(
                                            "Medicina (Campus Caruaru): 859,08 - Vagas: 40 - Duração: 12 semestres%n" +
                                                    "Medicina (Campus Recife): 827,78 - Vagas: 250 - Duração: 12 semestres%n" +
                                                    "Ciência da Computação (Campus Recife): 802,68 - Vagas: 60 - Duração: 8 semestres%n" +
                                                    "Sistemas de Informação (Campus Recife): 798,97 - Vagas: 60 - Duração: 8 semestres%n" +
                                                    "Engenharia da Computação (Campus Recife): 786,12 - Vagas: 60 - Duração: 10 semestres%n" +
                                                    "Direito (Campus Recife) Matutino: 760,16; Noturno: 745,24 - Vagas: 90 - Duração: 10 semestres%n" +
                                                    "Engenharia Biomédica: 743,67 - Vagas: 40 - Duração: 10 semestres%n" +
                                                    "Artes Visuais: 740,95 - Vagas: 40 - Duração: 8 semestres%n" +
                                                    "Odontologia: 739,65 - Vagas: 50 - Duração: 10 semestres%n"
                                    );
                                    break;

                                case "2":
                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("Calcule sua média ponderada.");
                                    System.out.print("\nDigite o campus (recife, agreste, vitoria): ");
                                    String campus = sc.next().toLowerCase();

                                    List<String> cursosDisponiveis = calc.CarregarDados(campus);

                                    if (cursosDisponiveis != null && !cursosDisponiveis.isEmpty()) {
                                        System.out.println("\nCursos disponíveis:");

                                        for (String curso : cursosDisponiveis) {
                                            System.out.println(curso);
                                        }
                                    } else {
                                        System.out.println("\nNenhum curso encontrado para o campus: " + campus);
                                        break; // Sai do case se não encontrou cursos
                                    }

                                    System.out.print("\nDigite o curso:");
                                    sc.nextLine();
                                    String curso = sc.nextLine();

                                    System.out.println("\nDigite suas notas:");

                                    System.out.print("\nNota de Redação: ");
                                    double nota1 = sc.nextDouble();

                                    System.out.print("Nota de Matemática e suas Tecnologias: ");
                                    double nota2 = sc.nextDouble();

                                    System.out.print("Nota de Linguagens, Código e suas Tecnologias: ");
                                    double nota3 = sc.nextDouble();

                                    System.out.print("Nota de Ciências Humanas e suas Tecnologias: ");
                                    double nota4 = sc.nextDouble();

                                    System.out.print("Nota de Ciências da Natureza e suas Tecnologias: ");
                                    double nota5 = sc.nextDouble();

                                    double media = calc.calcularNotas(nota1, nota2, nota3, nota4, nota5, curso);

                                    if (media > 0) {
                                        System.out.printf("\nSua média é: %.2f", media);
                                    } else {
                                        System.out.println("\nErro: Curso não encontrado.");
                                    }
                                    break;

                                case "3":
                                    for (UsuariosCadastro user : sistema.getUsuarios()) {
                                        if (user.getLogin().equals(login)) {
                                            System.out.println("\nInformações do usuário:");
                                            System.out.println("Nome: " + user.getNome());
                                            System.out.println("Email: " + user.getEmail());
                                            System.out.println("Idade: " + user.getIdade());
                                            System.out.println("Telefone: " + user.getTelefone());
                                        }
                                    }
                                    break;

                                case "4":
                                    System.out.println("Saindo do sistema...");
                                    sair = true;
                                    break;

                                default:
                                    System.out.println("\nOpção inválida!");
                                    break;
                            }
                        } while (!subOpcao.equals("4") && !sair);
                    }

                    else {
                        System.out.println("\nUsuário não encontrado!");
                    }
                    break;

                case "2":
                    System.out.print("\nCadastre seu login: ");
                    String novoLogin = menu.getScanner().next();
                    System.out.print("\nDigite sua senha: ");
                    String novaSenha = menu.getScanner().next();

                    sistema.adicionarNovoUsuario(novoLogin, novaSenha);
                    break;

                case "3":
                    System.out.println("\nSaindo...");
                    sair = true;
                    break;

                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        } while (!sair);
    }
}
