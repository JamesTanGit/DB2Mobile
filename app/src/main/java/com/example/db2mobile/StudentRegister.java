package com.example.db2mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class StudentRegister extends AppCompatActivity {

    EditText email, password, name, phone, parentEmail;
    Button submit;
    Spinner grade, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        grade = (Spinner) findViewById(R.id.grade);
        ArrayAdapter<CharSequence> gradeAdapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grade.setAdapter(gradeAdapter);
        role = (Spinner) findViewById(R.id.role);
        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(roleAdapter);
        submit = (Button) findViewById(R.id.updateParentInfo);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getJSON("http://192.168.0.21/DB2Mobile/php/StudentRegister.php");
            }
        });
    }

    private void getJSON(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {

            String saveEmail, savePassword, saveName, savePhone, saveParentEmail, saveGrade, saveRole;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                email = (EditText) findViewById(R.id.email);
                password = (EditText) findViewById(R.id.password);
                name = (EditText) findViewById(R.id.name);
                phone = (EditText) findViewById(R.id.phone);
                parentEmail = (EditText) findViewById(R.id.parentEmail);
                grade = (Spinner)findViewById(R.id.grade);
                role = (Spinner)findViewById(R.id.role);
                saveEmail = email.getText().toString();
                savePassword = password.getText().toString();
                saveName = name.getText().toString();
                savePhone = phone.getText().toString();
                saveParentEmail = parentEmail.getText().toString();
                saveGrade = grade.getSelectedItem().toString();
                saveRole = role.getSelectedItem().toString();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    HashMap<String, String> params = new HashMap<>();
                    params.put("email", saveEmail);
                    params.put("password", savePassword);
                    params.put("studentname", saveName);
                    params.put("phone", savePhone);
                    params.put("parentemail", saveParentEmail);
                    params.put("grade", saveGrade);
                    params.put("role", saveRole);
                    StringBuilder sbParams = new StringBuilder();
                    int i = 0;
                    for (String key : params.keySet()) {
                        try {
                            if (i != 0) {
                                sbParams.append("&");
                            }
                            sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));
                        }
                        catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestMethod("POST");
                    String paramsString = sbParams.toString();
                    // writes data to php
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(paramsString);
                    wr.flush();
                    wr.close();
                    // runs the php code and gets JSON from it
                    con.getInputStream();
//                    StringBuilder sb = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    return sb.toString().trim();
                    return "Success";
                }
                catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
}