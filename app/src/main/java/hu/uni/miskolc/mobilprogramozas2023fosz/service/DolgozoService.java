package hu.uni.miskolc.mobilprogramozas2023fosz.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DolgozoService {
    @GET("dolgozok")
    Call<List<DolgozoDTO>> dolgozokListazasa();

    @GET("dolgozok/{id}")
    Call<DolgozoDTO> getDolgozoById(@Path("id") int id);

    @GET("dolgozok")
    Call<List<DolgozoDTO>> dolgozokListazasaFizetesAlapjan(@Query("fizetes") int fizetes);

    @POST("dolgozok")
    Call<DolgozoDTO> createDolgozo(@Body DolgozoDTO dolgozo);
}
