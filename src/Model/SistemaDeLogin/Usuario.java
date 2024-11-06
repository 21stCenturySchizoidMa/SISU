package Model.SistemaDeLogin;

public class Usuario {
    protected String login;
    protected String senha;
    protected String nome, idade, email, telefone;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {}

    public void informacoesDoUsuario() {
        System.out.println("Informações do Usuário");
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLogin(){
        return login;
    }

    public String getSenha(){
        return senha;
    }
}
