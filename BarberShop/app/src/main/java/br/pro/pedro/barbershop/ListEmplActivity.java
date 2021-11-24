package br.pro.pedro.barbershop;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private ArrayAdapter<Funcionario> adapter;
    private List<Funcionario> listaDeFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        lvFuncionarios = findViewById(R.id.lvFuncionarios);

        carregarFuncionario();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListEmplActivity.this, FormEmplActivity.class);
                intent.putExtra("acao","inserir");
                startActivity(intent);
            }
        });

        lvFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idFunc = listaDeFuncionario.get(position).getId();
                   Intent intent = new Intent(ListEmplActivity.this,FormEmplActivity.class);
                intent.putExtra("acao","editar");
               intent.putExtra("idFunc",idFunc);
         startActivity(intent);
           }
        });

        lvFuncionarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
              public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {
               excluir(posicao);

              return true;
                 }
        });

    }

      private void excluir(int posicao){
        Funcionario Funcionario = listaDeFuncionario.get(posicao);
        android.app.AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setMessage("Confirme a exclusão do Funcionario " + Funcionario.getNomeFunc() + "?");
        alerta.setNeutralButton("Cancelar",null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FuncionarioDAO.excluir(ListEmplActivity.this,Funcionario.getId());
                carregarFuncionario();
            }
        });
        alerta.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarFuncionario();
    }

    private void carregarFuncionario(){

        listaDeFuncionario = FuncionarioDAO.getFuncionarios(this);

        if(listaDeFuncionario.size() == 0) {
            Funcionario fake = new Funcionario("Lista vazia...");

            listaDeFuncionario.add(fake);
            lvFuncionarios.setEnabled(false);
        }else{
            lvFuncionarios.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaDeFuncionario);
        lvFuncionarios.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actions) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}