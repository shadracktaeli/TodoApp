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

    /**
     * do not instantiate this here
     */

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private ToDoAdapter mAdapter;
    private FloatingActionButton fab;

    /** this must be a static final variable e.g
     * private static final int RC = 1;
     *
    */
    private static final int requestCode = 1;
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

        /**
         * always instantiate your adapter with an empty list e.g
         * mAdapter = new ToDoAdapter(this, new ArrayList(), swiper);
         */
        mAdapter = new ToDoAdapter(this, new ArrayList(), swiper);
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
        /**
         * always check if your list is null before you use it e.g
         * if (toDoList == null) {
         *  toDoList = new ArrayList();
         *  toDoList.add(0, todoItem);
         * } else {
         *  toDoList.add(0, todoItem);
         * }
         */
        if (toDoList == null) {
         *  toDoList = new ArrayList();
         *  toDoList.add(0, todoItem);
         * } else {
         *  toDoList.add(0, todoItem);
         * }

        mAdapter.notifyDataSetChanged();
    }
}
