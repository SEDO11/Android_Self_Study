package org.techtown.liview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트 뷰 테스트");

//      1
//        final String[] mid = { "히어로즈", "24시", "로스트", "로스트룸", "스몰빌", "탐정몽크",
//                "빅뱅이론", "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추럴", "브이" };
        final ArrayList<String> midList = new ArrayList<String>();

//      2
        ListView list = (ListView) findViewById(R.id.listView1);

//      3
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, midList);

//      4
//        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);

        final EditText edtItem = (EditText) findViewById(R.id.edtItem);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);

//      5
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), mid[i], Toast.LENGTH_SHORT).show();
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midList.add(edtItem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                midList.remove(i);
                adapter.notifyDataSetChanged();
            }
        });
    }
}