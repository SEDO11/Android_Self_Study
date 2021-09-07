package org.techtown.test;

import androidx.appcompat.app.AppCompatActivity; //안드로이드 기본 라이브러리

import android.os.Bundle;

public class MainActivity extends AppCompatActivity { //상속

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}