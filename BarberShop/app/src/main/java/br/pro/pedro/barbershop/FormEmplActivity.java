package br.pro.pedro.barbershop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FormEmplActivity extends AppCompatActivity {

    private EditText txt_empl;
    private Button btn_salvar_funcionario;
    private String acao;
    private Funcionario funcionario;

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
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        } else {
            if (acao.equals("inserir")) {
                funcionario = new Funcionario();
            }
            funcionario.setNomeFunc(nome);
            if (acao.equals("inserir")) {
                FuncionarioDAO.inserir(this, funcionario);
                txt_empl.setText("");
            } else {
                FuncionarioDAO.editar(this, funcionario);
                finish();
            }
        }
    }
}