package com.example.loanshark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText id,Name,Surname,Email,Phone,Password, confirmed;
    CheckBox Employed;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText)findViewById(R.id.editText);
        Name = (EditText)findViewById(R.id.editText2);
        Surname = (EditText)findViewById(R.id.editText3);
        Email = (EditText)findViewById(R.id.editText4);
        Phone = (EditText)findViewById(R.id.editText5);
        Password = (EditText)findViewById(R.id.editText6);
        Employed = (CheckBox)findViewById(R.id.checkBox);
        confirmed = (EditText)findViewById(R.id.confirmPassword);
        textView = (TextView) findViewById(R.id.error_password);

    }

    public void onAlready(View view){
        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);
    }

    public void onReg(View view){
        Boolean checkBoxState = Employed.isChecked();
        //Employed.setChecked(true);
        String str_id =  id.getText().toString();
        String str_name = Name.getText().toString();
        String str_Surname = Surname.getText().toString();
        String str_Email = Email.getText().toString();
        String str_phone = Phone.getText().toString();
        String str_password = Password.getText().toString();
        String str_confirmed = confirmed.getText().toString();
        String isEmployed;
        String type = "register";



        if(checkBoxState){
            isEmployed = "Y";
        }else{
            isEmployed = "N";
        }


        if(str_password.equals(str_confirmed)&&(id.length()!=0) || (Name.length()!=0)||(Surname.length()!=0) || (Email.length()!=0)|| (Phone.length()!=0)||(Password.length()!=0)||(confirmed.length()!=0)) {

            BackGroundWorker backGroundWorker = new BackGroundWorker(this);
            backGroundWorker.execute(type, str_id, str_name, str_Surname, str_Email, str_phone, isEmployed, str_password);
            Intent intent = new Intent(this,Main3Activity.class);
            startActivity(intent);

        }
        if(!(str_password.equals(str_confirmed) )){

            Toast.makeText(MainActivity.this, "The passwords don't match!", Toast.LENGTH_LONG).show();
        }

        if(id.length()==0 || (Name.length()!=0)||(Surname.length()!=0) || (Email.length()!=0)|| (Phone.length()!=0)||(Password.length()!=0)||(confirmed.length()!=0)) {
            Toast.makeText(MainActivity.this, "missing required  information", Toast.LENGTH_LONG).show();
        }



    }


}




















