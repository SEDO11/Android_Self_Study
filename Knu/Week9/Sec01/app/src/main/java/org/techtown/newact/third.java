package org.techtown.newact;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class third extends Activity { //상태바가 없는 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
