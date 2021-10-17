package br.pro.pedro.jtyping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ListUsers extends AppCompatActivity {

    private ListView lvCadastros;
    private ArrayAdapter adapter;
    private List<Cadastros> listaDeCadastros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        lvCadastros = findViewById(R.id.lvCadastros);

        carregarCadastros();

        //Botão de Voltar - Tela Inicial
        Button btnVoltarCad = findViewById(R.id.btnVoltarIni);

        btnVoltarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListUsers.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lvCadastros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                excluir(position);
                carregarCadastros();
                return true;
            }
        });
    }

    private void excluir(int posicao){
        Cadastros cad = listaDeCadastros.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(R.string.Excluir);
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage(cad.getNome());
        alerta.setNeutralButton(R.string.Cancelar, null);


        alerta.setPositiveButton(R.string.Sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CadastroDAO.excluir(ListUsers.this, cad.getId());
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarCadastros();
    }

    private void carregarCadastros(){
        listaDeCadastros = CadastroDAO.getCadastros(this);
        if( listaDeCadastros.size() == 0) {
            Cadastros fake = new Cadastros("0" , 0, "0","0" + "\n\n \t\t\t LISTA VAZIA!");
            listaDeCadastros.add(fake);
            lvCadastros.setEnabled(false);
        }else{
            lvCadastros.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeCadastros);
        lvCadastros.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}