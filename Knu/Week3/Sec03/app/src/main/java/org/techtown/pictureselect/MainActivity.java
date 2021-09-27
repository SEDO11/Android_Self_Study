package org.techtown.pictureselect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    Switch switchAgree;
    Button btnExit, btnReturn;
    RadioGroup rg;
    RadioButton radioArray[] = new RadioButton[3];
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진 보기");

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        switchAgree = (Switch) findViewById(R.id.switchAgree);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnReturn = (Button) findViewById(R.id.btnReturn);
        rg = (RadioGroup) findViewById(R.id.rg);
        radioArray[0] = (RadioButton) findViewById(R.id.rbP);
        radioArray[1] = (RadioButton) findViewById(R.id.rbQ);
        radioArray[2] = (RadioButton) findViewById(R.id.rbR);
        imgPet = (ImageView) findViewById(R.id.iv1);

        switchAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                //체크되면 보이게 설정
                if(switchAgree.isChecked() == true){
                    tv2.setVisibility(View.VISIBLE);
                    btnExit.setVisibility(View.VISIBLE);
                    btnReturn.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                    //체크를 풀면 다시 안 보이도록 설정
                } else {
                    tv2.setVisibility(View.INVISIBLE);
                    btnExit.setVisibility(View.INVISIBLE);
                    btnReturn.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        //라디오 버튼을 클릭할 때 이미지 뷰를 변경시킴, 배열 처림
        final int draw[] = {R.drawable.pie, R.drawable.q10, R.drawable.r11};

        for (int i=0; i<draw.length; i++){
            final int index;
            index = i;
            radioArray[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgPet.setImageResource(draw[index]);
                }
            });
        }

        //종료버튼 클릭
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //처음으로 버튼 클릭
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setVisibility(View.VISIBLE);
                btnExit.setVisibility(View.VISIBLE);
                btnReturn.setVisibility(View.VISIBLE);
                rg.setVisibility(View.VISIBLE);
                imgPet.setVisibility(View.VISIBLE);

                rg.clearCheck();
                switchAgree.setChecked(false);
            }
        });


    }
}