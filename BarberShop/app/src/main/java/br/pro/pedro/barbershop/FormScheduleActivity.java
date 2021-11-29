package br.pro.pedro.barbershop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class FormScheduleActivity extends AppCompatActivity {

    private EditText txt_nameCust;
    private EditText txt_Data;
    private EditText txt_Horario;
    private Button btn_salvar_Agenda;
    private Agenda agenda;
    private String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sched);

        txt_nameCust = findViewById(R.id.txtnameCust);
        txt_Data = findViewById(R.id.txtData);
        txt_Horario = findViewById(R.id.txt_horario);
        btn_salvar_Agenda = findViewById(R.id.btn_salvar_Agenda);

        acao = getIntent().getStringExtra("acao");

        if (acao.equals("editar")) {
            carregarAgenda();
        }

        btn_salvar_Agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAgenda();
            }
        });
    }

    private void carregarAgenda() {
        int id = getIntent().getIntExtra("id", 0);
        agenda = AgendaDAO.getAgendaById(this, id);
        txt_nameCust.setText(agenda.getNome());
        txt_Horario.setText(agenda.getHora());
        txt_Data.setText(agenda.getData());
    }

    private void salvarAgenda() {
        String nameCust = txt_nameCust.getText().toString();
        String Data = txt_Data.getText().toString();
        String Horario = txt_Horario.getText().toString();

        if (nameCust.isEmpty() && Data.isEmpty() && Horario.isEmpty()) {
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        }
        else
        {
            if( acao.equals("inserir")) {
                 agenda = new Agenda();
            }

            agenda.setNome( nameCust );
            agenda.setData( Data );
            agenda.setHora( Horario );

            if( acao.equals("inserir")) {
                AgendaDAO.inserir(this, agenda);
                txt_nameCust.setText("");
                txt_Data.setText("");
                txt_Horario.setText("");

            }else{
                AgendaDAO.editar(this, agenda);
                finish();
            }
        }

    }
}