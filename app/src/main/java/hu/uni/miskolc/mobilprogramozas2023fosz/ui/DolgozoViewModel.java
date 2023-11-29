package hu.uni.miskolc.mobilprogramozas2023fosz.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hu.uni.miskolc.mobilprogramozas2023fosz.service.DolgozoDTO;
import hu.uni.miskolc.mobilprogramozas2023fosz.service.DolgozoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DolgozoViewModel extends ViewModel {

    private MutableLiveData<List<DolgozoDTO>> dolgozok;

    public LiveData<List<DolgozoDTO>> getDolgozok(){
        if (dolgozok == null){
            dolgozok = new MutableLiveData<>();
            loadDolgozok();
        }
        return dolgozok;
    }

    public void addDolgozo(DolgozoDTO dolgozo){
        List<DolgozoDTO> dolgozokLista = dolgozok.getValue();
        dolgozokLista.add(dolgozo);
        dolgozok.postValue(dolgozokLista);
    }

    private void loadDolgozok() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/judit0310/dummyJsonServer/").
                addConverterFactory(GsonConverterFactory.create()).build();
        DolgozoService dolgozoService = retrofit.create(DolgozoService.class);

        Call<List<DolgozoDTO>> szemelyekListaza = dolgozoService.dolgozokListazasa();

        szemelyekListaza.enqueue(new Callback<List<DolgozoDTO>>() {
            @Override
            public void onResponse(Call<List<DolgozoDTO>> call, Response<List<DolgozoDTO>> response) {
                dolgozok.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DolgozoDTO>> call, Throwable t) {

            }
        });

    }

}
