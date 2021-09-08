package org.techtown.myfavoritegroupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkBtn(View v){
        Toast.makeText(this, "check button이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }

    public void naver(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void daum(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.daum.net"));
        startActivity(intent);
    }

    public void musinsa(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.store.musinsa.com/"));
        startActivity(intent);
    }

    public void youtube(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/"));
        startActivity(intent);
    }

    public void momCall(View v){
        String number = "01012345678";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + number));
        startActivity(intent);
    }




}