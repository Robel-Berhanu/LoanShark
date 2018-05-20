package com.example.loanshark;

import android.os.AsyncTask;


         import android.app.AlertDialog;
         import android.content.Context;
         import android.os.AsyncTask;

         import java.io.BufferedReader;
         import java.io.BufferedWriter;
         import java.io.IOException;
         import java.io.InputStream;
         import java.io.InputStreamReader;
         import java.io.OutputStream;
         import java.io.OutputStreamWriter;
         import java.net.HttpURLConnection;
         import java.net.MalformedURLException;
         import java.net.URL;
         import java.net.URLEncoder;

public class BackGroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    BackGroundWorker(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://lamp.ms.wits.ac.za/~s1348744/login.php";
        String register_url = "http://lamp.ms.wits.ac.za/~s1348744/register.php";
        String payment_url = "http://lamp.ms.wits.ac.za/~s1348744/payments.php";
        String borrow_url = "http://lamp.ms.wits.ac.za/~s1348744/Borrow.php";
        if(type.equals("register")){
            try {
                String user_id = params[1];
                String str_name = params[2];
                String str_surname = params[3];
                String str_email = params[4];
                String str_phone = params[5];
                String str_employed = params[6];
                String str_password = params[7];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("User_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&"
                        +URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(str_name,"UTF-8")+"&"
                        +URLEncoder.encode("Surname","UTF-8")+"="+URLEncoder.encode(str_surname,"UTF-8")+"&"
                        +URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(str_email,"UTF-8")+"&"
                        +URLEncoder.encode("Phone","UTF-8")+"="+URLEncoder.encode(str_phone,"UTF-8")+"&"
                        +URLEncoder.encode("employed","UTF-8")+"="+URLEncoder.encode(str_employed,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(str_password,"UTF-8")+"&";
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else if(type.equals("Login")){

            try {
                String user_id = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("User_ID","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException ec){
                ec.printStackTrace();
            }
        } else if(type.equals("makePayment")){
            try {
                String pay = params[1];
                String theDate = params[2];
                String user_id = params[3];


                URL url = new URL(payment_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("Paid","UTF-8")+"="+URLEncoder.encode(pay,"UTF-8")+"&"
                        +URLEncoder.encode("myDate","UTF-8")+"="+URLEncoder.encode(theDate,"UTF-8")+"&"
                        +URLEncoder.encode("theUser","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException mye){
                mye.printStackTrace();
            }

        }else if(type.equals("Borrow")){


            try {
                String borr = params[1];
                String interest = params[2];
                String Time = params[3];
                String Future = params[4];
                String user = params[5];

                URL url = new URL(borrow_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("TheBorrow","UTF-8")+"="+URLEncoder.encode(borr,"UTF-8")+"&"
                        +URLEncoder.encode("theTime","UTF-8")+"="+URLEncoder.encode(Time,"UTF-8")+"&"
                        +URLEncoder.encode("theInterest","UTF-8")+"="+URLEncoder.encode(interest,"UTF-8")+"&"
                        +URLEncoder.encode("theFuture","UTF-8")+"="+URLEncoder.encode(Future,"UTF-8")+"&"
                        +URLEncoder.encode("theTime","UTF-8")+"="+URLEncoder.encode(Time,"UTF-8")+"&"
                        +URLEncoder.encode("theUser","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException ec){
                ec.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Registration Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
