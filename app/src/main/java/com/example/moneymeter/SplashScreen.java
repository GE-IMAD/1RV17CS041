package com.example.moneymeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{sleep(50);}
        catch(Exception e){}
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }
}
