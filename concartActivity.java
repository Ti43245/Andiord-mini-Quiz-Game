package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class concartActivity extends AppCompatActivity {
MediaPlayer player;
Button Return_btn;
TextView resu;
Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concart);
        player = MediaPlayer.create(concartActivity.this, R.raw.congratulation);
        player.start();
        resu=findViewById(R.id.tvResult);
        i=getIntent();
        resu.setText("Player total score :" + i.getIntExtra("result",  0));





        Return_btn=findViewById(R.id.Return_btn);
        Return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(concartActivity.this,MenuActivity.class);
                startActivity(intent);
                player = MediaPlayer.create(concartActivity.this, R.raw.game);
                player.start();









                }
        });




    }


}


