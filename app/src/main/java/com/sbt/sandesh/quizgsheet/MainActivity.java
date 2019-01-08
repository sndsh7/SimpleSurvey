 package com.sbt.sandesh.quizgsheet;

 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.preference.PreferenceManager;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private SharedPreferences sharedPreferences;
     private SharedPreferences.Editor mEditor;

     private EditText u_name,emai_id,mobile_nr,place,speciality;
     public String Name,Email,Mobile,Place,Speciality;
     Button buttonAddItem;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         u_name = (EditText) findViewById(R.id.username);
         emai_id = (EditText) findViewById(R.id.emailId);
         mobile_nr = (EditText) findViewById(R.id.mobileNR);
         place = (EditText) findViewById(R.id.place);
         speciality = (EditText) findViewById(R.id.speciality);

         buttonAddItem = (Button)findViewById(R.id.btn_add_item);
         buttonAddItem.setOnClickListener(this);

         SharedPreferences result = getSharedPreferences("SaveData",Context.MODE_PRIVATE);
         Name = result.getString("Name","Data not found");
         Email = result.getString("Email","Data not found");
         Mobile = result.getString("Mobile","Data not found");
         Place = result.getString("Place","Data not found");
         Speciality = result.getString("Speciality","Data not found");

         u_name.setText(Name);
         emai_id.setText(Email);
         mobile_nr.setText(Mobile);
         place.setText(Place);
         speciality.setText(Speciality);
     }


     @Override
     public void onClick(View v) {

         if(v==buttonAddItem){

             sharedPreferences = getSharedPreferences("SaveData",Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.putString("Name",u_name.getText().toString());
             editor.putString("Email",emai_id.getText().toString());
             editor.putString("Mobile",mobile_nr.getText().toString());
             editor.putString("Place",place.getText().toString());
             editor.putString("Speciality",speciality.getText().toString());
             editor.apply();

             Intent intent = new Intent(getApplicationContext(),AddItem.class);
             startActivity(intent);
         }

     }
 }
