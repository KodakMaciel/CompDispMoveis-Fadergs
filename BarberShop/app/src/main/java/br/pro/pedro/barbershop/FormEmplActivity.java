package br.pro.pedro.barbershop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FormEmplActivity extends AppCompatActivity {

    private EditText txt_empl;
    private Button btn_salvar_funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_empl);

        txt_empl = findViewById(R.id.etNomeFunc);
        btn_salvar_funcionario = findViewById(R.id.btn_salvar_funcionario);

        btn_salvar_funcionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarFuncionario();
            }
        });
    }
        private void salvarFuncionario(){
            String nomeFunc = txt_empl.getText().toString();

            if(!nomeFunc.isEmpty()) {
                Funcionario funcionario = new Funcionario();
                funcionario.nomeFunc = nomeFunc;
            }
        }
}