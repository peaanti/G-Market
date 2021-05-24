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

public class Top50ProductAdapter extends RecyclerView.Adapter<Top50ProductAdapter.Top50ProductAdapterVH> {

    private List<Top50Product> top50productList;
    private RecyclerViewClickListener listener;

    public Top50ProductAdapter(List<Top50Product> top50productList, RecyclerViewClickListener listener) {
        this.top50productList = top50productList;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Top50ProductAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Top50ProductAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Top50ProductAdapterVH holder, int position) {
        Top50Product top50product = top50productList.get(position);

        String image_url = top50product.getImage();
        String product_name = top50product.getTitle();
        double product_price = top50product.getPrices().getRub();

        try {
            Log.e("picture", "before");
            Picasso.get().load(image_url)
                    .resize(460, 215)
                    .centerCrop()
                    .into(holder.product_image);
            Log.e("picture", "after");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("500", "try");
        holder.product_name.setText(product_name);
        Log.e("600", "product_name set text");
        holder.product_price.setText(product_price + "₽");
        Log.e("700", "set price");

    }


    @Override
    public int getItemCount() {
        return top50productList.size();
    }

    public class Top50ProductAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView product_name;
        TextView product_price;
        ImageView product_image;

        public Top50ProductAdapterVH(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAbsoluteAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
