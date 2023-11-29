package hu.uni.miskolc.mobilprogramozas2023fosz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hu.uni.miskolc.mobilprogramozas2023fosz.databinding.ActivityDolgozoAdatokBinding;
import hu.uni.miskolc.mobilprogramozas2023fosz.service.DolgozoDTO;

public class DolgozoAdatok extends AppCompatActivity {

    ActivityDolgozoAdatokBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDolgozoAdatokBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        DolgozoDTO dolgozo = (DolgozoDTO) intent.getSerializableExtra("dolgozo");
        if (dolgozo != null){
            binding.idKiir.setText(String.valueOf(dolgozo.getId()));
            binding.keresztnevKiir.setText(dolgozo.getKeresztNev());
            binding.vezeteknevKiir.setText(dolgozo.getVezetekNev());
            binding.fizetesKiir.setText(Integer.toString(dolgozo.getFizetes()));
        }

    }
}