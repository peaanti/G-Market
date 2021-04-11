package com.example.g_market;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        showAllProducts();

    }

    public void showAllProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.steampay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Products> call = jsonPlaceHolderApi.getProducts();

        call.enqueue(new Callback<Products>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Products> call, @NotNull Response<Products> response) {
                if (!response.isSuccessful()) {
                    List<Product> products = response.body().getProducts();
                    adapter.setData(products);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Products> call, @NotNull Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }
}