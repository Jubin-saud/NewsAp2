package com.example.newsap2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title,text_source;
    ImageView image_frm_src;
    CardView header_holder;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.news_title);
        text_source = itemView.findViewById(R.id.news_source);
        image_frm_src = itemView.findViewById(R.id.news_image);
        header_holder = itemView.findViewById(R.id.headermain);

    }
}
