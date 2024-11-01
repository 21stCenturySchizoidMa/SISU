package SistemaDeLogin;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuariosCadastro {
    private static final String FILE_NAME = "USUARIOS.txt";
    private static ArrayList<UsuariosCadastro> usuario;

    private String login;
    private String senha;
    private String nome, idade, email, telefone;

    public UsuariosCadastro(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsuariosCadastro() {
        usuario = new ArrayList<>();
        carregarDados();
    }

    public void informacoesDoUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        nome = sc.nextLine();
        System.out.print("Digite sua idade: ");
        idade = sc.nextLine();
        System.out.print("Digite seu e-mail: ");
        email = sc.nextLine();
        System.out.print("Digite seu telefone: ");
        telefone = sc.nextLine();
    }

    public String getNome() { return nome; }
    public String getIdade() { return idade; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    // Atualizamos o toString para adicionar um delimitador claro
    @Override
    public String toString() {
        return login + " | " + senha + " | " + nome + " | " + idade + " | " + email + " | " + telefone;
    }

    public void adicionarNovoUsuario(String login, String senha) {
        if (verificarUsuarioExistente(login)) {
            System.out.println("Usuário existente!");
        }

        else {
            this.login = login;
            this.senha = senha;
            informacoesDoUsuario();
            usuario.add(this);
            salvarDados();
            System.out.println("Conta cadastrada com sucesso!");
        }
    }

    public boolean verificarUsuario(String login, String senha) {
        for (UsuariosCadastro user : usuario) {
            if (user.login.equals(login) && user.senha.equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUsuarioExistente(String login) {
        for (UsuariosCadastro user : usuario) {
            if (user.login.equals(login)) {
                return true;
            }
        }
        return false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Modificação no carregarDados para verificar o delimitador " | "
    private static void carregarDados() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;  // Retorna sem erro se o arquivo não existir
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(" \\| ");
                if (dados.length >= 6) {
                    UsuariosCadastro usuarioNovo = new UsuariosCadastro(dados[0], dados[1]);
                    usuarioNovo.nome = dados[2];
                    usuarioNovo.idade = dados[3];
                    usuarioNovo.email = dados[4];
                    usuarioNovo.telefone = dados[5];
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
