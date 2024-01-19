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

public class Level2Activity extends AppCompatActivity {

    //Declaration of UI element

    private TextView tvQuestion, tvQuestionNo, tvScore, textViewTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;

    //Declaration of variable and assigning values
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
        setContentView(R.layout.activity_level2);
        /*
        Use this functions to locate elements by their id and assign then to the appropriate variable
         */


        questionlist = new ArrayList<>();
        tvQuestion = (TextView) findViewById(R.id.textQuestion);
        tvScore = (TextView) findViewById(R.id.textScore);
        tvQuestionNo = (TextView) findViewById(R.id.textQuestionNo);
        textViewTimer=(TextView)findViewById(R.id.idTimer);


        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);

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
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                        countDownTimer.cancel();
                    } else {
                        Toast.makeText(Level2Activity.this,
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

            Toast.makeText(Level2Activity.this,"Choose one ",Toast.LENGTH_LONG).show();

            qcounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question" + qcounter + "/" + totalQuestions);
            answered = false;




        } else {
            finish();
            Intent intent =new Intent(Level2Activity.this,concartActivity.class);
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

            Toast.makeText(Level2Activity.this,"correct",Toast.LENGTH_LONG).show();
            player = MediaPlayer.create(Level2Activity.this, R.raw.correct);
            player.start();

        }
        else{
            Toast.makeText(Level2Activity.this,"wrong",Toast.LENGTH_LONG).show();
            player =MediaPlayer.create(Level2Activity.this, R.raw.wrong);
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
        countDownTimer = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long l) {
                textViewTimer.setText("00:" + l / 1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();

            }

        }.start();

    }


    private void addQuestions() {
        questionlist.add(new QuestionModel("4+2?", "3", "5", "6", 3));
        questionlist.add(new QuestionModel("3*2?", "6", "7", "9", 1));
        questionlist.add(new QuestionModel("7+3?", "8", "10", "4", 2));
        questionlist.add(new QuestionModel("8+2?", "10", "3", "4", 1));
        questionlist.add(new QuestionModel("4+4?", "3", "8", "2", 2));
        questionlist.add(new QuestionModel("6/2?", "4", "7", "3", 3));
        questionlist.add(new QuestionModel("3+2?", "7", "5", "8", 2));
        questionlist.add(new QuestionModel("12/3?", "6", "4", "2", 2));
        questionlist.add(new QuestionModel("3+1?", "4", "3", "10", 1));
        questionlist.add(new QuestionModel("7+1?", "6", "5", "8", 3));




    }
}


