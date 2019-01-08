package com.sbt.sandesh.quizgsheet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Quiz1 extends AppCompatActivity {

    private RadioGroup Question1_RG,Question2_RG,Question3_RG,Question4_RG,Question5_RG,Question6_RG,Question7_RG,Question8_RG,Question9_RG,Question10_RG;
    private TextView question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;
    private RadioButton selectAns1,selectAns2,selectAns3,selectAns4,selectAns5,selectAns6,selectAns7,selectAns8,selectAns9,selectAns10;
    private Button qSubmit;

    public String Name,Email,Mobile,Place,Speciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        SharedPreferences result = getSharedPreferences("SaveData",Context.MODE_PRIVATE);
        Name = result.getString("Name","Data not found");
        Email = result.getString("Email","Data not found");
        Mobile = result.getString("Mobile","Data not found");
        Place = result.getString("Place","Data not found");
        Speciality = result.getString("Speciality","Data not found");

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        Question1_RG = (RadioGroup) findViewById(R.id.radio_group1);
        Question2_RG = (RadioGroup) findViewById(R.id.radio_group2);
        Question3_RG = (RadioGroup) findViewById(R.id.radio_group3);
        Question4_RG = (RadioGroup) findViewById(R.id.radio_group4);
        Question5_RG = (RadioGroup) findViewById(R.id.radio_group5);
        Question6_RG = (RadioGroup) findViewById(R.id.radio_group6);
        Question7_RG = (RadioGroup) findViewById(R.id.radio_group7);
        Question8_RG = (RadioGroup) findViewById(R.id.radio_group8);
        Question9_RG = (RadioGroup) findViewById(R.id.radio_group9);
        Question10_RG = (RadioGroup) findViewById(R.id.radio_group10);

        question1 = (TextView) findViewById(R.id.textView_q1);
        question2 = (TextView) findViewById(R.id.textView_q2);
        question3 = (TextView) findViewById(R.id.textView_q3);
        question4 = (TextView) findViewById(R.id.textView_q4);
        question5 = (TextView) findViewById(R.id.textView_q5);
        question6 = (TextView) findViewById(R.id.textView_q6);
        question7 = (TextView) findViewById(R.id.textView_q7);
        question8 = (TextView) findViewById(R.id.textView_q8);
        question9 = (TextView) findViewById(R.id.textView_q9);
        question10 = (TextView) findViewById(R.id.textView_q10);

        qSubmit = (Button) findViewById(R.id.btn_quiz1);

        qSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int SelectedId1 = Question1_RG.getCheckedRadioButtonId();
                int SelectedId2 = Question2_RG.getCheckedRadioButtonId();
                int SelectedId3 = Question3_RG.getCheckedRadioButtonId();
                int SelectedId4 = Question4_RG.getCheckedRadioButtonId();
                int SelectedId5 = Question5_RG.getCheckedRadioButtonId();
                int SelectedId6 = Question6_RG.getCheckedRadioButtonId();
                int SelectedId7 = Question7_RG.getCheckedRadioButtonId();
                int SelectedId8 = Question8_RG.getCheckedRadioButtonId();
                int SelectedId9 = Question9_RG.getCheckedRadioButtonId();
                int SelectedId10 = Question10_RG.getCheckedRadioButtonId();

                selectAns1 = (RadioButton)findViewById(SelectedId1);
                selectAns2 = (RadioButton)findViewById(SelectedId2);
                selectAns3 = (RadioButton)findViewById(SelectedId3);
                selectAns4 = (RadioButton)findViewById(SelectedId4);
                selectAns5 = (RadioButton)findViewById(SelectedId5);
                selectAns6 = (RadioButton)findViewById(SelectedId6);
                selectAns7 = (RadioButton)findViewById(SelectedId7);
                selectAns8 = (RadioButton)findViewById(SelectedId8);
                selectAns9 = (RadioButton)findViewById(SelectedId9);
                selectAns10 = (RadioButton)findViewById(SelectedId10);

//                Toast.makeText(Quiz1.this,
//                        selectAns1.getText(), Toast.LENGTH_SHORT).show();
                Quizz1();
            }
        });


    }

    private void Quizz1()
    {
        final ProgressDialog loading = ProgressDialog.show(this,"Your Response Store","Please wait");
        final String name = Name;
        final String email = Email;
        final String mobile = Mobile;
        final String place = Place;
        final String speciality = Speciality;
        final String Que1 = question1.getText().toString().trim();
        final String Ans1 = selectAns1.getText().toString().trim();
        final String Que2 = question2.getText().toString().trim();
        final String Ans2 = selectAns2.getText().toString().trim();
        final String Que3 = question3.getText().toString().trim();
        final String Ans3 = selectAns3.getText().toString().trim();
        final String Que4 = question4.getText().toString().trim();
        final String Ans4 = selectAns4.getText().toString().trim();
        final String Que5 = question5.getText().toString().trim();
        final String Ans5 = selectAns5.getText().toString().trim();
        final String Que6 = question6.getText().toString().trim();
        final String Ans6 = selectAns6.getText().toString().trim();
        final String Que7 = question7.getText().toString().trim();
        final String Ans7 = selectAns7.getText().toString().trim();
        final String Que8 = question8.getText().toString().trim();
        final String Ans8 = selectAns8.getText().toString().trim();
        final String Que9 = question9.getText().toString().trim();
        final String Ans9 = selectAns9.getText().toString().trim();
        final String Que10 = question10.getText().toString().trim();
        final String Ans10 = selectAns10.getText().toString().trim();




        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbw60lVvOj-HgLT0Ryl9Dv9w1FtHMNG7_nEmMkc8_FRJiSGPNw/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(Quiz1.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                //User Info
                parmas.put("name",name);
                parmas.put("email",email);
                parmas.put("place",place);
                parmas.put("mobile",mobile);
                parmas.put("speciality",speciality);
                //Question & Answer 1
                parmas.put("question1",Que1);
                parmas.put("answer1",Ans1);
                //Question & Answer 2
                parmas.put("question2",Que2);
                parmas.put("answer2",Ans2);
                //Question & Answer 3
                parmas.put("question3",Que3);
                parmas.put("answer3",Ans3);
                //Question & Answer 4
                parmas.put("question4",Que4);
                parmas.put("answer4",Ans4);
                //Question & Answer 5
                parmas.put("question5",Que5);
                parmas.put("answer5",Ans5);
                //Question & Answer 6
                parmas.put("question6",Que6);
                parmas.put("answer6",Ans6);
                //Question & Answer 7
                parmas.put("question7",Que7);
                parmas.put("answer7",Ans7);
                //Question & Answer 8
                parmas.put("question8",Que8);
                parmas.put("answer8",Ans8);
                //Question & Answer 9
                parmas.put("question9",Que9);
                parmas.put("answer9",Ans9);
                //Question & Answer 10
                parmas.put("question10",Que10);
                parmas.put("answer10",Ans10);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

}
