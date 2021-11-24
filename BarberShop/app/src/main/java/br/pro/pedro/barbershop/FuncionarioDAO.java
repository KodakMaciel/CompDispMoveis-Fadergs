package br.pro.pedro.barbershop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pro.pedro.barbershop.Banco;
import br.pro.pedro.barbershop.Funcionario;

public class FuncionarioDAO {

    public static void inserir(Context context, Funcionario Funcionario) {
        ContentValues values = new ContentValues();
        values.put("nome", Funcionario.getNomeFunc());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("Funcionario", null, values);
    }

    public static void editar(Context context, Funcionario Funcionario) {
        ContentValues values = new ContentValues();
        values.put("nome", Funcionario.getNomeFunc());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("Funcionario", values, " id = " + Funcionario.getId(), null);
    }


    public static void excluir(Context context, int idFunc) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("Funcionario", " id = " + idFunc, null);
    }

    public static List<Funcionario> getFuncionarios(Context context){
        List<Funcionario> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Funcionario order by nome", null);

        if(cursor.getCount() > 0) {

            cursor.moveToFirst();

            do{
                Funcionario Funcionario = new Funcionario();
                Funcionario.setId(cursor.getInt(0));
                Funcionario.setNomeFunc(cursor.getString(1));

                lista.add(Funcionario);

            }while(cursor.moveToNext());

        }

        return  lista;
    }

    public static Funcionario getFuncionarioById(Context context, int idFuncionario) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Funcionario where id = " + idFuncionario, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Funcionario Funcionario = new Funcionario();
            Funcionario.setId(cursor.getInt(0));
            Funcionario.setNomeFunc(cursor.getString(1));

            return Funcionario;
        } else {
            return null;
        }

    }
}
