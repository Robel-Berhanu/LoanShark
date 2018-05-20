package com.example.loanshark;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main5Activity extends AppCompatActivity {
    private TextView tvDate;
    private int year, day, month;
    static final int DATE_DIALOG_ID = 1;
    public Button setDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        final EditText payment = (EditText)findViewById(R.id.editText6);

        //addListener();
        Button btnDone = (Button)findViewById(R.id.button6);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
                String theDate = mdformat.format(calendar.getTime());

                String pay = payment.getText().toString();
                String userid = getIntent().getStringExtra("USER");
                String type = "makePayment";

                BackGroundWorker backGroundWorker = new BackGroundWorker(Main5Activity.this);
                backGroundWorker.execute(type,pay,theDate,userid);
                Toast.makeText(Main5Activity.this, "successfully paid", Toast.LENGTH_LONG).show();
            }
        });

    }
}
