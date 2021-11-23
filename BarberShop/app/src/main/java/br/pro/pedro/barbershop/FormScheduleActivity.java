package br.pro.pedro.barbershop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class FormScheduleActivity extends AppCompatActivity {

    private EditText txt_nameCust;
    private EditText txt_Data;
    private EditText txt_Horario;
    private Button   btn_salvar_Agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sched);

        txt_nameCust        = findViewById(R.id.txtnameCust);
        txt_Data            = findViewById(R.id.txtData);
        txt_Horario         = findViewById(R.id.txt_horario);
        btn_salvar_Agenda   = findViewById(R.id.btn_salvar_Agenda);

        btn_salvar_Agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAgenda();
            }
        });
    }
        private void salvarAgenda(){
            String nameCust = txt_nameCust.getText().toString();
            String Data     = txt_Data.getText().toString();
            String Horario  = txt_Horario.getText().toString();

            if(!nameCust.isEmpty()) {
                Agenda Agenda = new Agenda();
                Agenda.nome = nameCust;
                Agenda.data = Data;
                Agenda.hora = Horario;
            }
        }
}