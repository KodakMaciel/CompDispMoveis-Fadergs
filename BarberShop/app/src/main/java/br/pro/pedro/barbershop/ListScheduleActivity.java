package br.pro.pedro.barbershop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListScheduleActivity extends AppCompatActivity {

    private ListView lvAgenda;
    private ArrayAdapter adapter;
    private List<Agenda> agendaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_schedule);

        lvAgenda = findViewById(R.id.lvAgenda);

        carregarClientes();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListScheduleActivity.this, FormScheduleActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idAgenda = agendaList.get(position).getId();
                Intent intenet = new Intent(ListScheduleActivity.this, FormScheduleActivity.class);
                intenet.putExtra("acao", "editar");
                intenet.putExtra("id", idAgenda);
                startActivity(intenet);
            }
        });

        lvAgenda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {
                excluir(posicao);

                return true;
            }
        });
    }

    private void excluir(int posicao) {
        Agenda Agenda = agendaList.get(posicao);
        android.app.AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirme a exclusão do cliente " + Agenda.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AgendaDAO.excluir(ListScheduleActivity.this, Agenda.getId());
                carregarClientes();

            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarClientes();
    }

    private void carregarClientes() {

        agendaList = AgendaDAO.getAgenda(this);

        if (agendaList.size() == 0) {
            Agenda fake = new Agenda("Agenda Livre!", "", "","");
            agendaList.add(fake);
            lvAgenda.setEnabled(false);
        } else {
            lvAgenda.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, agendaList);
        lvAgenda.setAdapter(adapter);
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