package org.techtown.exam01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnMin, btnMul, btnDiv;
    TextView resultView, culView;
    EditText edit1View, edit2View;
    Button[] btnNum = new Button[10];
    Integer[] btnNumId = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
    R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    String num1, num2;
    Integer result;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //액션바 숨기기
        getSupportActionBar().hide();

        //연결
        edit1View = (EditText) findViewById(R.id.edit1);
        edit2View = (EditText) findViewById(R.id.edit2);
        btnAdd = (Button) findViewById(R.id.add);
        btnMin = (Button) findViewById(R.id.min);
        btnMul = (Button) findViewById(R.id.mul);
        btnDiv = (Button) findViewById(R.id.div);
        resultView = (TextView) findViewById(R.id.resultView);
        culView = (TextView) findViewById(R.id.culView);

        //숫자 버튼 연결
        for (i = 0; i < btnNumId.length; i++) {
            btnNum[i] = (Button) findViewById(btnNumId[i]);
        }

        //btnNum[index], 버튼 제어용 for문
        for (i = 0; i < btnNumId.length; i++) {
            final int index; //주의 ! 꼭 필요함
            index = i;

            btnNum[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        //editText1을 선택했을 때
                    if (edit1View.isFocused() == true) {
                        num1 = edit1View.getText().toString() + btnNum[index].getText().toString();
                        edit1View.setText(num1);
                        //editText2를 선택했을 때
                    } else if(edit2View.isFocused()==true){
                        num2 = edit2View.getText().toString() + btnNum[index].getText().toString();
                        edit2View.setText(num2);
                        //edit 텍스트를 선택하지 않았을 때
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //덧셈
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1View.getText().toString();
                num2 = edit2View.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                culView.setText("연산: 덧셈");
                resultView.setText("결과: " + result.toString());
            }
        });

        //뺄셈
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1View.getText().toString();
                num2 = edit2View.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                culView.setText("연산: 뺄셈");
                resultView.setText("결과: " + result.toString());
            }
        });

        //곱셈
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1View.getText().toString();
                num2 = edit2View.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                culView.setText("연산: 곱셈");
                resultView.setText("결과: " + result.toString());
            }
        });

        //나눗셈
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1View.getText().toString();
                num2 = edit2View.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                culView.setText("연산: 나눗셈");
                resultView.setText("결과: " + result.toString());
            }
        });
    }
}