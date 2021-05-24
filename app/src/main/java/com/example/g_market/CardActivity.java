package com.example.g_market;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CardActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        ImageView return_button = findViewById(R.id.return_button);
        TextView product_name = findViewById(R.id.product_name_card);
        RoundedImageView product_image = findViewById(R.id.product_image_card);
        TextView product_price = findViewById(R.id.product_price_card);
        TextView product_description = findViewById(R.id.product_description_card);
        TextView product_name_to_buy = findViewById(R.id.product_name_to_buy_card);
        Button btn = findViewById(R.id.product_buy_button);



        if (getIntent().hasExtra("product")) {
            Top50Product top50Product = getIntent().getParcelableExtra("product");
            String title = top50Product.getTitle();
            product_name.setText(title);
            String url = top50Product.getUrl();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(url);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            });

            String pic_url = top50Product.getImage();
            try {
                Log.e("picture", "before");
                Picasso.get().load(pic_url)
                        .resize(460, 215)
                        .centerCrop()
                        .into(product_image);
                Log.e("picture", "after");
            } catch (Exception e) {
                e.printStackTrace();
            }

            product_name_to_buy.setText(title);

            final String[] price = new String[1];
            final String[] description = new String[1];


            new Thread() {
                public void run() {
                    Document doc = null;
                    String url = top50Product.getUrl();
                    try {
                        doc = Jsoup.connect(url)
                                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                                .referrer("http://www.google.com")
                                .get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    int flag = 1;
                    
                    price[0] = doc.select("div.product__current-price").text();
                    System.out.println(price[0]);
                    try{
                        Elements element = doc.select("div.product__description-content");
                        description[0] = element.select("p").first().text();
                    } catch (Exception ignored){
                        flag = 0;
                    }
                    
                    System.out.println(description[0]);
                    int finalFlag = flag;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            product_price.setText(price[0].replace("руб.", "₽"));
                            if(finalFlag != 0){
                                product_description.setText(description[0]);
                            }
                        }
                    });

                }
            }.start();
        }

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

