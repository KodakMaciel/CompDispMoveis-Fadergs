package br.pro.pedro.barbershop;

public class Agenda {

    public int id;

    public String nome, hora, data;

    public Agenda() {

    }

    public Agenda(String nome, String data, String hora) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
    }

    @Override
    public String toString() { return nome + " - " + data + " - " + hora;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String data) {
        this.hora = hora;
    }
}
