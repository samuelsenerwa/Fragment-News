package com.example.topnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.model.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragments extends Fragment {

    String api_key = "96d8094b9d584e4baa94603f7125144b";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "ng";
    private RecyclerView recyclerView_home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, null);

        recyclerView_home = view.findViewById(R.id.recyclerview_home);
        modelClassArrayList = new ArrayList<>();
        recyclerView_home.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerView_home.setAdapter(adapter);

        findNews();

        return  view;


    }

    private void findNews() {
        ApiUtilities.getApiInterface().getNews(country, 100, api_key).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Toast.makeText(getContext(), "Error!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
