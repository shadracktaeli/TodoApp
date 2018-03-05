package com.example.geeko.todoapp;

import android.view.View;

/**
 * Created by Geeko on 2018/01/22.
 */

public interface CustomItemClickListener {
    void onItemClick(View v, int position, boolean isLongClick);
}
