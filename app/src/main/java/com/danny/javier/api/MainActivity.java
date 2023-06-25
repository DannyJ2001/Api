package com.danny.javier.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String BASE_URL = "https://jsonplaceholder.typicode.com";
    List<Usuario> allUsersList;
    RecyclerView rcvMain;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvMain = findViewById(R.id.rcvMain);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<List<Usuario>> call = apiService.getUsers();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    allUsersList = response.body();
                    rcvMain.setAdapter(new UserAdapter(MainActivity.this, allUsersList));
                } else {
                    Log.e("API", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("API", "Error en la llamada: " + t.getLocalizedMessage());
            }
        });
    }
}