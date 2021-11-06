package org.techtown.sdcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnPrev, btnNext;
    myPictureView mypicture;
    TextView tvNumber;
    int curNum = 1;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_background);
        setTitle("간단 이미지 뷰어 (변경)");
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        mypicture = (myPictureView) findViewById(R.id.myPictureView1);
        tvNumber = (TextView) findViewById(R.id.tvNumber);

        imageFiles = new File(Environment.getExternalStorageDirectory()
        .getAbsolutePath()+"/Pictures").listFiles();

        imageFname = imageFiles[1].toString();
        mypicture.imagePath = imageFname;
        tvNumber.setText("1" + "/" + (imageFiles.length-1));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum <= 1) {
                    Toast.makeText(getApplicationContext(), "첫번째 그림입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    curNum--;
                }
                imageFname = imageFiles[curNum].toString();
                mypicture.imagePath = imageFname;
                mypicture.invalidate();
                tvNumber.setText((curNum) + "/" + (imageFiles.length-1));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum >= imageFiles.length -1) {
                    Toast.makeText(getApplicationContext(), "마지막 그림입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    curNum++;
                }
                imageFname = imageFiles[curNum].toString();
                mypicture.imagePath = imageFname;
                mypicture.invalidate();
                tvNumber.setText((curNum) + "/" + (imageFiles.length-1));
            }
        });
    }
}