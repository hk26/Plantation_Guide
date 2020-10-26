package com.example.plantation_guide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Categories extends AppCompatActivity {

    Button button1;
    RecyclerView recview;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //---------------------back-button--------------
        ImageView login = (ImageView) findViewById(R.id.back_Btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Categories.this, home_page.class);
                startActivity(intent);
                onBackPressed();
            }

        });

        //---------------------hanging-Plant--------------------
        Button start_button=(Button)findViewById(R.id.hang_btn);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Categories.this,Hanging_Plants.class);
                startActivity(intent1);
            }
        });
        //---------------------indoor-Plant--------------------
        Button indor_btn=(Button)findViewById(R.id.indo_btn);

        indor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Categories.this,indoor.class);
                startActivity(intent2);
            }
        });

        //---------------------garden-Plant--------------------
        Button gardn_btn=(Button)findViewById(R.id.gard_btn);

        gardn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Categories.this, garden.class);
                startActivity(intent3);
            }
        });

        //---------------------aqua-Plant--------------------
        Button aqva_btn=(Button)findViewById(R.id.aqa_btn);

        aqva_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(Categories.this,aqua.class);
                startActivity(intent4);
            }
        });

        //---------------------meditional-Plant--------------------
        Button med_btn=(Button)findViewById(R.id.medi_btn);

        med_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(Categories.this, meditional_Plants.class);
                startActivity(intent5);
            }
        });



    }
}