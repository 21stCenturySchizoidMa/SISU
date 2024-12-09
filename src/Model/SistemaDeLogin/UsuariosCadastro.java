package Model.SistemaDeLogin;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuariosCadastro extends Usuario {
    private static final String FILE_NAME = "USUARIOS.txt";
    private static ArrayList<UsuariosCadastro> usuario = new ArrayList<UsuariosCadastro>();

    public String escolha;
    public String novoDado;

    public UsuariosCadastro(String login, String senha) {
        super(login, senha);
    }

    public UsuariosCadastro() {
        carregarDados();
    }

    public void informacoesDoUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        this.setNome(sc.nextLine());
        System.out.print("Digite seu genero: ");
        this.setGenero(sc.nextLine());
        System.out.println("Digite seu cpf: ");
        this.setCpf(sc.nextLine());
        System.out.print("Digite sua idade: ");
        this.setIdade(sc.nextLine());
        System.out.print("Digite seu e-mail: ");
        this.setEmail(sc.nextLine());
        System.out.print("Digite seu telefone: ");
        this.setTelefone(sc.nextLine());
        System.out.println("Digite seu endereço: ");
        this.setEndereco(sc.nextLine());
    }

    public String toString() {
        return super.getLogin() + " | " + super.getSenha() + " | " +
                super.getNome() + " | " + super.getIdade() + " | " +
                super.getEmail() + " | " + super.getTelefone() + " | " +
                super.getEndereco();
    }

    public void atualizarInformacoesDoUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Você deseja atualizar um dado específico? (sim/nao): ");
        escolha = sc.nextLine();

        if (escolha.equalsIgnoreCase("sim")) {
            do {
                System.out.print("\nQual dado você deseja atualizar? (nome, idade, email, telefone)");
                novoDado = sc.nextLine();

                switch (novoDado.toLowerCase()) {
                    case "nome":
                        System.out.print("\nDigite seu novo nome: ");
                        this.setNome(sc.nextLine());
                        break;

                    case "genero":
                        System.out.print("\nDigite seu novo genero: ");
                        this.setGenero(sc.nextLine());
                        break;

                    case "cpf":
                        System.out.print("\nDigite seu novo cpf: ");
                        this.setCpf(sc.nextLine());
                        break;

                    case "idade":
                        System.out.print("Digite sua nova idade: ");
                        this.setIdade(sc.nextLine());
                        break;

                    case "email":
                        System.out.print("Digite seu novo e-mail: ");
                        this.setEmail(sc.nextLine());
                        break;

                    case "telefone":
                        System.out.print("Digite seu novo telefone: ");
                        this.setTelefone(sc.nextLine());
                        break;

                    case "endereco":
                        System.out.println("Digite seu novo endereço: ");
                        this.setEndereco(sc.nextLine());
                        break;

                    default:
                        System.out.println("Dado inválido. Tente novamente.");
                        continue;
                }

                System.out.print("\nVocê deseja atualizar outro dado? (sim/nao): ");
                escolha = sc.nextLine();
            } while (escolha.equalsIgnoreCase("sim"));
        } else if (escolha.equalsIgnoreCase("nao")) {
            System.out.print("\nDeseja atualizar todos os seus dados? (sim/nao): ");
            escolha = sc.nextLine();

            if (escolha.equalsIgnoreCase("sim")) {
                System.out.print("Digite seu novo nome: ");
                this.setNome(sc.nextLine());
                System.out.println("Digite seu novo genero: ");
                this.setGenero(sc.nextLine());
                System.out.println("Digite seu novo cpf: ");
                this.setCpf(sc.nextLine());
                System.out.print("Digite sua nova idade: ");
                this.setIdade(sc.nextLine());
                System.out.print("Digite seu novo e-mail: ");
                this.setEmail(sc.nextLine());
                System.out.print("Digite seu novo telefone: ");
                this.setTelefone(sc.nextLine());
                System.out.println("Digite seu novo endereço");
                this.setEndereco(sc.nextLine());
            } else {
                System.out.println("Nenhum dado foi alterado.");
            }
        } else {
            System.out.println("Opção inválida. Operação cancelada.");
            return;
        }

        salvarDados();
        System.out.println("Informações atualizadas com sucesso!");
    }

    public void adicionarNovoUsuario(String login, String senha) {
        if (verificarUsuarioExistente(login)) {
            System.out.println("Usuário existente!");
        } else {
            this.setLogin(login);
            this.setSenha(senha);
            informacoesDoUsuario();
            usuario.add(this);
            salvarDados();
            System.out.println("Conta cadastrada com sucesso!");
        }
    }

    public boolean verificarUsuario(String login, String senha) {
        for (UsuariosCadastro user : usuario) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUsuarioExistente(String login) {
        for (UsuariosCadastro user : usuario) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private static void carregarDados() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(" \\| ");
                if (dados.length >= 8) {
                    UsuariosCadastro usuarioNovo = new UsuariosCadastro(dados[0], dados[1]);
                    usuarioNovo.setNome(dados[2]);
                    usuarioNovo.setGenero(dados[3]);
                    usuarioNovo.setCpf(dados[4]);
                    usuarioNovo.setIdade(dados[5]);
                    usuarioNovo.setEmail(dados[6]);
                    usuarioNovo.setTelefone(dados[7]);
                    usuarioNovo.setEndereco(dados[8]);
                    usuario.add(usuarioNovo);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private static void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (UsuariosCadastro dado : usuario) {
                writer.write(dado.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public ArrayList<UsuariosCadastro> getUsuarios() {
        return usuario;
    }
}