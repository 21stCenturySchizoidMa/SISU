package Model.SistemaDeLogin;

public class Usuario {
    private String login, senha;
    private String nome, idade, telefone, endereco, email, genero, cpf;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;

    }

    public Usuario() {}

    public void informacoesDoUsuario() {
        System.out.println("Informações do Usuário");
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdade() { return idade; }
    public void setIdade(String idade) { this.idade = idade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
