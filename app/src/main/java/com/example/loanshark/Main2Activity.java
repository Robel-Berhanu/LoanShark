package com.example.loanshark;

import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    EditText user_id, password;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user_id = (EditText) findViewById(R.id.editText7);
        password = (EditText) findViewById(R.id.editText9);
        myButton = (Button)findViewById(R.id.button3);
    }


    public void onreg(View v){
        Intent i = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(i);
    }

    public void onLog(View v) {
        final String userID = user_id.getText().toString();
        String userPass =password.getText().toString();

        if (userID.length()!=0 && userPass.length()!=0){
            BackGroundWorker bgWorker = new BackGroundWorker(Main2Activity.this) {


                @Override
                protected void onProgressUpdate(Void... values) {
                    return;
                }

                @Override
                protected void onPreExecute() {
                    return;
                }

                @Override
                protected void onPostExecute(String aVoid) {
                    if (aVoid.equals("login success")) {
                        Intent t = new Intent(Main2Activity.this, Main3Activity.class);
                        t.putExtra("USER_ID",userID);
                        startActivity(t);
                        password.setText("");
                        Toast.makeText(Main2Activity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Main2Activity.this, "Login Failed. Please try again", Toast.LENGTH_LONG).show();

                    }
                }
            };
            bgWorker.execute("Login",userID, userPass);
        }else{
            user_id.setText("");
            password.setText("");
            Toast.makeText(Main2Activity.this, "Please fill out all required data", Toast.LENGTH_LONG).show();
        }

    }






}


