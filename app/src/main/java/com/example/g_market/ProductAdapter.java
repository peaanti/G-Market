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

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterVH> {

    private final List<Product> mproductList;
    RequestQueue requestQueue;

    public ProductAdapter(List<Product> productList, MainActivity activity) {
        this.mproductList = productList;
        Cache cache = new DiskBasedCache(activity.getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    public void json_parse(String product_name, ImageView product_image) throws UnsupportedEncodingException {
        String url = "https://steampay.com/api/search?query=" + URLEncoder.encode(product_name, "UTF-8");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("products");
                        JSONObject product = jsonArray.getJSONObject(0);
                        String imageUrl = product.getString("image");
                        Log.e("picture", "before");
                        Picasso.get().load(imageUrl)
                                .resize(172, 81)
                                .centerCrop()
                                .into(product_image);
                        Log.e("picture", "after");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        Log.e("1", "aaaaa");
        requestQueue.add(request);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapterVH holder, int position) {

        Product product = mproductList.get(position);

        String product_name = product.getTitle();
        double product_price = product.getPrices().getRub();

        Log.e("5", "try");
        holder.product_name.setText(product_name);
        Log.e("6", "product_name set text");
        holder.product_price.setText(product_price + "₽");
        Log.e("7", "");

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
