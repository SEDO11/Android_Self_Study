package org.techtown.weather1;
// 날씨 앱, 도시를 edit text를 통해 검색하면 해당 도시의 날씨 정보를 불러와 출력한다.
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView cityView, weatherView, tempView, dateView;
    Button wBtn;
    EditText editCity;
    ImageButton imgBtn;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("날씨");

        wBtn = (Button) findViewById(R.id.weatherBtn);
        imgBtn = (ImageButton) findViewById(R.id.imgBtn);
        dateView = (TextView) findViewById(R.id.dateView);
        cityView = (TextView) findViewById(R.id.cityView);
        weatherView = (TextView) findViewById(R.id.weatherView);
        tempView = (TextView) findViewById(R.id.tempView);
        editCity = (EditText) findViewById(R.id.editcity);

        wBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editCity.getText().toString();
                CurrentWeatherCall(city);
            }
        });
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void CurrentWeatherCall(String city){
        //날씨 받아오는 api, edit text를 통해 받은 city값을 api에 넣어줘서 해당 도시의 날씨 정보를 불러옴
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=cf33495ce789e9e32dc58938c1af0d91";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    //실시간 날씨 확인
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    //년-월-일 출력
                    SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
                    //시-분 출력
                    SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm");
                    String getDay = simpleDateFormatDay.format(date);
                    String getTime = simpleDateFormatTime.format(date);
                    String getDate = getDay + "\n" + getTime;
                    //data textView에 시간 출력
                    dateView.setText(getDate);

                    JSONObject jsonObject = new JSONObject(response);

                    //도시 키값 받기
                    String city = jsonObject.getString("name");
                    //cityView에 도시 출력
                    cityView.setText(city);

                    //날씨 키값 받기
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);
                    String weather = weatherObj.getString("description");
                    //weather view에 날씨 출력
                    weatherView.setText(weather);

                    //기온 키값 받기
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));

                    //기온 받고 켈빈 온도를 섭씨 온도로 변경
                    //정수값으로 온도를 출력하기 위해 int값으로 받음
                    int tempDo = (Math.round((tempK.getInt("temp")-273)*100)/100);
                    //temp view에 날씨 출력
                    tempView.setText(tempDo + "°C");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
}