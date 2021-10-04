package org.techtown.wondollar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button btn;
    EditText editMoney;
    String s1;
    double usd = 0.00084;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        btn = (Button) findViewById(R.id.btnChange);
        editMoney = (EditText) findViewById(R.id.etWon);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = editMoney.getText().toString();

                result = Double.parseDouble(s1) * usd;
                tvResult.setText(Double.parseDouble(s1)+"원은 " + result +"달러 입니다.");
            }
        });
    }
}