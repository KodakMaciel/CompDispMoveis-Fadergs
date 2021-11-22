package br.pro.pedro.barbershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListScheduleActivity extends AppCompatActivity {

    private ListView lvFuncionarios;
    private List<Funcionario> listaDeFuncionario;
    //private DatabaseReference reference;
    //private ChildEventListener childEventListener;
    private ArrayAdapter<Funcionario> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListScheduleActivity.this, FormEmplActivity.class);
                startActivity(intent);
            }
        });

        lvFuncionarios = findViewById(R.id.lvFuncionarios);
        listaDeFuncionario = new ArrayList<>();
        adapter = new ArrayAdapter<Funcionario>(ListScheduleActivity.this, android.R.layout.simple_list_item_1, listaDeFuncionario);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (item.getItemId()) {

            case R.id.action_note:
                Intent intent = new Intent(ListScheduleActivity.this, ListEmplActivity.class);
                startActivity( intent );
                // Toast.makeText(this, "You have selected Schedule Class Menu", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private void excluir(final int posicao) {
        final Funcionario FuncSelecionado = listaDeFuncionario.get(posicao);

        AlertDialog.Builder alerta = new AlertDialog.Builder(ListScheduleActivity.this);
        alerta.setTitle("Excluir Funcionario");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão do Funcionario " + FuncSelecionado.nomeFunc + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //reference.child("professores").child(profSelecionado.id).removeValue();

                listaDeFuncionario.remove(posicao);
                adapter.notifyDataSetChanged();
            }
        });
        alerta.show();
    }
}