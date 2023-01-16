package com.example.assignment02_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button OptA, OptB, OptC, OptD,next;
    TextView QNo, Question, Score,CorrectAns, Remarks;
    String [] statement ={"How many daily prayers are there in Islam?", "What is the holy book called in Islam?",
            "How many names does Allah have?","Who is the Last Prophet Of Allah?", "How Many Paras of Holy Quran?"};
    String [] answers = {"5", "The Holy Quran", "99", "Hazrat Muhammad(P.B.U.H)", "30"};
    String [][] options = {{"4", "5", "7","3"}, {"The Holy Quran","Torah","Zabur", "Injil"}, {"99", "100", "101","90" }, {"Hazrat Adam", "Hazrat Muhammad(P.B.U.H)","Hazrat Dawood", "Hazrat Ibraheem"}, {"30","40","10","20"}};
    ArrayList<Integer> QList = new ArrayList<Integer>(5);
    ArrayList<Integer> OList = new ArrayList<Integer>(4);
    String [][] data;
    DbHelper db;
    int RanQues = 0, index = 0,score=0;
    String answer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OptA = findViewById(R.id.OptA);
        OptA.setOnClickListener(this);
        OptB = findViewById(R.id.OptB);
        OptB.setOnClickListener(this);
        OptC = findViewById(R.id.OptC);
        OptC.setOnClickListener(this);
        OptD = findViewById(R.id.OptD);
        OptD.setOnClickListener(this);
        next = findViewById(R.id.nextBtn);
        QNo = findViewById(R.id.QNo);
        Question = findViewById(R.id.Question);
        Score = findViewById(R.id.Score);
        CorrectAns = findViewById(R.id.CorrectAns);
        Remarks = findViewById(R.id.Remarks);

        QList.add(0);
        QList.add(1);
        QList.add(2);
        QList.add(3);
        QList.add(4);

        OList.add(0);
        OList.add(1);
        OList.add(2);
        OList.add(3);

        Collections.shuffle(QList);
        index = 0;
        QNo.setText("Question No:\t\t\t"+(index+1)+"/5");
        score = db.select();
        Score.setText("Score:\t\t\t"+ db.select());
        Question.setText(getQuestion(index));
        OptA.setText(getOpt(RanQues,OList.get(0)));
        OptB.setText(getOpt(RanQues,OList.get(1)));
        OptC.setText(getOpt(RanQues,OList.get(2)));
        OptD.setText(getOpt(RanQues,OList.get(3)));
        db = new DbHelper(this);
    }
    public String getQuestion(int a){
        RanQues = QList.get(a);
        return "Q: "+ statement[RanQues];
    }
    public String getOpt(int a,int b){
        return options[a][b];
    }
    public String getCorrectAnswer() {
        return "Right Answer: "+answers[RanQues];
    }
    public void NextButton(View view) {
        index++;
        if(index == 4)
        {
            next.setText("Show Results");
            next.setEnabled(true);
            score = db.select();
            int incorrect = 5-score;
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("keycorrect", score);
            intent.putExtra("keyIncorrect",incorrect+"");
            intent.putExtra("data",db.selectAllStudents().toArray());
            startActivity(intent);
        }
        if (index < 5)
        {
            QNo.setText("Question No:\t\t\t"+(index+1)+"/5");
            score = db.select();
            Score.setText("Score:\t\t\t"+ db.select());
            Question.setText(getQuestion(index));
            CorrectAns.setText(" ");
            Remarks.setText("");
            Collections.shuffle(OList);
            OptA.setText(getOpt(RanQues,OList.get(0)));
            OptB.setText(getOpt(RanQues,OList.get(1)));
            OptC.setText(getOpt(RanQues,OList.get(2)));
            OptD.setText(getOpt(RanQues,OList.get(3)));
            OptA.setEnabled(true);
            OptB.setEnabled(true);
            OptC.setEnabled(true);
            OptD.setEnabled(true);
        }
        else
            next.setEnabled(false);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.OptA:
                if (OptA.getText()  == answers[RanQues]){
                    Remarks.setText("Awesome *_*");
                    Remarks.setBackgroundColor(R.color.teal_700);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptA.getText().toString(),"T");

                } else {
                    Remarks.setText("OOH :(");
                    Remarks.setBackgroundColor(R.color.red);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptA.getText().toString(),"F");
                    db.insertResult(result);
                }
                break;
            case R.id.OptB:
                if (OptB.getText() == answers[RanQues]) {
                    Remarks.setText("Awesome *_*");
                    Remarks.setBackgroundColor(R.color.teal_700);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptB.getText().toString(),"T");
                    db.insertResult(result);
                } else {
                    Remarks.setText("OOH :(");
                    Remarks.setBackgroundColor(R.color.red);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptB.getText().toString(),"F");
                    db.insertResult(result);
                }
                break;
            case R.id.OptC:
                if (OptC.getText() == answers[RanQues]) {
                    Remarks.setText("Awesome *_*");
                    Remarks.setBackgroundColor(R.color.teal_700);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptC.getText().toString(),"T");
                    db.insertResult(result);
                } else {
                    Remarks.setText("OOH :(");
                    Remarks.setBackgroundColor(R.color.red);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptC.getText().toString(),"F");
                    db.insertResult(result);
                }
                break;
            case R.id.OptD:
                if (OptD.getText() == answers[RanQues]) {
                    Remarks.setText("Awesome *_*");
                    Remarks.setBackgroundColor(R.color.teal_700);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptD.getText().toString(),"T");
                    db.insertResult(result);
                } else {
                    Remarks.setText("OOH :(");
                    Remarks.setBackgroundColor(R.color.red);
                    Result result = new Result(Question.getText().toString(),answers[RanQues],OptD.getText().toString(),"F");
                    db.insertResult(result);
                }
                break;
        }
        score = db.select();
        Score.setText("Score:\t\t\t"+ score);
        CorrectAns.setText(getCorrectAnswer());
        OptA.setEnabled(false);
        OptB.setEnabled(false);
        OptC.setEnabled(false);
        OptD.setEnabled(false);
    }
}