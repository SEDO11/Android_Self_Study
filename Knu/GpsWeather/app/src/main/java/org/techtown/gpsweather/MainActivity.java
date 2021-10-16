package org.techtown.gpsweather;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("금일 코로나 확진자 확인");

        tv1 = (TextView)findViewById(R.id.tv1);

//        AndPermission.with(this)
//                .runtime()
//                .permission(
//                        Permission.ACCESS_FINE_LOCATION,
//                        Permission.ACCESS_COARSE_LOCATION)
//                .onGranted(new Action<List<String>>() {
//                    @Override
//                    public void onAction(List<String> permissions) {
//                        showToast("허용된 권한 갯수 : " + permissions.size());
//                    }
//                })
//                .onDenied(new Action<List<String>>() {
//                    @Override
//                    public void onAction(List<String> permissions) {
//                        showToast("거부된 권한 갯수 : " + permissions.size());
//                    }
//                })
//                .start();

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//    위치정보를 가져오면서 이상이 발생된 듯 하다
//    public String startLocationService() {
//        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        String result = null;
//
//        try {
//            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (location != null) {
//                Double latitude = location.getLatitude();
//                Double longitude = location.getLongitude();
//                int lat = Integer.parseInt(String.valueOf(latitude));
//                int lon = Integer.parseInt(String.valueOf(longitude));
//                result = lat + " " + lon;
//                return result;
//            }
//
//            GPSListener gpsListener = new GPSListener();
//            long minTime = 10000;
//            float minDistance = 0;
//
//            manager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER,
//                    minTime, minDistance, gpsListener);
//
//            Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
//                    Toast.LENGTH_SHORT).show();
//
//        } catch(SecurityException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    static class GPSListener implements LocationListener {
//        public void onLocationChanged(Location location) { }
//
//        public void onProviderDisabled(String provider) { }
//
//        public void onProviderEnabled(String provider) { }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) { }
//    }

    //금일 or 전일 연, 월, 일, 시간 정보 불러오기
    public String time() {
        //현재 시간 불러오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        //년-월-일
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyyMMdd");
        //현재시간
        SimpleDateFormat mFormat = new SimpleDateFormat("hh");
        String getDay = simpleDateFormatDay.format(date);
        String getHour = mFormat.format(date);
        String result = getDay + " " + getHour;
        return result;
    }

    // 버튼을 클릭 했을 경우
    public void btnClick(View v){
        switch (v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        String gps = startLocationService();
                        String time = time();
                        data = getXmlData(time);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv1.setText(data);
                                    Toast.makeText(getApplicationContext(),"금일 코로나 확진 정보를 갖고왔습니다.",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    //코로나 확진자 데이터를 api에서 파싱해서 가져옴
    String getXmlData(String getDay){ //연월일 데이터 가져옴
        StringBuffer buffer=new StringBuffer();
        String ymd = getDay.substring(0, getDay.indexOf(" "));
        String time = getDay.substring(getDay.indexOf(" ")+1);
//        String lat = gps.substring(0, gps.indexOf(" "));
//        String lon = gps.substring(gps.indexOf(" ")+1);
        String query="FWh87%2FqaLEma7tme7KUMsUs6zp6rbczh1uHDI88B80cXFV29f1uSbPx5tCvgP3eH8jf1vxJ1i0vWZbPXUpGelQ%3D%3D";
        String queryUrl="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey="+query+"&numOfRows=10&pageNo=1&base_date=20211016&base_time=0600&nx=55&ny=127";
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기
                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("category")){ //위치
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("obsrValue")){
                            buffer.append("금일 확진자 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append(" 명");
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기
                        buffer.append("dddd");
                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....
}