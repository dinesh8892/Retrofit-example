package com.example.retrofitexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    public static final String LOG_TAG = "MainActivity";
    private ArrayList<Post> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholder jsonPlaceholder = retrofit.create(JsonPlaceholder.class);

        Call<Posted> call = jsonPlaceholder.getPosts();

        call.enqueue(new Callback<Posted>() {
            @Override
            public void onResponse(Call<Posted> call, Response<Posted> response) {

                if (!response.isSuccessful()) {
                    Log.d(LOG_TAG, "response failed Code" + response.code());
                    return;
                }

                Posted posted = response.body();
                data = new ArrayList<>(Arrays.asList(posted.getPost()));
                adapter = new RecyclerAdapter(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Posted> call, Throwable t) {
                Log.d(LOG_TAG, "onFailure " + t.getMessage());
            }
        });
    }
}
