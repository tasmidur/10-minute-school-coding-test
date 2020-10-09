package com.example.a10_minuteschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        TextView subTitle = (TextView) toolbar.findViewById(R.id.toolbar_sub_title);
        ImageView icon=(ImageView)toolbar.findViewById(R.id.icon);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        int icon_id= intent.getIntExtra("icon",0);
        icon.setImageResource(icon_id);

        if(intent.hasExtra("subTitle")){
           subTitle.setText(intent.getStringExtra("subTitle"));
        }else{
            subTitle.setVisibility(View.INVISIBLE);
        }

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}