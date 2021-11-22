package br.pro.pedro.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.btn_agenda = findViewById(R.id.btn_agenda);
        this.mViewHolder.btn_cad_func = findViewById(R.id.btn_cad_func);

    }

    private static class ViewHolder {
        Button btn_agenda;
        Button btn_cad_func;
    }


}