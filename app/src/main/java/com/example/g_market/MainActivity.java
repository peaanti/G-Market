package com.example.g_market;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    Top50ProductAdapter Top50adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        setOnNavigationViewListener();


        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        showTop50();
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
                Log.e("1", "onResponse");
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Product> products = response.body().getProducts();
                    adapter = new ProductAdapter(products);
                    recyclerView.setAdapter(adapter);
                    Log.e("1", "Success!!!");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Products> call, @NotNull Throwable t) {
                Log.e("failure", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    public void showTop50(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://steampay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Top50JsonPlaceHolderApi top50JsonPlaceHolderApi = retrofit.create(Top50JsonPlaceHolderApi.class);

        Call<Top50Products> call = top50JsonPlaceHolderApi.getTop50Products();

        call.enqueue(new Callback<Top50Products>() {
            @Override
            public void onResponse(@NotNull Call<Top50Products> call, @NotNull Response<Top50Products> response) {
                if(response.isSuccessful()){
                    System.out.println("TOP 50 done!!!!!");
                    assert response.body() != null;
                    List<Top50Product> Top50products = response.body().getTop50Products();
                    System.out.println(Top50products);
                    Top50adapter = new Top50ProductAdapter(Top50products);
                    recyclerView.setAdapter(Top50adapter);
                    Log.e("1", "Success!!!");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Top50Products> call, @NotNull Throwable t) {
                Log.e("failure", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    private void setOnNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.SHOW_ALL:
                showAllProducts();
                Log.e("1", "eeee");
            case R.id.SHOW_TOP50:
                showTop50();
                Log.e("2", "success");
        }
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}