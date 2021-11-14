package org.techtown.newact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity { //상태바를 출력하는 액티비티

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티 (수정)");

        final RadioButton rdoSecond = (RadioButton) findViewById(R.id.rdoSecond);

        Button btnNewAct = (Button) findViewById(R.id.btnNewAct);
        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if(rdoSecond.isChecked() == true) {
                    intent = new Intent(getApplicationContext(), second.class);
                } else {
                    intent = new Intent(getApplicationContext(), third.class);
                }

                startActivity(intent);
            }
        });
    }
}