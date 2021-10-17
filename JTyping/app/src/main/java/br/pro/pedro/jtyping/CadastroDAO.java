package br.pro.pedro.jtyping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {

    public static void inserir( Context context, Cadastros cadastros  ){
        ContentValues values = new ContentValues();
        values.put( "nome" , cadastros.getNome() );
        values.put( "idade" , cadastros.getIdade() );
        values.put( "email" , cadastros.getEmail() );
        values.put( "sexo" , cadastros.getSexo() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("cadastros", null, values);
    }

    public static void editar( Context context, Cadastros cadastros  ){
        ContentValues values = new ContentValues();
        values.put( "nome" , cadastros.getNome() );
        values.put( "idade" , cadastros.getIdade() );
        values.put( "email" , cadastros.getEmail() );
        values.put( "sexo" , cadastros.getSexo() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("cadastros", values, "id = " + cadastros.getId(), null);
    }

    public static void excluir( Context context, int idCadastros  ){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("cadastros"," id = " + idCadastros, null);

    }

    public static List<Cadastros> getCadastros(Context context){
        List<Cadastros> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastros ORDER BY nome", null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Cadastros cad= new Cadastros();
                cad.setId( cursor.getInt( 0 ) );
                cad.setNome( cursor.getString( 1 ) );
                cad.setIdade( cursor.getInt( 2 ) );
                cad.setEmail( cursor.getString ( 3 ) );
                cad.setSexo( cursor.getString( 4 ) );
                lista.add( cad );

            }while ( cursor.moveToNext() );
        }

        return lista;
    }

    public static Cadastros getCadastrosById(Context context, int idCadastros){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastros WHERE id = " + idCadastros, null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Cadastros cad = new Cadastros();
            cad.setId( cursor.getInt( 0 ) );
            cad.setNome( cursor.getString( 1 ) );
            cad.setIdade( cursor.getInt( 2 ) );
            cad.setEmail( cursor.getString( 3 ) );
            cad.setSexo( cursor.getString( 4 ) );

            return cad;
        }else{
            return null;
        }
    }
}
