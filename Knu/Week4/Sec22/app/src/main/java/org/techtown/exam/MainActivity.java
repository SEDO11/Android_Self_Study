//java로 레이아웃 작성 2
package org.techtown.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //생성
    EditText edt;
    Button btn;
    TextView tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //리니어 레이아웃 그리기
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(baseLayout, params);

        //에딧 텍스트 레이아웃 선언, 힌트, 화면상에서 그리기
        edt = new EditText(this);
        edt.setHint("edt 텍스트 뷰 입니다.");
        baseLayout.addView(edt);

        //버튼
        btn = new Button(this);
        btn.setText("버튼입니다.");
        btn.setBackgroundColor(Color.YELLOW);
        baseLayout.addView(btn);

        //텍스트 뷰
        tview = new TextView(this);
        tview.setText("텍스트뷰 입니다.");
        tview.setTextSize(20);
        tview.setTextColor(Color.MAGENTA);
        baseLayout.addView(tview);

        //버튼을 눌렀을 때 edt 텍스트의 문구를 텍스트 뷰에 넣기
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                tview.setText(edt.getText().toString());
            }
        });
    }
}