package hu.uni.miskolc.mobilprogramozas2023fosz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hu.uni.miskolc.mobilprogramozas2023fosz.service.DolgozoDTO;
import hu.uni.miskolc.mobilprogramozas2023fosz.service.DolgozoService;
import hu.uni.miskolc.mobilprogramozas2023fosz.ui.DolgozoAdapter;
import hu.uni.miskolc.mobilprogramozas2023fosz.ui.DolgozoKattintas;
import hu.uni.miskolc.mobilprogramozas2023fosz.ui.DolgozoViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicePelda extends AppCompatActivity {

    DolgozoService dolgozoService;
    private DolgozoViewModel mViewModel;
    private DolgozoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_pelda);
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/judit0310/dummyJsonServer/").
                addConverterFactory(GsonConverterFactory.create()).build();
        dolgozoService = retrofit.create(DolgozoService.class);

        Call<List<DolgozoDTO>> szemelyekListaza = dolgozoService.dolgozokListazasa();

        szemelyekListaza.enqueue(new Callback<List<DolgozoDTO>>() {
            @Override
            public void onResponse(Call<List<DolgozoDTO>> call, Response<List<DolgozoDTO>> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<DolgozoDTO>> call, Throwable t) {

            }
        });

        Call<DolgozoDTO> szemelyListazasa = dolgozoService.getDolgozoById(3);
        szemelyListazasa.enqueue(new Callback<DolgozoDTO>() {
            @Override
            public void onResponse(Call<DolgozoDTO> call, Response<DolgozoDTO> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<DolgozoDTO> call, Throwable t) {

            }
        });


        Call<List<DolgozoDTO>> szemelyekListazaFizetes = dolgozoService.dolgozokListazasaFizetesAlapjan(182500);
        szemelyekListazaFizetes.enqueue(new Callback<List<DolgozoDTO>>() {
            @Override
            public void onResponse(Call<List<DolgozoDTO>> call, Response<List<DolgozoDTO>> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<DolgozoDTO>> call, Throwable t) {

            }
        });
        DolgozoDTO dolgozo = new DolgozoDTO(1,"Anna","Kiss",123456);
        Call<DolgozoDTO> dolgozoHozzaadas = dolgozoService.createDolgozo(dolgozo);
        dolgozoHozzaadas.enqueue(new Callback<DolgozoDTO>() {
            @Override
            public void onResponse(Call<DolgozoDTO> call, Response<DolgozoDTO> response) {
                System.out.println("Dolgozó hozzáadva");
            }

            @Override
            public void onFailure(Call<DolgozoDTO> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });*/



        RecyclerView recyclerView = findViewById(R.id.dolgozokListaView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        mViewModel = new ViewModelProvider(this).get(DolgozoViewModel.class);
        adapter = new DolgozoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        mViewModel.getDolgozok().observe(this, dolgozok -> {
            adapter.setDolgozok(dolgozok);
            adapter.setListener(new DolgozoKattintas() {
                @Override
                public void onDolgozoClick(int position, View v) {
                    DolgozoDTO dolgozo = dolgozok.get(position);
                    System.out.println("A következőre kattintottam: "+dolgozo);
                    Intent intent = new Intent(ServicePelda.this,DolgozoAdatok.class);
                    intent.putExtra("dolgozo",dolgozo);
                    startActivity(intent);
                    }
            });
            recyclerView.setAdapter(adapter);
        });

        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(view -> {
            mViewModel.addDolgozo(new DolgozoDTO(1,"Peee","Ldaaa",123));
        });
    }
}