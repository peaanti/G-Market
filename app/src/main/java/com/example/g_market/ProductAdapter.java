package com.example.g_market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterVH> {

    private List<Product> mproductList;

    public ProductAdapter() {
    }

    public void setData(List<Product> productList) {
        this.mproductList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapterVH holder, int position) {

        Product product = mproductList.get(position);

        String product_name = product.getTitle();
        double product_price = product.getPrices().getRub();

        holder.product_name.setText(product_name);
        holder.product_price.setText((int) product_price);

    }

    @Override
    public int getItemCount() {
        return mproductList.size();
    }


    public static class ProductAdapterVH extends RecyclerView.ViewHolder {

        TextView product_name;
        TextView product_price;

        public ProductAdapterVH(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
        }
    }
}
