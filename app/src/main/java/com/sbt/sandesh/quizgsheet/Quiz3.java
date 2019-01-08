package com.sbt.sandesh.quizgsheet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Quiz3 extends AppCompatActivity {

    private RadioGroup Question1_RG,Question2_RG,Question3_RG,Question4_RG,Question5_RG;
    private TextView question1,question2,question3,question4,question5;
    private RadioButton selectAns1,selectAns2,selectAns3,selectAns4,selectAns5;
    private Button qSubmit;

    public String Name,Email,Mobile,Place,Speciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        SharedPreferences result = getSharedPreferences("SaveData",Context.MODE_PRIVATE);
        Name = result.getString("Name","Data not found");
        Email = result.getString("Email","Data not found");
        Mobile = result.getString("Mobile","Data not found");
        Place = result.getString("Place","Data not found");
        Speciality = result.getString("Speciality","Data not found");


        addListenerOnButton();
    }

    private void addListenerOnButton() {

        Question1_RG = (RadioGroup) findViewById(R.id.Radio_Montemac1);
        Question2_RG = (RadioGroup) findViewById(R.id.Radio_Montemac2);
        Question3_RG = (RadioGroup) findViewById(R.id.Radio_Montemac3);
        Question4_RG = (RadioGroup) findViewById(R.id.Radio_Montemac4);
        Question5_RG = (RadioGroup) findViewById(R.id.Radio_Montemac5);

        question1 = (TextView) findViewById(R.id.Montemac_Question1);
        question2 = (TextView) findViewById(R.id.Montemac_Question2);
        question3 = (TextView) findViewById(R.id.Montemac_Question3);
        question4 = (TextView) findViewById(R.id.Montemac_Question4);
        question5 = (TextView) findViewById(R.id.Montemac_Question5);

        qSubmit = (Button) findViewById(R.id.btn_quiz3_submit);

        qSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int SelectedId1 = Question1_RG.getCheckedRadioButtonId();
                int SelectedId2 = Question2_RG.getCheckedRadioButtonId();
                int SelectedId3 = Question3_RG.getCheckedRadioButtonId();
                int SelectedId4 = Question4_RG.getCheckedRadioButtonId();
                int SelectedId5 = Question5_RG.getCheckedRadioButtonId();

                selectAns1 = (RadioButton)findViewById(SelectedId1);
                selectAns2 = (RadioButton)findViewById(SelectedId2);
                selectAns3 = (RadioButton)findViewById(SelectedId3);
                selectAns4 = (RadioButton)findViewById(SelectedId4);
                selectAns5 = (RadioButton)findViewById(SelectedId5);

                Quizz3();
            }
        });

    }

    private void Quizz3() {

        final ProgressDialog loading = ProgressDialog.show(this, "Your Response Store", "Please wait");
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbx6WlwWFMHqIZKX-sUs-pRANLHSzmPfehNqM0u2aCNEIt_7tr8/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(Quiz3.this, response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
                parmas.put("action", "addItem");
                //User Info
                parmas.put("name",name);
                parmas.put("email",email);
                parmas.put("place",place);
                parmas.put("mobile",mobile);
                parmas.put("speciality",speciality);
                //Question & Answer 1
                parmas.put("question1", Que1);
                parmas.put("answer1", Ans1);
                //Question & Answer 2
                parmas.put("question2", Que2);
                parmas.put("answer2", Ans2);
                //Question & Answer 3
                parmas.put("question3", Que3);
                parmas.put("answer3", Ans3);
                //Question & Answer 4
                parmas.put("question4", Que4);
                parmas.put("answer4", Ans4);
                //Question & Answer 5
                parmas.put("question5", Que5);
                parmas.put("answer5", Ans5);

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
