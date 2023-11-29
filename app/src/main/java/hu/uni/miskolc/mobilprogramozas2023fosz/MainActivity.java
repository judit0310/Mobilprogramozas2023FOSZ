package hu.uni.miskolc.mobilprogramozas2023fosz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import hu.uni.miskolc.mobilprogramozas2023fosz.db.Cim;

public class MainActivity extends AppCompatActivity {
    Button kuldesGomb;
    EditText iranyitoszamBevitel;
    EditText varosBevitel;
    EditText utcaBevitel;
    EditText hazszamBevitel;
    Button serviceGomb;
    Button dbGomb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iranyitoszamBevitel = findViewById(R.id.iranyitoszamBevitel);
        varosBevitel = findViewById(R.id.varosBevitel);
        utcaBevitel = findViewById(R.id.utcaBevitel);
        hazszamBevitel = findViewById(R.id.hazszamBevitel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        kuldesGomb = findViewById(R.id.button2);
        kuldesGomb.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CimKiir.class);
            Cim cim = new Cim();
            cim.setIranyitoszam(iranyitoszamBevitel.getText().toString());
            cim.setVaros(varosBevitel.getText().toString());
            cim.setUtca(utcaBevitel.getText().toString());
            cim.setHazszam(hazszamBevitel.getText().toString());
            intent.putExtra("cim", cim);
            startActivity(intent);
        });

        serviceGomb = findViewById(R.id.serviceButton);
        serviceGomb.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ServicePelda.class);
            startActivity(intent);
        });

        dbGomb = findViewById(R.id.dbGomb);
        dbGomb.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Adatbazis.class);
            startActivity(intent);
        });

    }
}