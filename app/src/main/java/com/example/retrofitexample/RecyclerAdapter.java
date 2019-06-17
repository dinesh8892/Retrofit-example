package com.example.retrofitexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Post> post;
    public static final String LOG_TAG = "myLogs";

    public RecyclerAdapter(ArrayList<Post> post){
        this.post = post;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView userId;
        private TextView title;
        private TextView text;


        public MyViewHolder(View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.id);
            userId = (TextView)itemView.findViewById(R.id.user_id);
            title = (TextView)itemView.findViewById(R.id.title);
            text = (TextView)itemView.findViewById(R.id.text);

        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);

        MyViewHolder mViewHolder = new MyViewHolder(v);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(post.get(i).getId());
        myViewHolder.userId.setText(post.get(i).getUserId());
        myViewHolder.title.setText(post.get(i).getTitle());
        myViewHolder.text.setText(post.get(i).getBody());

    }

    @Override
    public int getItemCount() {
        return post.size();
    }
}


