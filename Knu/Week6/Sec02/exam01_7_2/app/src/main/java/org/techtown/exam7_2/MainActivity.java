package org.techtown.exam7_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    LinearLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배경색 바꾸기(java)");
        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        btn1 = (Button) findViewById(R.id.btn1);
        registerForContextMenu(btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        registerForContextMenu(btn2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v == btn1) {
            menu.setHeaderTitle("배경색 변경");
            menu.add(0, 1, 0, " 배경색 (빨강)");
            menu.add(0, 2, 0, " 배경색 (파랑)");
            menu.add(0, 3, 0, " 배경색 (초록)");
        }
        if(v == btn2) {
            menu.add(0, 4, 0, "버튼 45도 회전");
            menu.add(0, 5, 0, "버튼 2배 확대");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 4:
                baseLayout.setRotation(45);
                return true;
            case 5:
                baseLayout.setScaleX(2);
                return true;
        }
        return false;
    }
}