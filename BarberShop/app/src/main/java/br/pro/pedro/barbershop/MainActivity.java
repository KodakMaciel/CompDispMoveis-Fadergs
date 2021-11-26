package br.pro.pedro.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button Schedule;
    private Button employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Schedule = findViewById(R.id.btn_agenda);
        employee = findViewById(R.id.btn_cad_func);

        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListScheduleActivity.class);
                //intent.putExtra("acao","inserir");
                startActivity(intent);
            }
        });

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListEmplActivity.class);
                //intent.putExtra("acao","inserir");
                startActivity(intent);
            }
        });

    }

}

