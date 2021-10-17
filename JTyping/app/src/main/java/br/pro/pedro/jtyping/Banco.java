package br.pro.pedro.jtyping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "JTypingBanco";
    private static final int VERSAO = 1;

    public Banco(Context context ){
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS cadastros ( " +
                    " id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, " +
                    " nome TEXT NOT NULL ," +
                    " idade TEXT NOT NULL," +
                    " email TEXT NOT NULL ," +
                    " sexo TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
