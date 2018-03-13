package com.example.geeko.todoapp;

/**
 * Created by Geeko on 2018/01/13.
 */

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>{

    private List<ToDo> toDoList;
    private SwipeRefreshLayout swiper;
    private Context c;

    public ToDoAdapter(Context c, List<ToDo> toDoList, SwipeRefreshLayout swiper){
        this.c = c;
        this.toDoList = toDoList;
        this.swiper = swiper;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ToDoViewHolder holder, int position){
        holder.title.setText(toDoList.get(position).getTitle());
        holder.description.setText(toDoList.get(position).getDescription());
        holder.date.setText(toDoList.get(position).getDate());

        holder.setItemClickListener(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position, boolean isLongClick) {
                if(isLongClick)
                    Toast.makeText(c, holder.title.getText(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(c, holder.title.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    @Override
    public int getItemCount(){
        return toDoList.size();
    }


    private void refresh(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                swiper.setRefreshing(false);
            }
        }, 3000);
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView title, description, date;
        private CustomItemClickListener listener;

        public ToDoViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            date = view.findViewById(R.id.date);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void setItemClickListener(CustomItemClickListener listener){
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onItemClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}
