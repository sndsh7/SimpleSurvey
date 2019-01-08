package com.sbt.sandesh.quizgsheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class AddItem extends AppCompatActivity {


    Button buttonToQuiz1,buttonToQuiz2,buttonToQuiz3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item);

        buttonToQuiz1 = (Button)findViewById(R.id.btn_quiz1);
        buttonToQuiz2 = (Button) findViewById(R.id.btn_quiz2);
        buttonToQuiz3 = (Button) findViewById(R.id.btn_quiz3);

        buttonToQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Quiz1.class);
                startActivity(intent);

            }
        });

        buttonToQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Quiz2.class);
                startActivity(intent);
            }
        });

        buttonToQuiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Quiz3.class);
                startActivity(intent);
            }
        });

    }

}
