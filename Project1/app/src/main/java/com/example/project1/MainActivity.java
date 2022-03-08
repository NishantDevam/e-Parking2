package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button add;
    private Button mul;
    private Button div;
    private Button mod;
    private Button sub;
    private Button dot;
    private Button equal;
    private Button bracket1;
    private Button bracket2;
    private Button clear;
    private TextView info;
    private TextView result;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '×';
    private final char DIVISION = '÷';
    private final char MODULUS = '%';
    private final char EQU=0;
    private Double val1=Double.NaN;
    private Double val2;
    private char ACTION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();

        zero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "9");
            }
        });
        bracket1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + "(");
            }
        });
        bracket2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + ")");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                   Compute();
                   ACTION=ADDITION;
                   result.setText(String.valueOf(val1)+ "+");
                   info.setText(null);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Compute();
                ACTION=SUBTRACTION;
                result.setText(String.valueOf(val1)+ "-");
                info.setText(null);
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Compute();
                ACTION=MULTIPLICATION;
                result.setText(String.valueOf(val1)+ "×");
                info.setText(null);
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Compute();
                ACTION=DIVISION;
                result.setText(String.valueOf(val1)+ "÷");
                info.setText(null);
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Compute();
                ACTION=MODULUS;
                result.setText(String.valueOf(val1)+ "%");
                info.setText(null);
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                info.setText(info.getText().toString() + ".");
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(info.getText().length()>0){
                    CharSequence name=info.getText().toString();
                    info.setText(name.subSequence(0,name.length()-1));
                }
                else{
                     val1=Double.NaN;
                     val2=Double.NaN;
                    info.setText(null);
                    result.setText(null);
                }
            }
        });



        equal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Compute();
                ACTION=EQU;
                //5+4=9
                result.setText(result.getText().toString() + String.valueOf(val2)+ "=" + String.valueOf(val1));
                info.setText(null);
            }
        });
    }

    private void setupUIViews() {

        zero = (Button) findViewById(R.id.btn0);
        one = (Button) findViewById(R.id.btn1);
        two = (Button) findViewById(R.id.btn2);
        three = (Button) findViewById(R.id.btn3);
        four = (Button) findViewById(R.id.btn4);
        five = (Button) findViewById(R.id.btn5);
        six = (Button) findViewById(R.id.btn6);
        seven = (Button) findViewById(R.id.btn7);
        eight = (Button) findViewById(R.id.btn8);
        nine = (Button) findViewById(R.id.btn9);
        add = (Button) findViewById(R.id.btnadd);
        sub = (Button) findViewById(R.id.btnsub);
        div = (Button) findViewById(R.id.btndiv);
        mod = (Button) findViewById(R.id.btnmod);
        mul=(Button)findViewById(R.id.btnmul);
        equal=(Button)findViewById(R.id.btnequ);
        dot=(Button)findViewById(R.id.btndot);
        bracket1=(Button)findViewById(R.id.btnbracket1);
        bracket2=(Button)findViewById(R.id.btnbracket2);
        info = (TextView) findViewById(R.id.tvControl);
        result = (TextView) findViewById(R.id.tvResult);
        clear=(Button)findViewById(R.id.btnC);
    }

    private void Compute(){

        if (!Double.isNaN(val1)){
            val2=Double.parseDouble(info.getText().toString());

            switch (ACTION){

                    case ADDITION:
                    val1=val1+val2;
                    break;

                    case SUBTRACTION:
                    val1=val1-val2;
                    break;

                    case MULTIPLICATION:
                    val1=val1*val2;
                    break;

                    case DIVISION:
                    val1=val1/val2;
                    break;

                    case MODULUS:
                    val1=val1%val2;
                    break;

                    case EQU:
                    break;


            }
        }
        else{
            val1=Double.parseDouble(info.getText().toString());
        }



    }
}