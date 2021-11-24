package br.pro.pedro.barbershop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "appbarber";
    private static final int VERSAO = 1;

    public Banco(Context Context){
        super(Context, NOME_BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS animal( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nome TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
