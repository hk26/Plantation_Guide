package com.example.plantation_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Button start_button=(Button)findViewById(R.id.category_Btn);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_category = new Intent(home_page.this, Categories.class);
                startActivity(intent_category);
            }
        });

        Button start1_button=(Button)findViewById(R.id.map_Btn);

        start1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_map = new Intent(home_page.this, nurseries.class);
                startActivity(intent_map);
            }
        });



    }
}