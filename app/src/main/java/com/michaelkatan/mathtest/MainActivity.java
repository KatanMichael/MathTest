package com.michaelkatan.mathtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity
{

    int num1;
    int num2;
    int answer;
    char sign;
    int totalQuastions = 0;
    int rightAnswers = 0;

    TextView quastionTV;
    TextView answerTV;
    TextView scoreTv;

    Button sumbit_btn;
    Button btn_A;
    Button btn_B;
    Button btn_C;
    Button btn_D;

    ArrayList<Button> answerBtns;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        quastionTV = findViewById(R.id.quastion_TV);
        answerTV = findViewById(R.id.asnwer);
        scoreTv = findViewById(R.id.score_TV);

        sumbit_btn = findViewById(R.id.submit_btn);
        btn_A = findViewById(R.id.btn_A);
        btn_B = findViewById(R.id.btn_B);
        btn_C = findViewById(R.id.btn_C);
        btn_D = findViewById(R.id.btn_D);

        answerBtns = new ArrayList<>();
        answerBtns.add(btn_A);
        answerBtns.add(btn_B);
        answerBtns.add(btn_C);
        answerBtns.add(btn_D);

        btn_A.setOnClickListener(new myClickListener());
        btn_B.setOnClickListener(new myClickListener());
        btn_C.setOnClickListener(new myClickListener());
        btn_D.setOnClickListener(new myClickListener());

        scoreTv.setText("Score: " + rightAnswers + " / "+ totalQuastions);
        getRandomQuastion();
        getRandomAnswers();
        sumbit_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(answerTV.getText().toString().equals("?"))
                {
                    Toast.makeText(MainActivity.this, "Select Answer First..", Toast.LENGTH_SHORT).show();
                }else {
                    totalQuastions++;
                    int temp;
                    temp = Integer.parseInt(answerTV.getText().toString());
                    if (temp == answer) {
                        Toast.makeText(MainActivity.this, "Your Right!", Toast.LENGTH_SHORT).show();
                        rightAnswers++;
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Answer...", Toast.LENGTH_SHORT).show();
                    }

                    getRandomQuastion();
                    getRandomAnswers();
                    answerTV.setText("?");
                    scoreTv.setText("Score: " + rightAnswers + " / " + totalQuastions);
                }
            }
        });
    }


    public class myClickListener  implements View.OnClickListener
    {
        Button b;
        @Override
        public void onClick(View v)
        {

            b = (Button) v;
            //Toast.makeText(MainActivity.this, ""+b.getText().toString(), Toast.LENGTH_SHORT).show();
            answerTV.setText(b.getText().toString());


        }
    }

    private void getRandomQuastion()
    {
        num1 = (int) (Math.random()*100);
        num2 = (int) (Math.random()*100);

        if((int)((Math.random()*10)%2) == 0)
        {
            sign = '+';
            answer = num1 + num2;
        }else
        {
            sign = '-';
            answer = num1 - num2;
        }

    quastionTV.setText(num1 + " " + sign + " " +num2 + " = ?");

    }

    private void getRandomAnswers()
    {
        int rightAnswer, closeAnswer,margin;
        int temp;
        rightAnswer = (int) (Math.random()*10)%4;
        closeAnswer = (int) (Math.random()*10)%4;

        margin = (int) (Math.random()*10)%6;

        while(margin ==  0)
        {
            margin = (int) (Math.random()*10)%6;
        }

        if((int)(Math.random()*10)%2 == 0)
        {
            margin = margin*-1;
        }

        while(closeAnswer == rightAnswer)
        {
            closeAnswer = (int) (Math.random()*10)%4;
        }

        answerBtns.get(rightAnswer).setText(answer+"");
        answerBtns.get(closeAnswer).setText(answer+margin+"");

        for(int i = 0; i < 4; i ++)
        {
            if(i != rightAnswer && i != closeAnswer)
            {
                temp = (int) (Math.random()*100);
                if(answer < 0)
                {
                    temp = temp*-1;
                }

                answerBtns.get(i).setText(temp+"");
            }
        }

    }

}
