package hu.uni.miskolc.mobilprogramozas2023fosz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;

import hu.uni.miskolc.mobilprogramozas2023fosz.databinding.ActivityCimKiirBinding;
import hu.uni.miskolc.mobilprogramozas2023fosz.db.Cim;

public class CimKiir extends AppCompatActivity {

    private ActivityCimKiirBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCimKiirBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Cim cim = (Cim) intent.getSerializableExtra("cim");
        binding.textView2.setText(cim.getIranyitoszam());
        binding.textView4.setText(cim.getVaros());
        binding.textView5.setText(cim.getUtca());
        binding.textView8.setText(cim.getHazszam());
    }

    @Override
    protected void onStart() {
        super.onStart();

        ActivityResultLauncher<Intent> kamerakep =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK){
                if (result.getData().getExtras() != null) {
                    Bitmap img = (Bitmap) result.getData().getExtras().get("data");
                    binding.table.setBackground(new BitmapDrawable(getResources(), img));
                }
            }else{

            }
        });

    binding.kameragomb.setOnClickListener(view -> {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        kamerakep.launch(intent);
    });
    Intent serviceIntent = new Intent(CimKiir.this, Zenelejatszas.class);
    binding.play.setOnClickListener(view -> {
        startService(serviceIntent);
    });

    binding.stop.setOnClickListener(view -> {
        stopService(serviceIntent);
    });
    }



}