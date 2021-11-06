package com.sample.location;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;
import com.google.firebase.firestore.util.Listener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);

        TextView location = findViewById(R.id.location);
        location.setText("위도=" + latitude + ", 경도=" + longitude);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager mLocMan; // 위치 관리자
                mLocMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

                if(!mLocMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    showToast("GPS가 꺼져 있으니 On시켜 주시기 바랍니다 !!");

                    // GPS 설정 화면으로 이동
                    Intent gpsOptionsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(gpsOptionsIntent);
                } else {
                    startLocationService();
                    CurrentWeatherCall();
                }
            }
        });
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    //날씨 api
    public void CurrentWeatherCall(){
        //gps 좌표 받아와서 파싱
        int idx = result.indexOf(" ");
        String lat = result.substring(0, idx);
        String lon = result.substring(idx+1);
        //gps 좌표 위경도 -> x y로 변환(공공기관 api는 x y값으로 좌표를 받기 때문)
        LatXLngY tmp = convertGRID_GPS(TO_GRID, Double.parseDouble(lat), Double.parseDouble(lon));

        //api 키값
        String apiKey = "cf33495ce789e9e32dc58938c1af0d91";
        //공공데이터 api 키값
        //String apiKey = "FWh87%2FqaLEma7tme7KUMsUs6zp6rbczh1uHDI88B80cXFV29f1uSbPx5tCvgP3eH8jf1vxJ1i0vWZbPXUpGelQ%3D%3D";
        tv.setText("현재 위치\n" + lat + " " + lon);

        //api 호출 주소
        String url = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+apiKey;
        //String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey="+apiKey+"&dataType=JSON&numOfRows=10&pageNo=1&base_date=20211025&base_time=0600&nx=55&ny=127";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    tv.setText(lat + " " + lon);
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
//                    JSONObject items = (JSONObject)jsonObject.get("items");
//                    JSONObject item = (JSONObject)items.get("item");
//                    String category = item.getString("category");
//                    weatherView.setText(category);


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

    //토스트 메세지
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
