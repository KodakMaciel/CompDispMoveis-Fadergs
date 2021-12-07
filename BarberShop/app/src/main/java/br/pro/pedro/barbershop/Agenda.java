package br.pro.pedro.barbershop;

public class Agenda {

    public int id;

    public String nome, hora, data, barbeiro;

    public Agenda() {

    }

    public Agenda(String nome, String data, String hora,String barbeiro) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.barbeiro = barbeiro;
    }

    @Override
    public String toString() {
        if(nome == "Agenda Livre!")
            return nome;
        else{
            return nome + " - " + data + " - " + hora + " - " + barbeiro;
        }
    }

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

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getBarbeiro() { return barbeiro;}

    public void setBarbeiro(String barbeiro) { this.barbeiro = barbeiro;}
}
