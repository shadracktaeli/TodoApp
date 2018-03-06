package com.example.geeko.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Geeko on 2018/01/13.
 */

public class TodoScreen extends AppCompatActivity{
    private Button btnCreate;
    private EditText txtTitle, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_screen);
        txtTitle = findViewById(R.id.title);
        txtDescription = findViewById(R.id.description);

        btnCreate = findViewById(R.id.create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * try to separate code into functions e.g
                 *
                 * if(validates()) {
                 *  Intent intent = new Intent();
                 *  intent.putExtra("title", txtTitle.getText().toString());
                 *  intent.putExtra("description", txtDescription.getText().toString());
                 *  setResult(RESULT_OK, intent);
                 *  finish();
                 * }
                 */
                final String title = txtTitle.getText().toString().trim();
                final String descrip = txtDescription.getText().toString().trim();
                if(TextUtils.isEmpty(title)) {
                    txtTitle.setError("This field cannot be blank!");
                } else if(TextUtils.isEmpty(descrip)){
                    txtDescription.setError("This field cannot be blank!");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("title", txtTitle.getText().toString());
                    intent.putExtra("description", txtDescription.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private boolean validates() {
        boolean validates = true;

        final String title = txtTitle.getText().toString().trim();
        final String descrip = txtDescription.getText().toString().trim();

        if(TextUtils.isEmpty(title)) {
            validates = false;
            txtTitle.setError("This field cannot be blank!");
        }

        if(TextUtils.isEmpty(descrip)){
            validates = false;
            txtDescription.setError("This field cannot be blank!");
        }

        return validates;
    }
}
