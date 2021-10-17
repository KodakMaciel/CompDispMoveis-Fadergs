package br.pro.pedro.jtyping;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class EmptyActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etIdade;
    private EditText etEmail;
    private Spinner spCategorias;
    private String acao;
    private Cadastros cadastro;

    private Button btnSalvar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        etNome = findViewById(R.id.txtNome);
        etIdade = findViewById(R.id.txtIdade);
        etEmail = findViewById(R.id.txtEmail);
        spCategorias = findViewById(R.id.selectSexo);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar") ){
            carregarCadastro();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarCadastro(){
        int id = getIntent().getIntExtra("idCadastro", 0);
        cadastro = CadastroDAO.getCadastrosById(this, id);
        etNome.setText( cadastro.getNome() );
        String[] sexo = getResources().getStringArray(R.array.sexo);
        for (int i = 1; i < sexo.length; i++){
            if( cadastro.getSexo().equals( sexo[i] ) ) {
                spCategorias.setSelection(i);
                break;
            }
        }
    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String idade = etIdade.getText().toString().equals("") ? "0" : etIdade.getText().toString();
        int idade2 = Integer.parseInt(idade);
        String email = etEmail.getText().toString();

        if( nome.isEmpty() || spCategorias.getSelectedItemPosition() == 0 || email.isEmpty()){
            Toast.makeText(this, R.string.Campos, Toast.LENGTH_LONG).show();
        }else{
            if( acao.equals("inserir")) {
                cadastro = new Cadastros();
            }
            cadastro.setNome( nome );
            cadastro.setIdade( idade2 );
            cadastro.setEmail( email );
            cadastro.setSexo( spCategorias.getSelectedItem().toString() );
            if( acao.equals("inserir")) {
                CadastroDAO.inserir(this, cadastro);
                etNome.setText("");
                etIdade.setText("");
                etEmail.setText("");
                spCategorias.setSelection(0, true);
            }else{
                CadastroDAO.editar(this, cadastro);
                finish();
            }
        }

    }
}