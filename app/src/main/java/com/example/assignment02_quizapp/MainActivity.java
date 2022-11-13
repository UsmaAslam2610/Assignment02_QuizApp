package com.example.assignment02_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button OptA, OptB, OptC, OptD,next;
    TextView QNo, Question, Score,CorrectAns, Remarks;
    String [] statement ={"How many daily prayers are there in Islam?", "What is the holy book called in Islam?",
            "How many names does Allah have?","Who is the Last Prophet Of Allah?", "How Many Paras of Holy Quran?"};
    String [] answers = {"5", "Quran", "99", "Hazrat Muhammad(P.B.U.H)", "30"};
    String [][] options = {{"4", "5", "7","3"}, {"The Holy Quran","Torah","Zabur", "Injil"}, {"99", "100", "101","90" }, {"Hazrat Adam", "Hazrat Muhammad(P.B.U.H)","Hazrat Dawood", "Hazrat Ibraheem"}, {"30","40","10","20"}};
    ArrayList<Integer> QList = new ArrayList<Integer>(5);
    ArrayList<Integer> OList = new ArrayList<Integer>(4);
    int RanQues = 0, index = 0, score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OptA = findViewById(R.id.OptA);
        OptB = findViewById(R.id.OptB);
        OptC = findViewById(R.id.OptC);
        OptD = findViewById(R.id.OptD);
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
        Score.setText("Score:\t\t\t"+ score);
        Question.setText(getQuestion(index));
        OptA.setText(getOpt(RanQues,OList.get(0)));
        OptB.setText(getOpt(RanQues,OList.get(1)));
        OptC.setText(getOpt(RanQues,OList.get(2)));
        OptD.setText(getOpt(RanQues,OList.get(3)));
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
        if (index < 5)
        {
            QNo.setText("Question No:\t\t\t"+(index+1)+"/5");
            Score.setText("Score:\t\t\t"+ score);
            Question.setText(getQuestion(index));
            CorrectAns.setText(" ");
            Remarks.setText("");
            Collections.shuffle(OList);
            OptA.setText(getOpt(RanQues,OList.get(0)));
            OptB.setText(getOpt(RanQues,OList.get(1)));
            OptC.setText(getOpt(RanQues,OList.get(2)));
            OptD.setText(getOpt(RanQues,OList.get(3)));
        }
    }
}