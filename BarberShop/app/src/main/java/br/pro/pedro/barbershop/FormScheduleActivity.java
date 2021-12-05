package br.pro.pedro.barbershop;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;


public class FormScheduleActivity extends AppCompatActivity {

    private EditText txt_nameCust;
    private EditText txt_Data;
    private EditText txt_Horario;
    private Button btn_salvar_Agenda;
    private Agenda agenda;
    private String acao;

    public final int SAVE = 0;
    public final int EDIT = 1;
    public final int EMPTY = 2;
    public final int CLIENT_DELETE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sched);

        txt_nameCust = findViewById(R.id.txtnameCust);
        txt_Data = findViewById(R.id.txtData);
        txt_Horario = findViewById(R.id.txt_horario);
        btn_salvar_Agenda = findViewById(R.id.btn_salvar_Agenda);
        Spinner spinner = findViewById(R.id.spinner);

        AgendaDAO AgendaDAO = new AgendaDAO();

        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>(this, R.layout.spinner_custom,
        AgendaDAO.buscaDadosSpinner(this));

        adapter_spinner.setDropDownViewResource(R.layout.spinner_custom_list);
        spinner.setAdapter(adapter_spinner);

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

    public void showToast(int type, String message) {
        ViewGroup view = findViewById(R.id.container_toast);
        View v = getLayoutInflater().inflate(R.layout.custom_toast, view);

        switch (type) {
            case EDIT:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_edit));
                break;

            case SAVE:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_registered));
                break;
        }

        TextView txtMessage = v.findViewById(R.id.txt_edit_message);
        txtMessage.setText(message);

        Toast toast = new Toast(this);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }

    public void emptyToast(int type, String message) {
        ViewGroup view = findViewById(R.id.container_toast);
        View v = getLayoutInflater().inflate(R.layout.custom_toast_empty_sched, view);

        switch (type) {
            case EMPTY:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_empty));
                break;
        }

        switch (type) {
            case CLIENT_DELETE:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_empty));
                break;
        }

        TextView txtMessage = v.findViewById(R.id.txt_edit_message);
        txtMessage.setText(message);

        Toast toast = new Toast(this);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

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
            emptyToast(EMPTY, "Preencha todos os campos!");
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
                showToast(SAVE, "Cadastrado com sucesso!");

            }else{
                AgendaDAO.editar(this, agenda);
                showToast(EDIT, "Editado com sucesso!");
                finish();
            }
        }

    }
}