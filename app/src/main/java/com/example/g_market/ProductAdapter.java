package com.example.g_market;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterVH> {

    private final List<Product> mproductList;


    public ProductAdapter(List<Product> productList) {
        this.mproductList = productList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    public void json_parse(String product_name, ImageView product_image) throws UnsupportedEncodingException {
        String url = "https://steampay.com/api/";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        JustJsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JustJsonPlaceHolderApi.class);
        Call<JustProducts> call = jsonPlaceHolderApi.getJustProducts(product_name);

        call.enqueue(new Callback<JustProducts>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<JustProducts> call, @NotNull Response<JustProducts> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<JustProduct> justproducts = response.body().getjustProducts();
                    JustProduct justProduct;
                    if (justproducts.size() > 0){
                       justProduct = justproducts.get(0);
                        String img_url = justProduct.getImage();
                        Log.e("1", "it works!");
                        try {
                            Log.e("picture", "before");
                            Picasso.get().load(img_url)
                                    .resize(172, 81)
                                    .centerCrop()
                                    .into(product_image);
                            Log.e("picture", "after");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Log.e("log", "not working");
                    }

                }
            }

            @Override
            public void onFailure(@NotNull Call<JustProducts> call, @NotNull Throwable t) {
                Log.e("failure", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapterVH holder, int position) {

        Product product = mproductList.get(position);

        String product_name = product.getTitle();
        double product_price = product.getPrices().getRub();

        Log.e("500", "try");
        holder.product_name.setText(product_name);
        Log.e("600", "product_name set text");
        holder.product_price.setText(product_price + "₽");
        Log.e("700", "set price");

        try {
            json_parse(product_name, holder.product_image);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return mproductList.size();
    }


    public static class ProductAdapterVH extends RecyclerView.ViewHolder {

        TextView product_name;
        TextView product_price;
        ImageView product_image;

        public ProductAdapterVH(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_image);
        }
    }
}
