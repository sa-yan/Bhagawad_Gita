package com.example.bhagawadgita;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] arr, summary;
    Context context;
    public RecyclerViewAdapter(Context context, String arr[], String summary[]){
        this.context=context;
        this.arr=arr;
        this.summary=summary;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.chapter_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Log.d("LALA", holder.nameChap.getText().toString());
        holder.nameChap.setText(arr[position]);
        Log.d("SUMMARY", summary[position]);
        holder.nameChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChapterMeaningActivity.class);
                intent.putExtra("com.example.bhagawadgita.EXTRA_NAME_1",summary[position]);
                intent.putExtra("com.example.bhagawadgita.EXTRA_NAME_2", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameChap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameChap=itemView.findViewById(R.id.nameChapter);
        }
    }
}
