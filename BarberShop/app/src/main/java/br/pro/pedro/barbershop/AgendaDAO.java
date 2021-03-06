package br.pro.pedro.barbershop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    public static void inserir(Context context, Agenda Agenda) {
        ContentValues values = new ContentValues();
        values.put("nome", Agenda.getNome());
        values.put("hora", Agenda.getHora());
        values.put("data", Agenda.getData());
        values.put("barbeiro", Agenda.getBarbeiro());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("Agenda", null, values);
    }

    public static void editar(Context context, Agenda Agenda) {
        ContentValues values = new ContentValues();
        values.put("nome", Agenda.getNome());
        values.put("hora", Agenda.getHora());
        values.put("data", Agenda.getData());
        values.put("barbeiro", Agenda.getBarbeiro());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("Agenda", values, " id =" + Agenda.getId(), null);
    }

    public static void excluir(Context context, int idAgenda) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("Agenda", "id = " + idAgenda, null);
    }

    public static List<Agenda> getAgenda(Context context) {
        List<Agenda> agendaList = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Agenda order by nome", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Agenda Agenda = new Agenda();
                Agenda.setId(cursor.getInt(0));
                Agenda.setNome(cursor.getString(1));
                Agenda.setHora(cursor.getString(2));
                Agenda.setData(cursor.getString(3));
                Agenda.setBarbeiro(cursor.getString(4));

                agendaList.add(Agenda);

            } while (cursor.moveToNext());
        }
        return agendaList;
    }

    public ArrayList<String> buscaDadosSpinner(Context context) {
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        String sql_busca_dados_spinner = "Select * FROM Funcionario";
        ArrayList<String> dados_Spinner = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, null);

        while (cursor.moveToNext()) {
            dados_Spinner.add(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        }

        return dados_Spinner;
    }

    public static Agenda getAgendaById(Context context, int idAgenda) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Agenda where id = " + idAgenda, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Agenda Agenda = new Agenda();
            Agenda.setId(cursor.getInt(0));
            Agenda.setNome(cursor.getString(1));
            Agenda.setHora(cursor.getString(2));
            Agenda.setData(cursor.getString(3));
            Agenda.setBarbeiro(cursor.getString(4));

            return Agenda;

        } else {
            return null;
        }
    }
}
