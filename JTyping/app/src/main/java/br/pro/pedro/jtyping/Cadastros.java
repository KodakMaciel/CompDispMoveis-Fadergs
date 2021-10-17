package br.pro.pedro.jtyping;

public class Cadastros {

    public  int id;
    public int idade;
    public  String nome, email, sexo;

    public Cadastros(){

    }

    public Cadastros(String nome, int idade, String email, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.sexo = sexo;
    }


    @Override
    public String toString() {
        return " Nome: " + nome + " \n Idade: " + idade + "\n Email: " + email + " \n Sexo: " + sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
