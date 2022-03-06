package com.example.newsap2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsap2.model.NewsApiResponse;
import com.example.newsap2.model.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements sekectLisenter, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdaptor adaptor;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;
    ProgressDialog dailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dailog.setTitle("fetching....");
                dailog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(newslistener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dailog = new ProgressDialog(this);
        dailog.setTitle("Loading...");
        dailog.show();

        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn7);
        b7.setOnClickListener(this);

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(newslistener,"general",null);
    }
    private final onFetchDataListener<NewsApiResponse> newslistener = new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No Articles Found!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                showlist(list);
                dailog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An error has occured!!!", Toast.LENGTH_SHORT).show();

        }
    };

    private void showlist(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recylermain);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adaptor = new CustomAdaptor(this,list,this);
        recyclerView.setAdapter(adaptor);
    }

    @Override
    public void onNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
        .putExtra("data", headlines)
        );
    }

    @Override
    public void onClick(View view) {
        Button button =(Button) view;
        String category = button.getText().toString();
        dailog.setTitle("Fetching news articles of " +category);
        dailog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(newslistener,category,null);
    }
}