package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level1Activity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNo, tvScore, tTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;

    MediaPlayer player;

    int totalQuestions;
    int qcounter = 0;


    private QuestionModel currentQuestion;

    ColorStateList dfRBColor;
    boolean answered;

    int score;

    CountDownTimer countDownTimer;


    private List<QuestionModel> questionlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);






        questionlist = new ArrayList<>();
        tvQuestion =  findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);
        tTimer=findViewById(R.id.idTimer);

        dfRBColor = rb1.getTextColors();

        questionlist = new ArrayList<>();


        addQuestions();
        Collections.shuffle(questionlist);

        totalQuestions = questionlist.size();
        showNextQuestion();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answered == false) {
                    if (rb1.isChecked() ||rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                        countDownTimer.cancel();
                    } else {
                        Toast.makeText(Level1Activity.this,
                                "Please select an option", Toast.LENGTH_LONG).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });


    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRBColor);
        rb2.setTextColor(dfRBColor);
        rb3.setTextColor(dfRBColor);

        if (qcounter < totalQuestions) {
            Timer();
            currentQuestion = questionlist.get(qcounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            Toast.makeText(Level1Activity.this,"Choose one ",Toast.LENGTH_LONG).show();

            qcounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question" + qcounter + "/" + totalQuestions);
            answered = false;

        } else {
            finish();
            Intent intent =new Intent(Level1Activity.this,concartActivity.class);
            intent.putExtra("result",score);
            startActivity(intent);
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected)+1;
        if (answerNo == currentQuestion.getCorrectAnsNo()) {
            score++;
            tvScore.setText("Score:" + score);


            Toast.makeText(Level1Activity.this,"correct",Toast.LENGTH_LONG).show();
            player = MediaPlayer.create(Level1Activity.this, R.raw.correct);
            player.start();

        }
        else{

            Toast.makeText(Level1Activity.this,"wrong",Toast.LENGTH_LONG).show();
            player =MediaPlayer.create(Level1Activity.this, R.raw.wrong);
            player.start();

        }



        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnsNo()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                break;

        }

        if (qcounter < totalQuestions) {
            btnNext.setText("Next");

        } else {
            btnNext.setText("finish");
        }
    }

    private void Timer() {
        countDownTimer = new CountDownTimer(21000, 1000) {
            @Override
            public void onTick(long l) {
                tTimer.setText("00:" + l / 1000);
            }



            @Override
            public void onFinish() {

                showNextQuestion();
               // player = MediaPlayer.create(Level1Activity.this, R.raw.alarm);
                //player.start();


            }

        }.start();

    }


    private void addQuestions() {
        questionlist.add(new QuestionModel("4+5=?", "3", "5", "9", 3));
        questionlist.add(new QuestionModel("6/3?", "2", "7", "9", 1));
        questionlist.add(new QuestionModel("5*2?", "8", "10", "7", 2));
        questionlist.add(new QuestionModel("9/3?", "6", "3", "4", 2));
        questionlist.add(new QuestionModel("9-8?", "3", "1", "2", 2));
        questionlist.add(new QuestionModel("4/2?", "3", "6", "2", 3));
        questionlist.add(new QuestionModel("3+3?", "7", "6", "8", 2));
        questionlist.add(new QuestionModel("10/2?", "6", "5", "4", 2));
        questionlist.add(new QuestionModel("5+3?", "8", "3", "10", 1));
        questionlist.add(new QuestionModel("3*3?", "8", "4", "9", 3));




    }
}


