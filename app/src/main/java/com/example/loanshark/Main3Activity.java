package com.example.loanshark;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    String SpinnerValue;
   // Button b =(Button)findViewById(R.id.button4);
    TextView t ;
    TextView t2;
    TextView Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final Spinner transactions = (Spinner) findViewById(R.id.spinner);
        t = (TextView)findViewById(R.id.FtextView);
        t2 = (TextView)findViewById(R.id.stext);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.list));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transactions.setAdapter(adapter);

        //transactions.setOnItemSelectedListener((OnItemSelectedListener) this);

        //Adding setOnItemSelectedListener method on spinner.
        transactions.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SpinnerValue = parent.getItemAtPosition(position).toString(); //or parent.getItemAtPosition(pos).toString()
                String user_id = getIntent().getStringExtra("USER_ID");
                changeActivity(SpinnerValue,user_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }


            //
            public boolean isEnabled(int pos) {
                // TODO Auto-generated method stub
                if (pos ==0) {
                    return false;
                }
                else{
                    return true;
                }

            }

        });
        Button b =(Button)findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Amount = (TextView) findViewById(R.id.textView8);

                //get future payment from finances table
                //get sum of payments made from payments table
                //total debt = future payment - sum(payment)

                ContentValues params = new ContentValues();
                //ContentValues params2 = new ContentValues();

                String user_id = getIntent().getStringExtra("USER_ID");
                params.put("theUser_id",user_id);
                //params2.put("theUser_id",user_id);

                AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1348744/Debtplus.php",params) {
                    @Override
                    protected void onPostExecute(String output) {
                        processJSON(output);
                    }
                };
                asyncHttpPost.execute();


//                AsyncHTTPPost asyncHTTPPost2 = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1348744/Debt.php",params) {
//                    @Override
//                    protected void onPostExecute(String output) {
//                        processJSON2(output);
//                    }
//                };
//                asyncHTTPPost2.execute();

            }

        });
    }




    public void changeActivity (String Val ,String user_id) {
        switch(SpinnerValue){
            case "Select Item":
                SpinnerValue = " ";

                break;


            case "Borrow Money":
                Intent i = new Intent(this, Main4Activity.class);
                i.putExtra("USE",user_id);
                startActivity(i);
                break;

            case "Make Payment":
                Intent p = new Intent(this,Main5Activity.class);
                p.putExtra("USER", user_id);
                startActivity(p);
                break;
        }
    }

    public void processJSON(String json){
        try {
            JSONArray all = new JSONArray(json);
            String space = "";
            String space2 = "";

            for (int i=0; i<all.length(); i++){
                JSONObject item=all.getJSONObject(i);
                String futureAmt = item.getString("FUTURE_VALUE");
                String theSum = item.getString("total");
                space = futureAmt;
                space2 = theSum;
                t.setText(space);
                t2.setText(space2);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public void processJSON2(String json){
//        try {
//            JSONArray all = new JSONArray(json);
//            String space = "";
//
//            for (int i=0; i<all.length(); i++){
//                JSONObject item=all.getJSONObject(i);
//                String theSumOfPayments = item.getString("total");
//                space = theSumOfPayments;
//                t2.setText(space);
//
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}