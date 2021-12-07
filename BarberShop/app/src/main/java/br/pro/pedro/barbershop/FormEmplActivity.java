package br.pro.pedro.barbershop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FormEmplActivity extends AppCompatActivity {

    private EditText txt_empl;
    private Button btn_salvar_funcionario;
    private String acao;
    private Funcionario funcionario;

    public final int SAVE = 0;
    public final int EDIT = 1;
    public final int EMPTY = 2;
    public final int CLIENT_DELETE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_empl);

        txt_empl = findViewById(R.id.etNomeFunc);
        btn_salvar_funcionario = findViewById(R.id.btn_salvar_funcionario);

        acao = getIntent().getStringExtra("acao");

        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btn_salvar_funcionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarFormulario() {
        int id = getIntent().getIntExtra("idFunc", 0);
        funcionario = FuncionarioDAO.getFuncionarioById(this, id);
        txt_empl.setText(funcionario.getNomeFunc());
    }

    private void salvar() {
        String nome = txt_empl.getText().toString();
        if (nome.isEmpty()) {
            emptyToast(EMPTY, "Preencha todos os campos!");
        } else {
            if (acao.equals("inserir")) {
                funcionario = new Funcionario();
            }
            funcionario.setNomeFunc(nome);
            if (acao.equals("inserir")) {
                FuncionarioDAO.inserir(this, funcionario);
                txt_empl.setText("");
                showToast(SAVE, "Cadastrado com sucesso!");
            } else {
                FuncionarioDAO.editar(this, funcionario);
                showToast(EDIT, "Editado com sucesso!");
                finish();
            }
        }
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


}