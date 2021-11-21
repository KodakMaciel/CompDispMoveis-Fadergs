package br.pro.pedro.barbershop;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ListEmplActivity extends AppCompatActivity {

    private ListView lvFuncionarios;
    private List<Funcionario> listaDeFuncionario;
    private ArrayAdapter<Funcionario> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListEmplActivity.this, FormEmplActivity.class);
                startActivity(intent);
            }
        });

        lvFuncionarios = findViewById(R.id.lvFuncionarios);
        listaDeFuncionario = new ArrayList<>();
        adapter = new ArrayAdapter<Funcionario>(ListEmplActivity.this, android.R.layout.simple_list_item_1, listaDeFuncionario);
        lvFuncionarios.setAdapter(adapter);

        lvFuncionarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir(position);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    // Continuar apartir daqui, ListaProfActivity
    // Continuar apartir daqui, ListaProfActivity
    // Continuar apartir daqui, ListaProfActivity
    // Continuar apartir daqui, ListaProfActivity
    // Continuar apartir daqui, ListaProfActivity

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        }
    }

}