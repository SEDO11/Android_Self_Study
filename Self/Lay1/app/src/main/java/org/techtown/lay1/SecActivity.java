package org.techtown.lay1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SecActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec);
        setTitle("두 번째 레이아웃");
    }
}
