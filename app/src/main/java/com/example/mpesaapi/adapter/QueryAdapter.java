package com.example.mpesaapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpesaapi.R;

import java.util.ArrayList;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {
    ArrayList<Query> queryList;
    private Context context;

    public QueryAdapter(ArrayList<Query> queryList, Context context) {
        this.queryList = queryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stk_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Query query = queryList.get(position);

        holder.textViewDate.setText(query.getDate());
        holder.textViewPassword.setText(query.getPassword());
        holder.textViewCheckOutId.setText(query.getCheckOut());
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewDate;
        public TextView textViewPassword;
        public TextView textViewCheckOutId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate=itemView.findViewById(R.id.textViewDate);
            textViewPassword=itemView.findViewById(R.id.textViewPassword);
            textViewCheckOutId=itemView.findViewById(R.id.textViewCheckOut);
        }
    }
}
