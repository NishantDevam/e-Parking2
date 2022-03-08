package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quesAttempted,question;
    private Button BtnOptionA,BtnOptionB,BtnOptionC,BtnOptionD;
    private ArrayList<quizModal> quizModalArrayList;
    Random random;//Random is used to access the data of arraylist.
    int currentScore=0,questionAttempted=1,currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quesAttempted=findViewById(R.id.quesAttempted);
        question=findViewById(R.id.questions);
        BtnOptionA=findViewById(R.id.BtnOptionA);
        BtnOptionB=findViewById(R.id.BtnOptionB);
        BtnOptionC=findViewById(R.id.BtnOptionC);
        BtnOptionD=findViewById(R.id.BtnOptionD);
        quizModalArrayList=new ArrayList<>();
        random=new Random();
        getQuizQuestion(quizModalArrayList);
        currentPos=random.nextInt(quizModalArrayList.size());//Random is basically used to get random questions by specifying its current position
        setDataToViews(currentPos);

        BtnOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(BtnOptionA.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());//Random is basically used to get random questions by specifying its current position
                setDataToViews(currentPos);
            }
        });
        BtnOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(BtnOptionB.getText().toString().trim().toLowerCase())) {
                   currentScore++;
               }
               questionAttempted++;
               currentPos=random.nextInt(quizModalArrayList.size());
               setDataToViews(currentPos);
            }
        });
        BtnOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(BtnOptionC.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        BtnOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(BtnOptionD.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

    }

    private void setDataToViews(int currentPos) {
        quesAttempted.setText("Questions Attempted :"+questionAttempted+"/10");
        if(questionAttempted==10){
            showBottomSheet();
        }
        else {
            question.setText(quizModalArrayList.get(currentPos).getQuestion());
            question.setText(quizModalArrayList.get(currentPos).getOptionA());
            question.setText(quizModalArrayList.get(currentPos).getOptionB());
            question.setText(quizModalArrayList.get(currentPos).getOptionC());
            question.setText(quizModalArrayList.get(currentPos).getOptionD());
        }
    }

    private void getQuizQuestion(ArrayList<quizModal> quizModalArrayList) {
        quizModalArrayList.add(new quizModal("Which of the following is not a Java features?","Dynamic","Architecture Neutral","Use of pointers","Object-oriented","Use of pointers"));
        quizModalArrayList.add(new quizModal("_____ is used to find and fix bugs in the Java programs","JVM","JRE","JDB","JDK","JDB"));
        quizModalArrayList.add(new quizModal(" What is the return type of the hashCode() method in the Object class?","int","object","long","void","int"));
        quizModalArrayList.add(new quizModal("What does the expression float a = 35 / 0 return?","0","Not a Number","Infinity","Run time exception","Infinity"));
        quizModalArrayList.add(new quizModal("Which of the following tool is used to generate API documentation in HTML format from doc comments in source code?","javap tool","javaw command","Javadoc tool","javah command","Javadoc tool"));
        quizModalArrayList.add(new quizModal(" Which package contains the Random class?","java.util package","java.lang package","java.awt package","java.io package","java.util package"));
        quizModalArrayList.add(new quizModal("Which object of HttpSession can be used to view and manipulate information about a session?"," session identifier","creation time","last accessed time"," All mentioned above"," All mentioned above"));
        quizModalArrayList.add(new quizModal(" Which is a mechanism where one object acquires all the properties and behaviors of the parent object?","Inheritance","Encapsulation","Polymorphism","None of the above","Inheritance"));
        quizModalArrayList.add(new quizModal("Which access specifiers can be used for a class so that it’s members can be accessed by a different class in the different package?","Private"," Public","Protected","None of the above","Public"));
        quizModalArrayList.add(new quizModal("Which is a superclass of all exception classes?","Throwable","Exception","RuntimeException","IOException","Throwable"));
    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
        View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV=bottomSheetView.findViewById(R.id.idTVScore);
        Button restartBtn=bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTV.setText("Your Score is \n"+currentScore+"/10");
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
                questionAttempted=1;
                currentScore=0;
                bottomSheetDialog.dismiss();
            }
        });

    }

}