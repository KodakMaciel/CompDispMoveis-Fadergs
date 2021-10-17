package br.pro.pedro.jtyping;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Botão Entrar
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastros = new Intent(MainActivity.this, EmptyActivity.class);
                telaCadastros.putExtra("acao", "inserir");
                startActivity(telaCadastros);
            }
        });



        //Botão Cadastros
        Button btnCadastros1 = findViewById(R.id.btnCadastros1);

        btnCadastros1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListUsers.class);
                startActivity(intent);
            }
        });

    }
}