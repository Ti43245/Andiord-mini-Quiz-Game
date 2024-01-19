package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button help_btn;
    MediaPlayer player;

    MediaPlayer startclick;
    MediaPlayer helpclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startButton=findViewById(R.id.btnStart);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);

                startclick= MediaPlayer.create(MainActivity.this, R.raw.start);
                startclick.start();

            }
        });
        help_btn=findViewById(R.id.help_btn);
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HelpActivity.class);
                startActivity(intent);

                helpclick= MediaPlayer.create(MainActivity.this, R.raw.help);
                helpclick.start();


            }

        });
    }
}