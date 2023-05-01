package com.samsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_game);

        Button button_back = (Button) findViewById(R.id.button_back2);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Intent intent = new Intent(AboutGame.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {

                }
            }
        });


    }
}
