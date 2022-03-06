package com.example.newsap2;

import android.content.Context;
import android.widget.Toast;

import com.example.newsap2.model.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(onFetchDataListener listener, String category, String query){

        Callnewsapi callnewsapi = retrofit.create(Callnewsapi.class);

        Call<NewsApiResponse> call = callnewsapi.callHeadlines("in",category,query,"8f3002c74fa94350a7c64875cf327d75");

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (response.isSuccessful()){
//                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("failure!!!");

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public RequestManager(Context context) {
        this.context = context;
    }
    public interface Callnewsapi{
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_Key
        );
    }
}
