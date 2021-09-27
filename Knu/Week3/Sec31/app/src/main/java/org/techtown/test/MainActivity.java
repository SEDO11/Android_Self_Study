package org.techtown.test;

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
    TextView textv1, textv2;
    Switch swStartBtn;
    RadioGroup rGroup;
    RadioButton rButton[] = new RadioButton[3];
    ImageView imgView;
    Button btnExit, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textv1 = findViewById(R.id.tv1);
        textv2 = findViewById(R.id.tv2);
        swStartBtn = findViewById(R.id.swStart);
        rGroup = findViewById(R.id.rGroup);
        rButton[0] = findViewById(R.id.btnAnd1);
        rButton[1] = findViewById(R.id.btnAnd2);
        rButton[2] = findViewById(R.id.btnAnd3);
        imgView = findViewById(R.id.imgView);
        btnExit = findViewById(R.id.btnExit);
        btnReturn = findViewById(R.id.btnReturn);

        swStartBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(swStartBtn.isChecked() == true){
                    textv2.setVisibility(View.VISIBLE);
                    rGroup.setVisibility(View.VISIBLE);
                    imgView.setVisibility(View.VISIBLE);
                    btnExit.setVisibility(View.VISIBLE);
                    btnReturn.setVisibility(View.VISIBLE);
                } else {
                    textv2.setVisibility(View.INVISIBLE);
                    rGroup.setVisibility(View.INVISIBLE);
                    imgView.setVisibility(View.INVISIBLE);
                    btnExit.setVisibility(View.INVISIBLE);
                    btnReturn.setVisibility(View.INVISIBLE);
                }
            }
        });

        final int draw[] = {R.drawable.andic, R.drawable.andmm, R.drawable.ando};

        for (int i=0; i<rButton.length; i++){
            final int index;
            index = i;
            rButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgView.setImageResource(draw[index]);
                }
            });
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textv2.setVisibility(View.INVISIBLE);
                rGroup.setVisibility(View.INVISIBLE);
                imgView.setVisibility(View.INVISIBLE);
                btnExit.setVisibility(View.INVISIBLE);
                btnReturn.setVisibility(View.INVISIBLE);

                rGroup.clearCheck();
                swStartBtn.setChecked(false);
            }
        });
    }
}