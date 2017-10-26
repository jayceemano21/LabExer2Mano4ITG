package com.jcmano.labexer2mano4itg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    Button btnLoadShared, btnLoadInternal, btnClear, btnBack;
    TextView tvDisplay;
    SharedPreferences preferences;
    FileInputStream fis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnLoadShared = (Button) findViewById(R.id.btn_LoadShared);
        btnLoadInternal = (Button) findViewById(R.id.btn_LoadInternal);
        btnClear = (Button) findViewById(R.id.btn_Clear);
        btnBack = (Button) findViewById(R.id.btn_Back);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void loadShared(View view){
        String user = preferences.getString("user","");
        String password = preferences.getString("pwd","");
        tvDisplay.setText("User is " + user + " and the password is " + password);
    }

    public void loadInternal (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void backPage (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void clearText(View view){
        tvDisplay.setText("");
    }
}
