package com.example.loanshark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void onDone(View view){

        EditText Borrow = (EditText) findViewById(R.id.editText8);
        String theBorrow = Borrow.getText().toString();
        String theInterest = "";
        String theTime = "";
        String theTotal = "";
        String type = "Borrow";
        Button button = (Button) findViewById(R.id.button5);

        TextView Interest, Time, Total;

        Interest = (TextView) findViewById(R.id.textView3);
        Total = (TextView) findViewById(R.id.textView5);

        Time = (TextView) findViewById(R.id.textView4);



        String user_id = getIntent().getStringExtra("USE");

       // Total = (TextView) findViewById(R.id.textView5);

        String borrow = Borrow.getText().toString();

        int Amount = Integer.parseInt(borrow);

        double i = 0.0;
        int t =0;
        double Future_Value;

        if(Amount<=2000){
            i = 0.03;
            t = 4;
            theTime = new Integer(t).toString();
            theInterest = new Double(i).toString();


        }else if(2000<Amount && Amount<=4000){
            i = 0.05;
            t =8;
            theTime = new Integer(t).toString();
            theInterest = new Double(i).toString();
        }else if(4000<Amount && Amount<=6000) {
            i = 0.07;
            t = 12;
        }else if(Amount>6000){
            i= 0.1;
            t = 24;
            theTime = new Integer(t).toString();
            theInterest = new Double(i).toString();
        }

        Future_Value = Amount*(1+i*t);

        theTotal = new Double(Future_Value).toString();

        Interest.setText(i + "");
        Time.setText(t +"");
        Total.setText(Future_Value+"");

        BackGroundWorker backGroundWorker = new BackGroundWorker(Main4Activity.this);
        backGroundWorker.execute(type,theBorrow,theInterest,theTime,theTotal,user_id);

    }
}
