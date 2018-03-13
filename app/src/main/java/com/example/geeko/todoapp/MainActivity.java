package com.example.geeko.todoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<ToDo> toDoList; // Instantiation removed

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private ToDoAdapter mAdapter;
    private FloatingActionButton fab;

    
    private static final int requestCode = 1; //Integer variable revised to static final
    private String title, descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        swiper = findViewById(R.id.swiper);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodoScreen.class);
                startActivityForResult(intent,requestCode);
            }
        });

        toDoList = new ArrayList(); //Instatiating list before parsing it to the adapter


        mAdapter = new ToDoAdapter(this, toDoList, swiper);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1 && resultCode==RESULT_OK && data!=null);
        title = data.getStringExtra("title");
        descrip = data.getStringExtra("description");
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date mydate = new Date();
        String todayDate = timeStampFormat.format(mydate);

        initToDoData(title, descrip, todayDate);
    }

    private void initToDoData(String title, String description, String date) {
        ToDo todoItem = new ToDo(title, description, date);

        if (toDoList == null) {             //Revision made to check whether list is null from the onset
            toDoList = new ArrayList<>();
            toDoList.add(0, todoItem);
         } else {
            toDoList.add(0, todoItem);
        }

        mAdapter.notifyDataSetChanged();
    }
}
