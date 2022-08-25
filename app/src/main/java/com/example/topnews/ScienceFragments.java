package com.example.topnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceFragments extends Fragment {

    String api_key = "96d8094b9d584e4baa94603f7125144b";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "ng";
    private RecyclerView recyclerView_science;
    private  String category ="science";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sciencefragment, null);

        recyclerView_science = view.findViewById(R.id.recyclerview_science);
        modelClassArrayList = new ArrayList<>();
        recyclerView_science.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerView_science.setAdapter(adapter);

        findNews();

        return  view;


    }

    private  void  findNews(){
        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, api_key)
                .enqueue(new Callback<mainNews>() {
                    @Override
                    public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                        if (response.isSuccessful()){
                            modelClassArrayList.addAll(response.body().getArticles());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<mainNews> call, Throwable t) {

                    }
                });
    }
}
