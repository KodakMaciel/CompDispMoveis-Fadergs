package br.pro.pedro.barbershop;

public class Funcionario {

    public int idFunc;

    public String nomeFunc;

    public Funcionario() {

    }

    @Override
    public String toString() {
        return nomeFunc;
    }

    public int getId() {
        return idFunc;
    }

    public void setId(int id) {
        this.idFunc = idFunc;
    }

    public Funcionario(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nome) {
        this.nomeFunc = nomeFunc;
    }
}

