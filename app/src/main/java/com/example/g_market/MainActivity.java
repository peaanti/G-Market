package com.example.g_market;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        textViewResult = findViewById(R.id.text_view_result);

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
                if (!response.isSuccessful()){
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                List<Product> products = response.body().getProducts();

                for (Product product : products) {
                    String content = "";
                    content += "Title: " + product.getTitle() + "\n";
                    content += "Url: " + product.getUrl() + "\n";
                    content += "Availability: " + product.getIs_available() + "\n";
                    content +="Price: " + product.getPrices().getRub() + " ₽\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Products> call, @NotNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}