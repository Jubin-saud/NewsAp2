package com.example.newsap2;

import com.example.newsap2.model.NewsHeadlines;

import java.util.List;

public interface onFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
