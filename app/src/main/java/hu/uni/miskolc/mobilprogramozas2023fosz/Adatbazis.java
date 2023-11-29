package hu.uni.miskolc.mobilprogramozas2023fosz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import hu.uni.miskolc.mobilprogramozas2023fosz.db.Cim;
import hu.uni.miskolc.mobilprogramozas2023fosz.db.CimDAO;
import hu.uni.miskolc.mobilprogramozas2023fosz.db.CimDatabase;

public class Adatbazis extends AppCompatActivity {

    Button kuldesGomb;
    EditText iranyitoszamMezo;
    EditText varosMezo;
    EditText utcaMezo;
    EditText hazszamMezo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adatbazis);

        CimDatabase db = Room.databaseBuilder(getApplicationContext(),
                CimDatabase.class, "cimek").build();
        CimDAO dao = db.getCimDao();

        kuldesGomb = findViewById(R.id.dbAddGomb);
        iranyitoszamMezo = findViewById(R.id.iranyitoszamBevitel2);
        varosMezo = findViewById(R.id.varosBevitel2);
        utcaMezo = findViewById(R.id.utcaBevitel2);
        hazszamMezo = findViewById(R.id.hazszamBevitel2);
        kuldesGomb.setOnClickListener(view -> {
            Cim cim = new Cim();
            cim.setIranyitoszam(iranyitoszamMezo.getText().toString());
            cim.setVaros(varosMezo.getText().toString());
            cim.setUtca(utcaMezo.getText().toString());
            cim.setHazszam(hazszamMezo.getText().toString());
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    if (dao.findSame(cim).size() == 0) {
                        dao.insert(cim);
                        System.out.println(dao.getAllCim());
/*Belső tároló

 */
                        File file = new File(getFilesDir()+"/log.txt");
                        try{
                            if(!file.exists()){
                                file.createNewFile();
                            }

                            FileWriter writer = new FileWriter(file,true);
                            BufferedWriter bw = new BufferedWriter(writer);
                            Timestamp now = new Timestamp(System.currentTimeMillis());
                            bw.append(String.format("%s,%s%s",now.toString(),
                                    dao.getAllCim().size()," db cim van az adatbázisban"));
                            bw.newLine();
                            bw.flush();
                            bw.close();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        /*Külső tároló*/

                        File publicfile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                                "publiclog.txt");
                        try{
                            if(!publicfile.exists()){
                                publicfile.createNewFile();
                            }

                            FileWriter writer = new FileWriter(publicfile,true);
                            BufferedWriter bw = new BufferedWriter(writer);
                            Timestamp now = new Timestamp(System.currentTimeMillis());
                            bw.append(String.format("%s,%s%s\n",now.toString(),
                                    dao.getAllCim().size()," db cim van az adatbázisban"));
                            bw.flush();
                            bw.close();
                            FileReader reader = new FileReader(publicfile);
                            BufferedReader br = new BufferedReader(reader);
                            int db = 0;
                            while(br.readLine() != null){
                                db++;
                            }
                            br.close();
                            System.out.println("Eddigi sorok száma: "+db);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Adatbazis.this, "Új cím felvéve", Toast.LENGTH_SHORT).show();
                                iranyitoszamMezo.setText("");
                                varosMezo.setText("");
                                utcaMezo.setText("");
                                hazszamMezo.setText("");
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Adatbazis.this, "Ez a cím már fel van véve", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }

            });

        });


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(dao.getAllCim());
            }
        });

    }
}