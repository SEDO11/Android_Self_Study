package org.techtown.exam7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout baseLayout;
    EditText edt;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = (RelativeLayout) findViewById(R.id.baseLayout);
        edt = (EditText) findViewById(R.id.edtAngle);
        iv = (ImageView) findViewById(R.id.iv1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.itemRotate:
                iv.setRotation(Float.parseFloat(edt.getText().toString()));
                return true;
            case R.id.item1:
                iv.setImageResource(R.drawable.jeju2);
                return true;
            case R.id.item2:
                iv.setImageResource(R.drawable.jeju14);
                return true;
            case R.id.item3:
                iv.setImageResource(R.drawable.jeju6);
                return true;
        }
        return false;
    }
}