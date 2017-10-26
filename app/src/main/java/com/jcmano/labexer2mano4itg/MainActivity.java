package com.jcmano.labexer2mano4itg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUser, etPass;
    Button btnShared, btnInternal, btnNext;
    FileOutputStream fos;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.et_userName);
        etPass = (EditText) findViewById(R.id.et_password);
        btnShared = (Button) findViewById(R.id.btn_SharedPreferences);
        btnInternal = (Button) findViewById(R.id.btn_InternalStorage);
        btnNext = (Button) findViewById(R.id.btn_Next);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void saveShared(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",etUser.getText().toString());
        editor.putString("pwd",etPass.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternal (View view){
        String user = "Username is  " + etUser.getText().toString() + " | ";
        String pass = "Password is  " + etPass.getText().toString() + " ";

        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(user.getBytes());
            fos.write(pass.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_SHORT).show();
    }

    public void nextPage (View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

}
