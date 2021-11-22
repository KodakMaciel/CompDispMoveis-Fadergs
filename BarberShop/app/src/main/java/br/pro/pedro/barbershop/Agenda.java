package br.pro.pedro.barbershop;

import androidx.annotation.NonNull;

public class Agenda {

    public String nome, hora, data, id, Funcionario;

    @NonNull
    @Override
    public String toString() { return Funcionario + " - " + nome + " - " + data + " - " + hora;}
}
