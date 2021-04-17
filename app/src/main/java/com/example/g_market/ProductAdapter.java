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

import java.util.List;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapterVH holder, int position) {

        Product product = mproductList.get(position);

        String product_name = product.getTitle();
        double product_price = product.getPrices().getRub();
        String url = "https://steampay.com//goods//" + product.getUrl().substring(26) + ".jpg";


        try {
            holder.product_name.setText(product_name);
            holder.product_price.setText(product_price + "₽");
            Log.e("pic", url);
            Log.e("pc", "before pic");
            Picasso.get().load(url)
                    .resize(172, 81)
                    .centerCrop()
                    .into(holder.product_image);
            Log.e("pc", "after pic");
        } catch (Exception ignored) {}
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
